package com.heemin.ws.model.dao;

import com.heemin.ws.model.dto.requests.review.ExerciseReviewOrderCondition;
import com.heemin.ws.model.dto.review.ExerciseVideoReview;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExerciseVideoReviewDao {
	int insert(ExerciseVideoReview review);
	int update(ExerciseVideoReview review);
	int delete(long id);

	ExerciseVideoReview selectById(long id);
	List<ExerciseVideoReview> selectByVideoId(@Param("id") long id, @Param("condition") ExerciseReviewOrderCondition orderCondition);
	List<ExerciseVideoReview> selectByMemberId(long id);
	
	int insertLike(@Param("memberId") long memberId, @Param("reviewId") long reviewId);
	int deleteLike(@Param("memberId") long memberId, @Param("reviewId") long reviewId);
	int likeCntUp(long id);
	int likeCntDown(long id);
	
	int insertBlock(@Param("memberId") long memberId, @Param("reviewId") long reviewId);
	int deleteBlock(@Param("memberId") long memberId, @Param("reviewId") long reviewId);
	int blockCntUp(long id);
	int blockCntDown(long id);

	int checkLiked(@Param("memberId") long memberId, @Param("reviewId") long reviewId);
	int checkBlocked(@Param("memberId") long memberId, @Param("reviewId") long reviewId);
}
