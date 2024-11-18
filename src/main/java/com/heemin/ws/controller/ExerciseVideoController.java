package com.heemin.ws.controller;

import com.heemin.ws.model.dto.SearchCondition;
import com.heemin.ws.model.dto.member.Member;
import com.heemin.ws.model.dto.requests.video.VideoBlock;
import com.heemin.ws.model.dto.requests.video.VideoLike;
import com.heemin.ws.model.dto.video.ExerciseVideo;
import com.heemin.ws.model.service.ExerciseVideoReviewService;
import com.heemin.ws.model.service.ExerciseVideoService;
import com.heemin.ws.model.service.MemberService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/videos")
public class ExerciseVideoController {
	
	MemberService memberService;
	ExerciseVideoService videoService;
	ExerciseVideoReviewService reviewService;

	public ExerciseVideoController(MemberService memberService, ExerciseVideoService videoService,
								   ExerciseVideoReviewService reviewService) {
		this.memberService = memberService;
		this.videoService = videoService;
		this.reviewService = reviewService;
	}

	// 운동 영상 목록 조회 ( + 검색)
	@PostMapping("")
	public ResponseEntity<?> getList(@RequestBody SearchCondition searchCondition, HttpSession session){
		long memberId = -1;
		if (session.getAttribute("memberId") != null)
			memberId = (Long)session.getAttribute("memberId");
		
		// 유저 정보를 넣어서 유저가 좋아요 표시를 했는지 받아오기
		List<ExerciseVideo> videos = videoService.getVideoByCondition(memberId, searchCondition);
		if (videos == null || videos.size() == 0)
			return new ResponseEntity<String>("등록된 비디오 영상 자료가 없습니다.", HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<ExerciseVideo>>(videos, HttpStatus.OK);
	}
	
	// 회원가입시 사용할 샘플 비디오 목록 반환
	@GetMapping("/ex")
	public ResponseEntity<?> getExample(){
		List<ExerciseVideo> videos = videoService.getVideoSample();
		return new ResponseEntity<List<ExerciseVideo>>(videos, HttpStatus.OK);
	}
	
	/**
	 * 영상 상세정보를 받기 위해 신청할 수 있는 API
	 * 
	 * @param id 받고자 하는 영상 상세정보 id
	 * @param session 세션 정보를 받아오기
	 * @return 영상 상세 정보 반환
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> getDetail(@PathVariable long id, HttpSession session){
		long memberId = -1;
		if (session.getAttribute("memberId") != null)
		memberId = (Long)session.getAttribute("memberId");
		
		ExerciseVideo video = videoService.getVideoById(id, memberId);
		if (video == null)
			return new ResponseEntity<String>("등록된 비디오 영상 자료가 없습니다.", HttpStatus.NOT_FOUND);
		return new ResponseEntity<ExerciseVideo>(video, HttpStatus.OK);
	}
	
	// 사용자 권한에 따른 영상정보 삭제
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remove(HttpSession session, @PathVariable long id){
		long memberId = -1;
		if (session.getAttribute("memberId") != null)
			memberId = (Long)session.getAttribute("memberId");
		
		Member curMem = memberService.getById(memberId);
		
		// 관리자가 아닌 경우 authorized 되지 않았음을 보내줌
		if (curMem == null || curMem.getMemberAuthId() != 0)
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		
		if (videoService.removeVideo(id))
			return new ResponseEntity<Void>(HttpStatus.OK);
		return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 영상 좋아요 관리 API
	@PutMapping("/like")
	public ResponseEntity<?> setLike(HttpSession session, @RequestBody VideoLike videoLike){
		long memberId = -1;
		if (session.getAttribute("memberId") != null)
			memberId = (Long)session.getAttribute("memberId");
		// 로그인하지 않은 경우 BAD REQUEST로 돌림
		if (memberId == -1)
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		
		if (videoService.setVideoLike(memberId, videoLike.getVideoId(), videoLike.isLike()))
			return new ResponseEntity<Void>(HttpStatus.OK);
		else
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 영상 싫어요 관리 API
	@PutMapping("/block")
	public ResponseEntity<?> setBlock(HttpSession session, @RequestBody VideoBlock videoBlock){
		long memberId = -1;
		if (session.getAttribute("memberId") != null)
			memberId = (Long)session.getAttribute("memberId");
		// 로그인하지 않은 경우 BAD REQUEST로 돌림
		if (memberId == -1)
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		
		if (videoService.setVideoLike(memberId, videoBlock.getVideoId(), videoBlock.isBlock()))
			return new ResponseEntity<Void>(HttpStatus.OK);
		else
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 본인이 찜한 영상 받아오기
	@GetMapping("/like")
	public ResponseEntity<?> getMyLikeList(HttpSession session){
		long memberId = -1;
		if (session.getAttribute("memberId") != null)
			memberId = (Long)session.getAttribute("memberId");
		// 로그인하지 않은 경우 BAD REQUEST로 돌림
		if (memberId == -1)
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		
		List<ExerciseVideo> videos = videoService.getVideoByLike(memberId);
		if (videos == null)
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		else
			return new ResponseEntity<List<ExerciseVideo>>(videos, HttpStatus.OK);
			
	}
	
	@GetMapping("/like/{memberId}")
	public ResponseEntity<?> getMemberLikeList(@PathVariable long memberId, HttpSession session){
		long curId = -1;
		if (session.getAttribute("memberId") != null)
			curId = (Long)session.getAttribute("memberId");
		
		List<ExerciseVideo> videos = videoService.getVideoByLike(memberId);
		if (videos == null)
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		else
			return new ResponseEntity<List<ExerciseVideo>>(videos, HttpStatus.OK);
	}
}
