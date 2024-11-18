package com.heemin.ws.model.dao;

import com.heemin.ws.model.dto.category.ExerciseCategory;
import java.util.List;

public interface CategoryDao {
	List<ExerciseCategory> selectAll();
	List<ExerciseCategory> selectExerciseCategoryByVideoId(long videoId);
}
