package com.ssafy.ssafit.model.dto.requests.video;

public class VideoBlock {
	private long videoId;
	private boolean block;
	public VideoBlock() {
		
	}
	public VideoBlock(long videoId, boolean block) {
		this.videoId = videoId;
		this.block = block;
	}
	public long getVideoId() {
		return videoId;
	}
	public void setVideoId(long videoId) {
		this.videoId = videoId;
	}
	public boolean isBlock() {
		return block;
	}
	public void setBlock(boolean block) {
		this.block = block;
	}
	@Override
	public String toString() {
		return "VideoBlock [videoId=" + videoId + ", block=" + block + "]";
	}
	
}
