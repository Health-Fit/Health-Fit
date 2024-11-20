package com.heemin.ws.controller;

import com.heemin.ws.model.dto.Response;
import com.heemin.ws.model.dto.SearchCondition;
import com.heemin.ws.model.dto.requests.review.ExerciseReviewBlock;
import com.heemin.ws.model.dto.requests.review.ExerciseReviewLike;
import com.heemin.ws.model.dto.requests.review.ExerciseReviewOrderCondition;
import com.heemin.ws.model.dto.review.ExerciseVideoReview;
import com.heemin.ws.model.service.ExerciseVideoReviewService;
import com.heemin.ws.support.Auth;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

import java.lang.annotation.Native;
import java.util.List;

import static com.heemin.ws.controller.MemberManager.getMemberId;

@RestController
@RequestMapping("/api/reviews")
public class ExerciseVideoReviewController {

    private final ExerciseVideoReviewService reviewService;

    public ExerciseVideoReviewController(ExerciseVideoReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // 리뷰 생성하기 기능
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody ExerciseVideoReview review, NativeWebRequest webRequest) {
        long memberId = getMemberId(webRequest);
        if (memberId == -1)
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

        // 추가한 멤버의 아이디를 현재 로그인한 멤버로 저장
        review.setMemberId(memberId);
        System.out.println(review);
        reviewService.add(review);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    // 리뷰 삭제하기 기능
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") long id, NativeWebRequest webRequest) {
        // 삭제를 위해서 현재 로그인한 유저와 리뷰 작성유저가 동일한지 확인
        long memberId = getMemberId(webRequest);
        if (memberId == -1)
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

        reviewService.remove(id, memberId);
        return ResponseEntity.ok().build();
    }

    // 리뷰 수정하기 기능
    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody ExerciseVideoReview review, NativeWebRequest webRequest) {
        // 수정을 위해서 현재 로그인한 유저와 리뷰 작성유저가 동일한지 확인
        long memberId = getMemberId(webRequest);
        if (memberId == -1)
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

        reviewService.update(review, memberId);
        return ResponseEntity.ok().build();
    }
    
    // 리뷰 좋아요 기능
    @PutMapping("/like")
    public ResponseEntity<?> like(@RequestBody ExerciseReviewLike exerciseReviewLike, NativeWebRequest webRequest){
        long memberId = getMemberId(webRequest);
		// 로그인하지 않은 경우 BAD REQUEST로 돌림
		if (memberId == -1)
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    	
    	if (reviewService.setReviewLike(memberId, exerciseReviewLike.getId(), exerciseReviewLike.isLike()))
    		return new ResponseEntity<Void>(HttpStatus.OK);
    	else
    		return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    // 리뷰 신고하기 기능
    @PutMapping("/block")
    public ResponseEntity<?> block(@RequestBody ExerciseReviewBlock exerciseReviewBlock, NativeWebRequest webRequest){
        long memberId = getMemberId(webRequest);
		// 로그인하지 않은 경우 BAD REQUEST로 돌림
		if (memberId == -1)
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    	
    	if (reviewService.setReviewBlock(memberId, exerciseReviewBlock.getId(), exerciseReviewBlock.isBlock()))
    		return new ResponseEntity<Void>(HttpStatus.OK);
    	else
    		return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    // 비디오 아이디로 리뷰 받아오기
    @PostMapping("/{videoId}")
    public ResponseEntity<?> getVideoReview(NativeWebRequest webRequest, @PathVariable long videoId, @RequestBody ExerciseReviewOrderCondition orderCondition){
        long memberId = getMemberId(webRequest);
    	List<ExerciseVideoReview> reviews = reviewService.getByVideoId(videoId, memberId, orderCondition);
    	if (reviews == null || reviews.isEmpty())
    		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    	return new ResponseEntity<List<ExerciseVideoReview>>(reviews, HttpStatus.OK);
    }
    
    
    @GetMapping("/{memberId}")
    public ResponseEntity<?> getMemberReview(@PathVariable("memberId") long id, NativeWebRequest webRequest){
        long memberId = getMemberId(webRequest);
    	List<ExerciseVideoReview> reviews = reviewService.getByMemberId(id, memberId);
    	if (reviews == null || reviews.isEmpty())
    		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    	return new ResponseEntity<List<ExerciseVideoReview>>(reviews, HttpStatus.OK);
    }
}
