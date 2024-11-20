package com.heemin.ws.model.dao;

import com.heemin.ws.model.dto.place.PlaceReview;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PlaceReviewDao {
	List<PlaceReview> selectById(long id);
	int insertReview(PlaceReview placeReview);

	int checkLiked(@Param("memberId") long memberId, @Param("reviewId") long reviewId);
	int checkBlocked(@Param("memberId") long memberId, @Param("reviewId") long reviewId);
}
