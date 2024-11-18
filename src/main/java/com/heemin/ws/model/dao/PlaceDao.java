package com.heemin.ws.model.dao;

import org.apache.ibatis.annotations.Param;

import com.heemin.ws.model.dto.Place;

public interface PlaceDao {
	Place selectById(long id);
	int insertLike(@Param("memberId") long memberId, @Param("placeId") long placeId);
	int deleteLike(@Param("memberId") long memberId, @Param("placeId") long placeId);
	
	int insertBlock(@Param("memberId") long memberId, @Param("placeId") long placeId);
	int deleteBlock(@Param("memberId") long memberId, @Param("placeId") long placeId);
}
