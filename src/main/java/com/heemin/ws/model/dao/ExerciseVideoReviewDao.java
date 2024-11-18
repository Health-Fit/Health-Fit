package com.heemin.ws.model.dao;

import com.heemin.ws.model.dto.review.ExerciseVideoReview;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExerciseVideoReviewDao {
	int insert(ExerciseVideoReview review);
	int update(ExerciseVideoReview review);
	int delete(long id);
	List<ExerciseVideoReview> selectByVideoId(long Id);
	List<ExerciseVideoReview> selectByMemberId(long Id);
	
	int insertLike(@Param("memberId") long memberId, @Param("reviewId") long reviewId);
	int deleteLike(@Param("memberId") long memberId, @Param("reviewId") long reviewId);
	
	int insertBlock(@Param("memberId") long memberId, @Param("reviewId") long reviewId);
	int deleteBlock(@Param("memberId") long memberId, @Param("reviewId") long reviewId);
}
