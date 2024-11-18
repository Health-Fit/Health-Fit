package com.heemin.ws.model.dao;

import com.heemin.ws.model.dto.SearchCondition;
import com.heemin.ws.model.dto.video.ExerciseVideo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExerciseVideoDao {
	ExerciseVideo selectById(long id);
	int updateViewCnt(long id);

	List<ExerciseVideo> selectSample();

	List<ExerciseVideo> selectAll();

	List<ExerciseVideo> selectByCondition(SearchCondition condtion);

	List<ExerciseVideo> selectByCategory(int categoryId);

	List<ExerciseVideo> selectOrderByViewCnt();
	
	// 해당 멤버가 좋아요 표시한 영상만 받아오기
	List<ExerciseVideo> selectByLike(long memberId);

	int insertLike(@Param("memberId") long memberId, @Param("videoId") long videoId);
	int deleteLike(@Param("memberId") long memberId, @Param("videoId") long videoId);
	
	int insertBlock(@Param("memberId") long memberId, @Param("videoId") long videoId);
	int deleteBlock(@Param("memberId") long memberId, @Param("videoId") long videoId);

	int checkLiked(long userId, long videoId);
	
	int updateDelete(long videoId);
}
