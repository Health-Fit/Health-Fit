package com.heemin.ws.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.heemin.ws.model.dto.ExerciseReview;

public interface ExerciseVideoReviewDao {
	int insert(ExerciseReview review);
	int update(ExerciseReview review);
	int delete(long id);
	List<ExerciseReview> selectByVideoId(long Id);
	List<ExerciseReview> selectByMemberId(long Id);
	
	int insertLike(@Param("memberId") long memberId, @Param("reviewId") long reviewId);
	int deleteLike(@Param("memberId") long memberId, @Param("reviewId") long reviewId);
	
	int insertBlock(@Param("memberId") long memberId, @Param("reviewId") long reviewId);
	int deleteBlock(@Param("memberId") long memberId, @Param("reviewId") long reviewId);
}
