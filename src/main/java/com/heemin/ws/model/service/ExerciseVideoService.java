package com.ssafy.ssafit.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.CategoryDao;
import com.ssafy.ssafit.model.dao.ExerciseVideoDao;
import com.ssafy.ssafit.model.dto.Response;
import com.ssafy.ssafit.model.dto.SearchCondition;
import com.ssafy.ssafit.model.dto.video.ExerciseVideo;

@Service
public class ExerciseVideoService {

	ExerciseVideoDao videoDao;
	CategoryDao categoryDao;

	public ExerciseVideoService(ExerciseVideoDao videoDao, CategoryDao categoryDao) {
		this.videoDao = videoDao;
		this.categoryDao = categoryDao;
	}

	public List<ExerciseVideo> getVideoList(long memberId) {
		List<ExerciseVideo> videos = videoDao.selectAll();
		insertCategory(videos);
		if (memberId != -1)
			insertLiked(videos, memberId);
		return videos;
	}
	
	public List<ExerciseVideo> getVideoByCondition(long memberId, SearchCondition condition){
		List<ExerciseVideo> videos = videoDao.selectByCondition(condition);
		insertCategory(videos);
		if (memberId != -1)
			insertLiked(videos, memberId);
		return videos;
	}
	
	public List<ExerciseVideo> getVideoSample(){
		List<ExerciseVideo> videos = videoDao.selectSample();
		return videos;
	}

	public ExerciseVideo getVideoById(long id, long memberId) {
		// 영상 보기전 조회수 증가
		videoDao.updateViewCnt(id);
		ExerciseVideo video = videoDao.selectById(id);
		insertCategory(video);
		if (memberId != -1)
		insertLiked(video, memberId);
		return video;
	}

	public List<ExerciseVideo> getVideoByCategory(int categoryId, long memberId) {
		List<ExerciseVideo> videos = videoDao.selectByCategory(categoryId);
		insertCategory(videos);
		if (memberId != -1)
			insertLiked(videos, memberId);
		return videos;
	}

	public List<ExerciseVideo> getVideoOrderByViewCnt(long memberId) {
		List<ExerciseVideo> videos = videoDao.selectOrderByViewCnt();
		insertCategory(videos);
		if (memberId != -1)
			insertLiked(videos, memberId);
		return videos;
	}
	
	public List<ExerciseVideo> getVideoByLike(long memberId){
		List<ExerciseVideo> videos = videoDao.selectByLike(memberId);
		insertCategory(videos);
		if (memberId != -1)
			insertLiked(videos, memberId);
		return videos;
		
	}
	
	public boolean setVideoLike(long memberId, long videoId, boolean like) {
		if (like)
			return videoDao.insertLike(memberId, videoId) == 1;
		else
			return videoDao.deleteLike(memberId, videoId) == 1;
	}
	
	public boolean setVideoBlock(long memberId, long videoId, boolean block) {
		if (block)
			return videoDao.insertBlock(memberId, videoId) == 1;
		else
			return videoDao.deleteBlock(memberId, videoId) == 1;
	}
	
	public boolean removeVideo(long videoId) {
		return videoDao.updateDelete(videoId) == 1;
	}
	
	
	

	public void insertCategory(List<ExerciseVideo> videos) {
		for (int i = 0; i < videos.size(); i++)
			videos.get(i).setCategories(categoryDao.selectExerciseCategoryByVideoId(videos.get(i).getId()));
	}

	public void insertCategory(ExerciseVideo video) {
		video.setCategories(categoryDao.selectExerciseCategoryByVideoId(video.getId()));
	}

	public void insertLiked(List<ExerciseVideo> videos, long userId) {
		for (int i = 0; i < videos.size(); i++) {
			int cnt = videoDao.checkLiked(userId, videos.get(i).getId());
			if (cnt > 0)
				videos.get(i).setLiked(true);
			else
				videos.get(i).setLiked(false);
		}
	}

	public void insertLiked(ExerciseVideo video, long userId) {

		int cnt = videoDao.checkLiked(userId, video.getId());
		if (cnt > 0)
			video.setLiked(true);
		else
			video.setLiked(false);

	}
}
