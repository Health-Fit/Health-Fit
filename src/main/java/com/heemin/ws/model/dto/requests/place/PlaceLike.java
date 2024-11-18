package com.heemin.ws.model.dto.requests.place;

public class PlaceLike {
	private long placeId;
	private boolean like;
	public PlaceLike() {
		// TODO Auto-generated constructor stub
	}
	public PlaceLike(long placeId, boolean like) {
		super();
		this.placeId = placeId;
		this.like = like;
	}
	public long getPlaceId() {
		return placeId;
	}
	public void setPlaceId(long placeId) {
		this.placeId = placeId;
	}
	public boolean isLike() {
		return like;
	}
	public void setLike(boolean like) {
		this.like = like;
	}
	@Override
	public String toString() {
		return "PlaceLike [placeId=" + placeId + ", like=" + like + "]";
	}
	
}
