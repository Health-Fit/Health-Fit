package com.ssafy.ssafit.model.dto.requests.video;

import java.util.List;

import com.ssafy.ssafit.model.dto.review.ExerciseVideoReview;
import com.ssafy.ssafit.model.dto.video.ExerciseVideo;

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
