package com.heemin.ws.model.dto.requests.review;

public class ExerciseReviewBlock {
	private long id;
	private boolean block;
	public ExerciseReviewBlock() {
		
	}
	public ExerciseReviewBlock(long id, boolean block) {
		super();
		this.id = id;
		this.block = block;
	}
	public long getId() {
		return id;
	}
	public void setId(long reviewId) {
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
		return "ExerciseReviewBlock [reviewId=" + id + ", block=" + block + "]";
	}
	
}
