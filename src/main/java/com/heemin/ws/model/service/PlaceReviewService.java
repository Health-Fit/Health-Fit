package com.ssafy.ssafit.model.service;

import java.util.List;

import com.ssafy.ssafit.model.dao.PlaceReviewDao;
import com.ssafy.ssafit.model.dto.place.PlaceReview;

public class PlaceReviewService {
	PlaceReviewDao placeReviewDao;
	public PlaceReviewService(PlaceReviewDao placeReviewDao) {
		this.placeReviewDao = placeReviewDao;
	}
	
	public List<PlaceReview> getById(long id){
		return placeReviewDao.selectById(id);
	}
	
	public boolean addReview(PlaceReview placeReview) {
		return placeReviewDao.insertReview(placeReview) == 1;
	}
}
