package com.heemin.ws.model.dto.requests.place;

public class PlaceBlock {
	private long id;
	private boolean block;
	public PlaceBlock() {
		// TODO Auto-generated constructor stub
	}
	public PlaceBlock(long id, boolean block) {
		super();
		this.id = id;
		this.block = block;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public boolean isBlock() {
		return block;
	}
	public void setBlock(boolean block) {
		this.block = block;
	}
	@Override
	public String toString() {
		return "PlaceBlock [placeId=" + id + ", block=" + block + "]";
	}
	
}
