package com.heemin.ws.model.service;

import com.heemin.ws.model.dao.PlaceReviewDao;
import com.heemin.ws.model.dto.place.PlaceReview;
import java.util.List;

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
