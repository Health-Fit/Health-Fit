package com.heemin.ws.model.dto.requests.video;

public class VideoLike {
	private long videoId;
	private boolean like;
	public VideoLike() {
		
	}
	public VideoLike(long videoId, boolean like) {
		this.videoId = videoId;
		this.like = like;
	}
	public long getVideoId() {
		return videoId;
	}
	public void setVideoId(long videoId) {
		this.videoId = videoId;
	}
	public boolean isLike() {
		return like;
	}
	public void setLike(boolean like) {
		this.like = like;
	}
	@Override
	public String toString() {
		return "VideoLike [videoId=" + videoId + ", like=" + like + "]";
	}
}
