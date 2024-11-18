package com.heemin.ws.model.dto.place;

import java.time.LocalDate;
import java.util.Date;

public class PlaceReview {
	private long id;
	private long memberId;
	private long exercisePlaceId;
	private String content;
	private long likeCnt;
	private long blockCnt;
	private boolean liked;
	private boolean  blocked;
	private int rating;
	private LocalDate regDate;
	private LocalDate updateDate;
	private boolean deleted;
	
	public PlaceReview() {
		
	}

	public PlaceReview(long id, long memberId, long exercisePlaceId, String content, long likeCnt, long blockCnt,
			boolean liked, boolean blocked, int rating, LocalDate regDate, LocalDate updateDate, boolean deleted) {
		super();
		this.id = id;
		this.memberId = memberId;
		this.exercisePlaceId = exercisePlaceId;
		this.content = content;
		this.likeCnt = likeCnt;
		this.blockCnt = blockCnt;
		this.liked = liked;
		this.blocked = blocked;
		this.rating = rating;
		this.regDate = regDate;
		this.updateDate = updateDate;
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

	public long getExercisePlaceId() {
		return exercisePlaceId;
	}

	public void setExercisePlaceId(long exercisePlaceId) {
		this.exercisePlaceId = exercisePlaceId;
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
		return "PlaceReview [id=" + id + ", memberId=" + memberId + ", exercisePlaceId=" + exercisePlaceId
				+ ", content=" + content + ", likeCnt=" + likeCnt + ", blockCnt=" + blockCnt + ", liked=" + liked
				+ ", blocked=" + blocked + ", rating=" + rating + ", regDate=" + regDate + ", updateDate=" + updateDate
				+ ", deleted=" + deleted + "]";
	}
	
	
}
