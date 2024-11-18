package com.heemin.ws.model.dao;

import com.heemin.ws.model.dto.place.Place;
import org.apache.ibatis.annotations.Param;

public interface PlaceDao {
	Place selectById(long id);
	int insertLike(@Param("memberId") long memberId, @Param("placeId") long placeId);
	int deleteLike(@Param("memberId") long memberId, @Param("placeId") long placeId);
	
	int insertBlock(@Param("memberId") long memberId, @Param("placeId") long placeId);
	int deleteBlock(@Param("memberId") long memberId, @Param("placeId") long placeId);
}
