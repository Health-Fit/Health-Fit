package com.heemin.ws.model.service;

import com.heemin.ws.exception.ForbiddenException;
import com.heemin.ws.model.dao.ExerciseVideoReviewDao;
import com.heemin.ws.model.dto.requests.review.ExerciseReviewOrderCondition;
import com.heemin.ws.model.dto.review.ExerciseVideoReview;

import java.time.LocalDateTime;
import java.util.List;

import com.heemin.ws.model.dto.video.ExerciseVideo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExerciseVideoReviewService {
    private ExerciseVideoReviewDao reviewDao;
    
    public ExerciseVideoReviewService(ExerciseVideoReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }
    
    public void add(ExerciseVideoReview review) {
        if (reviewDao.insert(review) != 1) {
            throw new ForbiddenException();
        }
    }
    
    public void update(ExerciseVideoReview review, long memberId) {
        // 해당 리뷰를 받아와서 리뷰 작성 아이디가 제대로 된 아이디인지 확인
        ExerciseVideoReview orgReview = reviewDao.selectById(review.getId());
        if (orgReview != null && orgReview.getMemberId() == memberId){
            review.setUpdateDate(LocalDateTime.now());
            if (reviewDao.update(review) != 1) {
                throw new ForbiddenException();
            }
        }else
            throw new ForbiddenException();
    }
    
    public void remove(long id, long memberId) {
        // 해당 리뷰를 받아와서 리뷰 작성 아이디가 제대로 된 아이디인지 확인
        ExerciseVideoReview review = reviewDao.selectById(id);
        if (review != null && review.getMemberId() == memberId){
            if (reviewDao.delete(id) != 1) {
                throw new ForbiddenException();
            }
        }else
            throw new ForbiddenException();
    }
    
    public List<ExerciseVideoReview> getByVideoId(long id, long memberId, ExerciseReviewOrderCondition orderCondition){
        List<ExerciseVideoReview> reviews = reviewDao.selectByVideoId(id, orderCondition);
        insertSubInfos(reviews, memberId);
        return reviews;
    }
    
    public List<ExerciseVideoReview> getByMemberId(long id, long memberId){
        List<ExerciseVideoReview> reviews = reviewDao.selectByMemberId(id);
        insertSubInfos(reviews, memberId);
        return reviews;
    }

    @Transactional
    public boolean setReviewLike(long memberId, long reviewId, boolean like) {
        int likeCnt = 0;

        if (like){
            // 혹시라도 존재하는 block 삭제 ( like 와 같이 되어있을 수 없음 )
            int blockCnt = reviewDao.deleteBlock(memberId, reviewId);
            // 삭제된 block이 존재한다면 block_cnt를 낮춰줘야 한다.
            if (blockCnt == 1)
                reviewDao.blockCntDown(reviewId);
            // like를 하나 올려준다.
            likeCnt = reviewDao.insertLike(memberId, reviewId);
            if (likeCnt == 1){
                reviewDao.likeCntUp(reviewId);
                return true;
            }
        }
		else{
            // like를 하나 내려준다.
            likeCnt = reviewDao.deleteLike(memberId, reviewId);
            if (likeCnt == 1){
                reviewDao.likeCntDown(reviewId);
                return true;
            }
        }
        return false;
	}

    @Transactional
	public boolean setReviewBlock(long memberId, long reviewId, boolean block) {
        int blockCnt = 0;
		if (block){
            // 혹시라도 존재하는 like 삭제
            int likeCnt = reviewDao.deleteLike(memberId, reviewId);
            // 삭제된 like가 존재한다면 like-cnt를 낮춰줘야 한다.
            reviewDao.likeCntDown(reviewId);

            // block을 하나 올려준다.
            blockCnt = reviewDao.insertBlock(memberId, reviewId);
            if (blockCnt == 1){
                reviewDao.blockCntUp(reviewId);
                return true;
            }
        }
		else{
            // block을 하나 내려준다.
            blockCnt = reviewDao.deleteBlock(memberId, reviewId);
            if (blockCnt == 1){
                reviewDao.blockCntDown(reviewId);
                return true;
            }
        }
        return false;
	}


    // 부가 정보 입력. 카테고리, 현재 아이디에 대한 좋아요, 싫어요 표시 (리스트 형식)
    public void insertSubInfos(List<ExerciseVideoReview> reviews, long memberId){
        if (memberId != -1){
            insertLiked(reviews, memberId);
            insertBlocked(reviews, memberId);
        }
    }
    // 부가 정보 입력. 카테고리, 현재 아이디에 대한 좋아요, 싫어요 표시 (객체 형식)
    public void insertSubInfos(ExerciseVideoReview review, long memberId){
        if (memberId != -1){
            insertLiked(review, memberId);
            insertBlocked(review, memberId);
        }
    }

    // 현재 아이디 좋아요 정보 입력하기
    public void insertLiked(List<ExerciseVideoReview> reviews, long userId) {
        for (int i = 0; i < reviews.size(); i++) {
            int cnt = reviewDao.checkLiked(userId, reviews.get(i).getId());
            if (cnt > 0)
                reviews.get(i).setLiked(true);
            else
                reviews.get(i).setLiked(false);
        }
    }
    public void insertLiked(ExerciseVideoReview review, long userId) {
        int cnt = reviewDao.checkLiked(userId, review.getId());
        if (cnt > 0)
            review.setLiked(true);
        else
            review.setLiked(false);
    }

    // 현재 아이디 싫어요 정보 입력하기
    public void insertBlocked(List<ExerciseVideoReview> reviews, long userId) {
        for (int i = 0; i < reviews.size(); i++){
            int cnt = reviewDao.checkBlocked(userId, reviews.get(i).getId());
            if (cnt > 0)
                reviews.get(i).setBlocked(true);
            else
                reviews.get(i).setLiked(false);
        }
    }
    public void insertBlocked(ExerciseVideoReview review, long userId){
        int cnt = reviewDao.checkBlocked(userId, review.getId());
        if (cnt > 0)
            review.setBlocked(true);
        else
            review.setBlocked(false);
    }
}
