package com.heemin.ws.model.service;

import com.heemin.ws.model.dao.PlaceReviewDao;
import com.heemin.ws.model.dto.place.Place;
import com.heemin.ws.model.dto.place.PlaceReview;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PlaceReviewService {
    PlaceReviewDao placeReviewDao;

    public PlaceReviewService(PlaceReviewDao placeReviewDao) {
        this.placeReviewDao = placeReviewDao;
    }

    // 장소 리뷰 id를 통해 장소 상세정보 받아오기
    public List<PlaceReview> getById(long id, long memberId) {
        List<PlaceReview> placeReviews = placeReviewDao.selectById(id);
        //insertSubInfos(placeReviews, memberId);
        return placeReviews;
    }

    // 장소 리뷰 정보를 DB에 저장하기
    public boolean addReview(PlaceReview placeReview) {
        return placeReviewDao.insertReview(placeReview) == 1;
    }

    // 부가 정보 입력. 카테고리, 현재 아이디에 대한 좋아요, 싫어요 표시 (리스트 형식)
    public void insertSubInfos(List<PlaceReview> placeReviews, long memberId){
        if (memberId != -1){
            insertLiked(placeReviews, memberId);
            insertBlocked(placeReviews, memberId);
        }
    }
    // 부가 정보 입력. 카테고리, 현재 아이디에 대한 좋아요, 싫어요 표시 (객체 형식)
    public void insertSubInfos(PlaceReview placeReview, long memberId){
        if (memberId != -1){
            insertLiked(placeReview, memberId);
            insertBlocked(placeReview, memberId);
        }
    }

    // 현재 아이디 좋아요 정보 입력하기
    public void insertLiked(List<PlaceReview> placeReviews, long userId) {
        for (int i = 0; i < placeReviews.size(); i++) {
            int cnt = placeReviewDao.checkLiked(userId, placeReviews.get(i).getId());
            if (cnt > 0)
                placeReviews.get(i).setLiked(true);
            else
                placeReviews.get(i).setLiked(false);
        }
    }
    public void insertLiked(PlaceReview placeReview, long userId) {
        int cnt = placeReviewDao.checkLiked(userId, placeReview.getId());
        if (cnt > 0)
            placeReview.setLiked(true);
        else
            placeReview.setLiked(false);
    }

    // 현재 아이디 싫어요 정보 입력하기
    public void insertBlocked(List<PlaceReview> placeReviews, long userId) {
        for (int i = 0; i < placeReviews.size(); i++){
            int cnt = placeReviewDao.checkBlocked(userId, placeReviews.get(i).getId());
            if (cnt > 0)
                placeReviews.get(i).setBlocked(true);
            else
                placeReviews.get(i).setLiked(false);
        }
    }
    public void insertBlocked(PlaceReview placeReview, long userId){
        int cnt = placeReviewDao.checkBlocked(userId, placeReview.getId());
        if (cnt > 0)
            placeReview.setBlocked(true);
        else
            placeReview.setBlocked(false);
    }
}
