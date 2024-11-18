package com.heemin.ws.model.dao;

import java.util.List;

import com.heemin.ws.model.dto.ExerciseCategory;

public interface CategoryDao {
	List<ExerciseCategory> selectAll();
	List<ExerciseCategory> selectExerciseCategoryByVideoId(long videoId);
}
