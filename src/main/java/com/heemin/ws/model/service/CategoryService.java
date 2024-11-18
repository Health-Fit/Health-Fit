package com.ssafy.ssafit.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.CategoryDao;
import com.ssafy.ssafit.model.dto.category.ExerciseCategory;

@Service
public class CategoryService {
	CategoryDao categoryDao;
	
	public CategoryService(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	
	public List<ExerciseCategory> getCategoryList(){
		return categoryDao.selectAll();
	}
}
