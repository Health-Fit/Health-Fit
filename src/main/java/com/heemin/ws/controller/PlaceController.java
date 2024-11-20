package com.heemin.ws.controller;

import com.heemin.ws.model.dto.place.Place;
import com.heemin.ws.model.dto.requests.place.PlaceBlock;
import com.heemin.ws.model.dto.requests.place.PlaceLike;
import com.heemin.ws.model.service.PlaceService;
import com.heemin.ws.support.Auth;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import static com.heemin.ws.controller.MemberManager.getMemberId;

@RestController
@RequestMapping("/api/places")
public class PlaceController {
	PlaceService placeService;
	
	public PlaceController(PlaceService placeService) {
		this.placeService = placeService;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable long id, NativeWebRequest webRequest){
		long memberId = getMemberId(webRequest);
		Place place = placeService.getById(id);
		if (place == null)
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<Place>(place, HttpStatus.OK);
	}
	
	@PutMapping("/like")
	public ResponseEntity<?> like(@Auth Long memberId, @RequestBody PlaceLike placeLike){
		// 로그인하지 않은 경우 BAD REQUEST로 돌림
		if (memberId == null)
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    	
    	if (placeService.setLike(memberId, placeLike.getPlaceId(), placeLike.isLike()))
    		return new ResponseEntity<Void>(HttpStatus.OK);
    	else
    		return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PutMapping("/block")
    public ResponseEntity<?> block(@Auth Long memberId, @RequestBody PlaceBlock placeBlock){
		// 로그인하지 않은 경우 BAD REQUEST로 돌림
		if (memberId == null)
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    	
    	if (placeService.setBlock(memberId, placeBlock.getPlaceId(), placeBlock.isBlock()))
    		return new ResponseEntity<Void>(HttpStatus.OK);
    	else
    		return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
