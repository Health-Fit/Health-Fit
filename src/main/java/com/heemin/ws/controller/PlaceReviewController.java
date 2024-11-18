package com.ssafy.ssafit.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.heemin.ws.model.dto.PlaceReview;
import com.heemin.ws.model.service.PlaceReviewService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/reviews/place")
public class PlaceReviewController {
	PlaceReviewService placeReviewService;
	
	public PlaceReviewController(PlaceReviewService placeReviewService) {
		this.placeReviewService = placeReviewService;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getByPlaceId(HttpSession session, long id){
		
		List<PlaceReview> reviews = placeReviewService.getById(id);
		if (reviews == null || reviews.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<List<PlaceReview>>(reviews, HttpStatus.OK);
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<?> add(@RequestBody PlaceReview placeReview){
		if (placeReviewService.addReview(placeReview))
			return new ResponseEntity<Void>(HttpStatus.OK);
		return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
