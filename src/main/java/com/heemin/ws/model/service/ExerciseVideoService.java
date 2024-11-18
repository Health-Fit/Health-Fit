package com.heemin.ws.model.service;

import com.heemin.ws.model.dao.ExerciseVideoDao;
import org.springframework.stereotype.Service;

@Service
public class ExerciseVideoService {
    private ExerciseVideoDao exerciseVideoDao;

    public ExerciseVideoService(ExerciseVideoDao exerciseVideoDao) {
        this.exerciseVideoDao = exerciseVideoDao;
    }
}
