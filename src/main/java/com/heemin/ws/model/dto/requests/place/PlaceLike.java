package com.heemin.ws.model.dto.requests.place;

public class PlaceLike {
	private long id;
	private boolean like;
	public PlaceLike() {
		// TODO Auto-generated constructor stub
	}
	public PlaceLike(long id, boolean like) {
		super();
		this.id = id;
		this.like = like;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public boolean isLike() {
		return like;
	}
	public void setLike(boolean like) {
		this.like = like;
	}
	@Override
	public String toString() {
		return "PlaceLike [placeId=" + id + ", like=" + like + "]";
	}
}
