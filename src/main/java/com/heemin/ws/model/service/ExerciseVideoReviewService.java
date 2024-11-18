package com.heemin.ws.model.service;

import com.heemin.ws.model.dao.ExerciseVideoReviewDao;
import org.springframework.stereotype.Service;

@Service
public class ExerciseVideoReviewService {
    private ExerciseVideoReviewDao exerciseVideoReviewDao;

    public ExerciseVideoReviewService(ExerciseVideoReviewDao exerciseVideoReviewDao) {
        this.exerciseVideoReviewDao = exerciseVideoReviewDao;
    }
}
