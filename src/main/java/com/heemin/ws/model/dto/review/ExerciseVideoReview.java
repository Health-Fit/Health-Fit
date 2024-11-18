package com.heemin.ws.model.dto.review;

import java.time.LocalDate;
import java.util.Date;

public class ExerciseVideoReview {
	private long id;
	private long memberId;
	private long exerciseVideoId;
	private String content;
	private long likeCnt;
	private long blockCnt;
	private boolean liked;		// 해당 리뷰 좋아요 표시 여부
	private boolean blocked;	// 해당 리뷰 싫어요 표시 여부
	private int rating;
	private LocalDate regDate;
	private LocalDate updateDate;
	private boolean deleted;
	
	public ExerciseVideoReview() {
		
	}
	
	public ExerciseVideoReview(long memberId, long exerciseVideoId, String content, int rating, boolean deleted) {
		this.memberId = memberId;
		this.exerciseVideoId = exerciseVideoId;
		this.content = content;
		this.rating = rating;
		this.deleted = deleted;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getMemberId() {
		return memberId;
	}

	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}

	public long getExerciseVideoId() {
		return exerciseVideoId;
	}

	public void setExerciseVideoId(long exerciseVideoId) {
		this.exerciseVideoId = exerciseVideoId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getLikeCnt() {
		return likeCnt;
	}

	public void setLikeCnt(long likeCnt) {
		this.likeCnt = likeCnt;
	}

	public long getBlockCnt() {
		return blockCnt;
	}

	public void setBlockCnt(long blockCnt) {
		this.blockCnt = blockCnt;
	}

	public boolean isLiked() {
		return liked;
	}

	public void setLiked(boolean liked) {
		this.liked = liked;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public LocalDate getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDate regDate) {
		this.regDate = regDate;
	}

	public LocalDate getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDate updateDate) {
		this.updateDate = updateDate;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", memberId=" + memberId + ", exerciseVideoId=" + exerciseVideoId + ", content="
				+ content + ", likeCnt=" + likeCnt + ", blockCnt=" + blockCnt + ", rating=" + rating + ", regDate="
				+ regDate + ", updateDate=" + updateDate + ", deleted=" + deleted + "]";
	}
}
