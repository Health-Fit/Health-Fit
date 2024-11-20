package com.heemin.ws.controller;

import com.heemin.ws.model.dto.place.PlaceReview;
import com.heemin.ws.model.service.PlaceReviewService;
import com.heemin.ws.support.Auth;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

import static com.heemin.ws.controller.MemberManager.getMemberId;

@RestController
@RequestMapping("/api/reviews/place")
public class PlaceReviewController {
	PlaceReviewService placeReviewService;
	
	public PlaceReviewController(PlaceReviewService placeReviewService) {
		this.placeReviewService = placeReviewService;
	}

	// 장소리뷰 id로 상세 정보 받아오기
	@GetMapping("/{id}")
	public ResponseEntity<?> getByPlaceId(@PathVariable long id, NativeWebRequest webRequest){
		long memberId = getMemberId(webRequest);
		List<PlaceReview> reviews = placeReviewService.getById(id, memberId);
		if (reviews == null || reviews.isEmpty())
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<List<PlaceReview>>(reviews, HttpStatus.OK);
	}

	// 장소 리뷰 등록하기
	@PostMapping("/{id}")
	public ResponseEntity<?> add(@RequestBody PlaceReview placeReview, NativeWebRequest webRequest){
		long memberId = getMemberId(webRequest);
		// 현재 로그인된 정보가 존재하지 않는 경우 작성불가.
		if (memberId == -1)
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		// 해당 리뷰 작성자 등록
		placeReview.setMemberId(memberId);
		if (placeReviewService.addReview(placeReview))
			return new ResponseEntity<Void>(HttpStatus.OK);
		return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
