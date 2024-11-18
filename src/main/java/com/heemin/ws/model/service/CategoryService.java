package com.heemin.ws.model.service;

import com.heemin.ws.model.dao.CategoryDao;
import com.heemin.ws.model.dto.category.ExerciseCategory;
import java.util.List;
import org.springframework.stereotype.Service;

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
