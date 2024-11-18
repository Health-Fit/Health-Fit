package com.ssafy.ssafit.model.dto.requests.review;

public class ExerciseReviewLike {
	private long reviewId;
	private boolean like;
	public ExerciseReviewLike() {
	
	}
	public ExerciseReviewLike(long reviewId, boolean like) {
		super();
		this.reviewId = reviewId;
		this.like = like;
	}
	public long getReviewId() {
		return reviewId;
	}
	public void setReviewId(long reviewId) {
		this.reviewId = reviewId;
	}
	public boolean isLike() {
		return like;
	}
	public void setLike(boolean like) {
		this.like = like;
	}
	@Override
	public String toString() {
		return "ExerciseReviewLike [reviewId=" + reviewId + ", like=" + like + "]";
	}
	
}
