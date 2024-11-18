package com.heemin.ws.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.heemin.ws.model.dto.SearchCondition;
import com.heemin.ws.model.dto.Video;

public interface ExerciseVideoDao {
	Video selectById(long id);
	int updateViewCnt(long id);

	List<Video> selectSample();

	List<Video> selectAll();

	List<Video> selectByCondition(SearchCondition condtion);

	List<Video> selectByCategory(int categoryId);

	List<Video> selectOrderByViewCnt();
	
	// 해당 멤버가 좋아요 표시한 영상만 받아오기
	List<Video> selectByLike(long memberId);

	int insertLike(@Param("memberId") long memberId, @Param("videoId") long videoId);
	int deleteLike(@Param("memberId") long memberId, @Param("videoId") long videoId);
	
	int insertBlock(@Param("memberId") long memberId, @Param("videoId") long videoId);
	int deleteBlock(@Param("memberId") long memberId, @Param("videoId") long videoId);

	int checkLiked(long userId, long videoId);
	
	int updateDelete(long videoId);
}
