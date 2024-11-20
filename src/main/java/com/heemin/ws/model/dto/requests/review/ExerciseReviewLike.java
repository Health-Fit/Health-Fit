package com.heemin.ws.model.dto.requests.review;

public class ExerciseReviewLike {
	private long id;
	private boolean like;
	public ExerciseReviewLike() {
	
	}
	public ExerciseReviewLike(long id, boolean like) {
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
		return "ExerciseReviewLike [reviewId=" + id + ", like=" + like + "]";
	}
	
}
