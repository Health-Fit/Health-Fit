package com.heemin.ws.model.dto.requests.review;

public class ExerciseReviewBlock {
	private long reviewId;
	private boolean block;
	public ExerciseReviewBlock() {
		
	}
	public ExerciseReviewBlock(long reviewId, boolean block) {
		super();
		this.reviewId = reviewId;
		this.block = block;
	}
	public long getReviewId() {
		return reviewId;
	}
	public void setReviewId(long reviewId) {
		this.reviewId = reviewId;
	}
	public boolean isBlock() {
		return block;
	}
	public void setBlock(boolean block) {
		this.block = block;
	}
	@Override
	public String toString() {
		return "ExerciseReviewBlock [reviewId=" + reviewId + ", block=" + block + "]";
	}
	
}
