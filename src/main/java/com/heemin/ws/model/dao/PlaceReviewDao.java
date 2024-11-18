package com.heemin.ws.model.dao;

import java.util.List;

import com.heemin.ws.model.dto.PlaceReview;

public interface PlaceReviewDao {
	List<PlaceReview> selectById(long id);
	int insertReview(PlaceReview placeReview);
}
