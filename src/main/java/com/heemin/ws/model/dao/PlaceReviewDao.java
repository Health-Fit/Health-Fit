package com.heemin.ws.model.dao;

import com.heemin.ws.model.dto.place.PlaceReview;
import java.util.List;

public interface PlaceReviewDao {
	List<PlaceReview> selectById(long id);
	int insertReview(PlaceReview placeReview);
}
