package com.heemin.ws.model.service;

import com.heemin.ws.model.dao.PlaceDao;
import com.heemin.ws.model.dto.place.Place;
import com.heemin.ws.model.dto.review.ExerciseVideoReview;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlaceService {
	PlaceDao placeDao;
	
	public PlaceService(PlaceDao placeDao) {
		this.placeDao = placeDao;
	}

	// 장소 id 이용하여 장소 상세정보 받아오기
	public Place getById(long id, long memberId) {
		Place place = placeDao.selectById(id);
		insertSubInfos(place, memberId);
		return place;
	}

	// 장소 좋아요 표시
	@Transactional
	public boolean setLike(long memberId, long placeId, boolean like) {
		if (like){
			// 좋아요 표시되었으면, 해당 장소에 대한 싫어요 취소
			int likeCnt = placeDao.insertLike(memberId, placeId);
			if (likeCnt == 1){
				placeDao.deleteBlock(memberId, placeId);
				return true;
			}
		}
		else
			return placeDao.deleteLike(memberId, placeId) == 1;
		return false;
	}

	// 장소 싫어요 표시
	@Transactional
	public boolean setBlock(long memberId, long placeId, boolean block) {
		if (block){
			// 싫어요 표시되었으면, 해당 장소에 대한 좋아요 취소
			int blockCnt = placeDao.insertBlock(memberId, placeId);
			if (blockCnt == 1){
				placeDao.deleteLike(memberId, placeId);
				return true;
			}
		}
		else
			return placeDao.deleteBlock(memberId, placeId) == 1;
		return false;
	}

	// 부가 정보 입력. 카테고리, 현재 아이디에 대한 좋아요, 싫어요 표시 (리스트 형식)
	public void insertSubInfos(List<Place> places, long memberId){
		if (memberId != -1){
			insertLiked(places, memberId);
			insertBlocked(places, memberId);
		}
	}
	// 부가 정보 입력. 카테고리, 현재 아이디에 대한 좋아요, 싫어요 표시 (객체 형식)
	public void insertSubInfos(Place place, long memberId){
		if (memberId != -1){
			insertLiked(place, memberId);
			insertBlocked(place, memberId);
		}
	}

	// 현재 아이디 좋아요 정보 입력하기
	public void insertLiked(List<Place> places, long userId) {
		for (int i = 0; i < places.size(); i++) {
			int cnt = placeDao.checkLiked(userId, places.get(i).getId());
			if (cnt > 0)
				places.get(i).setLiked(true);
			else
				places.get(i).setLiked(false);
		}
	}
	public void insertLiked(Place place, long userId) {
		int cnt = placeDao.checkLiked(userId, place.getId());
		if (cnt > 0)
			place.setLiked(true);
		else
			place.setLiked(false);
	}

	// 현재 아이디 싫어요 정보 입력하기
	public void insertBlocked(List<Place> places, long userId) {
		for (int i = 0; i < places.size(); i++){
			int cnt = placeDao.checkBlocked(userId, places.get(i).getId());
			if (cnt > 0)
				places.get(i).setBlocked(true);
			else
				places.get(i).setLiked(false);
		}
	}
	public void insertBlocked(Place place, long userId){
		int cnt = placeDao.checkBlocked(userId, place.getId());
		if (cnt > 0)
			place.setBlocked(true);
		else
			place.setBlocked(false);
	}
}
