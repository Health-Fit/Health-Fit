package com.ssafy.ssafit.model.dto.requests.place;

public class PlaceBlock {
	private long placeId;
	private boolean block;
	public PlaceBlock() {
		// TODO Auto-generated constructor stub
	}
	public PlaceBlock(long placeId, boolean block) {
		super();
		this.placeId = placeId;
		this.block = block;
	}
	public long getPlaceId() {
		return placeId;
	}
	public void setPlaceId(long placeId) {
		this.placeId = placeId;
	}
	public boolean isBlock() {
		return block;
	}
	public void setBlock(boolean block) {
		this.block = block;
	}
	@Override
	public String toString() {
		return "PlaceBlock [placeId=" + placeId + ", block=" + block + "]";
	}
	
}
