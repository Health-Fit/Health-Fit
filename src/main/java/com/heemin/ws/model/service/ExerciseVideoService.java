package com.heemin.ws.model.service;

import com.heemin.ws.model.dao.CategoryDao;
import com.heemin.ws.model.dao.ExerciseVideoDao;
import com.heemin.ws.model.dto.SearchCondition;
import com.heemin.ws.model.dto.video.ExerciseVideo;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		insertSubInfos(videos, memberId);
		return videos;
	}

	// 검색조건에 의해서 비디오 정보 받아오기
	public List<ExerciseVideo> getVideoByCondition(long memberId, SearchCondition condition){
		List<ExerciseVideo> videos = videoDao.selectByCondition(condition);
		// 영상 상세 정보(카테고리, 좋아요, 싫어요 표시 여부) 넣기
		insertSubInfos(videos, memberId);
		return videos;
	}

	// 비디오 예시 불러오기
	public List<ExerciseVideo> getVideoSample(){
		List<ExerciseVideo> videos = videoDao.selectSample();
		return videos;
	}

	// 영상 상세정보 가져오기 기준
	public ExerciseVideo getVideoById(long id, long memberId) {
		// 영상 보기전 조회수 증가
		videoDao.updateViewCnt(id);
		ExerciseVideo video = videoDao.selectById(id);
		insertSubInfos(video, memberId);
		return video;
	}

	public List<ExerciseVideo> getVideoByCategory(int categoryId, long memberId) {
		List<ExerciseVideo> videos = videoDao.selectByCategory(categoryId);
		insertSubInfos(videos, memberId);
		return videos;
	}

	public List<ExerciseVideo> getVideoOrderByViewCnt(long memberId) {
		List<ExerciseVideo> videos = videoDao.selectOrderByViewCnt();
		insertSubInfos(videos, memberId);
		return videos;
	}

	// 해당 멤버 아이디가 좋아요 표시한 동영상 가져오기
	public List<ExerciseVideo> getVideoByLike(long memberId){
		List<ExerciseVideo> videos = videoDao.selectByLike(memberId);
		insertSubInfos(videos, memberId);
		return videos;
		
	}

	@Transactional
	public boolean setVideoLike(long memberId, long videoId, boolean like) {
		// 좋아요 표시하면 싫어요의 삭제와 동시에 일어나야 한다.
		if (like){
			videoDao.deleteBlock(memberId, videoId);
			return videoDao.insertLike(memberId, videoId) == 1;
		}
		else
			return videoDao.deleteLike(memberId, videoId) == 1;
	}

	@Transactional
	public boolean setVideoBlock(long memberId, long videoId, boolean block) {
		// 싫어요를 표시하면 좋아요의 삭제와 동시에 일어나야 한다.
		if (block){
			videoDao.deleteLike(memberId, videoId);
			return videoDao.insertBlock(memberId, videoId) == 1;
		}
		else
			return videoDao.deleteBlock(memberId, videoId) == 1;
	}
	
	public boolean removeVideo(long videoId) {
		return videoDao.updateDelete(videoId) == 1;
	}
	

	// 부가 정보 입력. 카테고리, 현재 아이디에 대한 좋아요, 싫어요 표시 (리스트 형식)
	public void insertSubInfos(List<ExerciseVideo> videos, long memberId){
		insertCategory(videos);
		if (memberId != -1){
			insertLiked(videos, memberId);
			insertBlocked(videos, memberId);
		}
	}
	// 부가 정보 입력. 카테고리, 현재 아이디에 대한 좋아요, 싫어요 표시 (객체 형식)
	public void insertSubInfos(ExerciseVideo video, long memberId){
		insertCategory(video);
		if (memberId != -1){
			insertLiked(video, memberId);
			insertBlocked(video, memberId);
		}
	}

	// 카테고리 영화 정보에 입력하기
	public void insertCategory(List<ExerciseVideo> videos) {
		for (int i = 0; i < videos.size(); i++)
			videos.get(i).setCategories(categoryDao.selectExerciseCategoryByVideoId(videos.get(i).getId()));
	}
	public void insertCategory(ExerciseVideo video) {
		video.setCategories(categoryDao.selectExerciseCategoryByVideoId(video.getId()));
	}

	// 현재 아이디 좋아요 정보 입력하기
	public void insertLiked(List<ExerciseVideo> videos, long memberId) {
		for (int i = 0; i < videos.size(); i++) {
			int cnt = videoDao.checkLiked(memberId, videos.get(i).getId());
			if (cnt > 0)
				videos.get(i).setLiked(true);
			else
				videos.get(i).setLiked(false);
		}
	}
	public void insertLiked(ExerciseVideo video, long memberId) {
		int cnt = videoDao.checkLiked(memberId, video.getId());
		if (cnt > 0)
			video.setLiked(true);
		else
			video.setLiked(false);
	}

	// 현재 아이디 싫어요 정보 입력하기
	public void insertBlocked(List<ExerciseVideo> videos, long userId) {
		for (int i = 0; i < videos.size(); i++){
			int cnt = videoDao.checkBlocked(userId, videos.get(i).getId());
			if (cnt > 0)
				videos.get(i).setBlocked(true);
			else
				videos.get(i).setLiked(false);
		}
	}
	public void insertBlocked(ExerciseVideo video, long userId){
		int cnt = videoDao.checkBlocked(userId, video.getId());
		if (cnt > 0)
			video.setBlocked(true);
		else
			video.setBlocked(false);
	}
}
