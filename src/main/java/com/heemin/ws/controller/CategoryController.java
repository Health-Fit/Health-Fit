package com.heemin.ws.controller;

import com.heemin.ws.model.dto.category.ExerciseCategory;
import com.heemin.ws.model.service.CategoryService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/categories")
public class CategoryController {
	
	CategoryService categoryService;
	
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@GetMapping("")
	public ResponseEntity<?> getCategoryList(){
		List<ExerciseCategory> categories = categoryService.getCategoryList();
		if (categories == null)
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		else
			return new ResponseEntity<List<ExerciseCategory>>(categories, HttpStatus.OK);
	}
}
