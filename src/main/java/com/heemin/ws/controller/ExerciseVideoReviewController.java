package com.heemin.ws.controller;

import com.heemin.ws.model.dto.SearchCondition;
import com.heemin.ws.model.dto.requests.review.ExerciseReviewBlock;
import com.heemin.ws.model.dto.requests.review.ExerciseReviewLike;
import com.heemin.ws.model.dto.review.ExerciseVideoReview;
import com.heemin.ws.model.service.ExerciseVideoReviewService;
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
@RequestMapping("/api/reviews")
public class ExerciseVideoReviewController {

    private final ExerciseVideoReviewService reviewService;

    public ExerciseVideoReviewController(ExerciseVideoReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // 리뷰 생성하기 기능
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody ExerciseVideoReview review) {
        reviewService.add(review);
        
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 리뷰 삭제하기 기능
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") long id) {
        reviewService.remove(id);
        
        return ResponseEntity.ok().build();
    }

    // 리뷰 수정하기 기능
    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody ExerciseVideoReview review) {
        reviewService.update(review);
        
        return ResponseEntity.ok().build();
    }
    
    // 리뷰 좋아요 기능
    @PutMapping("/like")
    public ResponseEntity<?> like(HttpSession session, @RequestBody ExerciseReviewLike exerciseReviewLike){
    	long memberId = -1;
		if (session.getAttribute("memberId") != null)
			memberId = (Long)session.getAttribute("memberId");
		// 로그인하지 않은 경우 BAD REQUEST로 돌림
		if (memberId == -1)
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    	
    	if (reviewService.setReviewLike(memberId, exerciseReviewLike.getReviewId(), exerciseReviewLike.isLike()))
    		return new ResponseEntity<Void>(HttpStatus.OK);
    	else
    		return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    // 리뷰 신고하기 기능
    @PutMapping("/block")
    public ResponseEntity<?> block(HttpSession session, @RequestBody ExerciseReviewBlock exerciseReviewBlock){
    	long memberId = -1;
		if (session.getAttribute("memberId") != null)
			memberId = (Long)session.getAttribute("memberId");
		// 로그인하지 않은 경우 BAD REQUEST로 돌림
		if (memberId == -1)
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    	
    	if (reviewService.setReviewBlock(memberId, exerciseReviewBlock.getReviewId(), exerciseReviewBlock.isBlock()))
    		return new ResponseEntity<Void>(HttpStatus.OK);
    	else
    		return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    // 비디오 아이디로 리뷰 받아오기
    @PostMapping("/{videoId}")
    public ResponseEntity<?> getVideoReview(HttpSession session, @PathVariable long videoId, @RequestBody SearchCondition searchCondition){
    	long memberId = -1;
    	if (session.getAttribute("memberId") != null)
    		memberId = (Long)session.getAttribute("memberId");
    	
    	List<ExerciseVideoReview> reviews = reviewService.getByVideoId(videoId);
    	if (reviews == null || reviews.size() == 0)
    		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    	return new ResponseEntity<List<ExerciseVideoReview>>(reviews, HttpStatus.OK);
    }
    
    
    @GetMapping("/{memberId}")
    public ResponseEntity<?> getMemberReview(HttpSession session, @PathVariable long memberId){
//    	long memberId = -1;
    	if (session.getAttribute("memberId") != null)
    		memberId = (Long)session.getAttribute("memberId");
    	
    	List<ExerciseVideoReview> reviews = reviewService.getByMemberId(memberId);
    	if (reviews == null || reviews.size() == 0)
    		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    	return new ResponseEntity<List<ExerciseVideoReview>>(reviews, HttpStatus.OK);
    }
    
}
