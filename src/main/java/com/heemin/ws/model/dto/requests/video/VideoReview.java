package com.heemin.ws.model.dto.requests.video;

import com.heemin.ws.model.dto.video.ExerciseVideo;
import java.util.List;

public class VideoReview {
	private ExerciseVideo video;
	private List<VideoReview> reviews;
	
	public VideoReview() {
		
	}
	public VideoReview(ExerciseVideo video, List<VideoReview> reviews) {
		super();
		this.video = video;
		this.reviews = reviews;
	}
	public ExerciseVideo getVideo() {
		return video;
	}
	public void setVideo(ExerciseVideo video) {
		this.video = video;
	}
	public List<VideoReview> getReviews() {
		return reviews;
	}
	public void setReviews(List<VideoReview> reviews) {
		this.reviews = reviews;
	}
	
}
