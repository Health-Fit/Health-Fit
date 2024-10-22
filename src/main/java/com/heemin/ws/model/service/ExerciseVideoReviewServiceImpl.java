package com.heemin.ws.model.service;

import com.heemin.ws.model.dao.ExerciseVideoReviewDao;
import org.springframework.stereotype.Service;

@Service
public class ExerciseVideoReviewServiceImpl implements ExerciseVideoReviewService {
    private ExerciseVideoReviewDao exerciseVideoReviewDao;

    public ExerciseVideoReviewServiceImpl(ExerciseVideoReviewDao exerciseVideoReviewDao) {
        this.exerciseVideoReviewDao = exerciseVideoReviewDao;
    }
}
