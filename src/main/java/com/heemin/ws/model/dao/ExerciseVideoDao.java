package com.heemin.ws.model.dao;

import com.heemin.ws.model.dto.video.ExerciseVideo;
import java.util.List;

public interface ExerciseVideoDao {

    List<ExerciseVideo> selectExample();
}
