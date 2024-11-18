package com.heemin.ws.model.dao;

import com.heemin.ws.model.dto.category.ExerciseCategory;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CategoryDao {
	List<ExerciseCategory> selectAll();
	List<ExerciseCategory> selectExerciseCategoryByVideoId(long videoId);
	int insertMemberCategory(@Param("member") long memberId, @Param("categories") List<Integer> categories);
}
