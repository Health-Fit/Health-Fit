package com.heemin.ws.model.service;

import com.heemin.ws.model.dao.PlaceDao;
import com.heemin.ws.model.dto.place.Place;

public class PlaceService {
	PlaceDao placeDao;
	
	public PlaceService(PlaceDao placeDao) {
		this.placeDao = placeDao;
	}
	
	public Place getById(long id) {
		return placeDao.selectById(id);
	}
	
	public boolean setLike(long memberId, long placeId, boolean like) {
		if (like)
			return placeDao.insertLike(memberId, placeId) == 1;
		else
			return placeDao.deleteLike(memberId, placeId) == 1;
	}
	
	public boolean setBlock(long memberId, long placeId, boolean block) {
		if (block)
			return placeDao.insertBlock(memberId, placeId) == 1;
		else
			return placeDao.deleteBlock(memberId, placeId) == 1;
	}
}
