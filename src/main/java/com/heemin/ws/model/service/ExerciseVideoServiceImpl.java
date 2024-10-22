package com.heemin.ws.model.service;

import com.heemin.ws.model.dao.ExerciseVideoDao;
import org.springframework.stereotype.Service;

@Service
public class ExerciseVideoServiceImpl implements ExerciseVideoService {
    private ExerciseVideoDao exerciseVideoDao;

    public ExerciseVideoServiceImpl(ExerciseVideoDao exerciseVideoDao) {
        this.exerciseVideoDao = exerciseVideoDao;
    }
}
