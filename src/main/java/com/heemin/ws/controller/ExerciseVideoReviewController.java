package com.heemin.ws.controller;

import com.heemin.ws.model.service.ExerciseVideoReviewService;
import org.springframework.stereotype.Controller;

@Controller
public class ExerciseVideoReviewController {
    private ExerciseVideoReviewService exerciseVideoReviewService;

    public ExerciseVideoReviewController(ExerciseVideoReviewService exerciseVideoReviewService) {
        this.exerciseVideoReviewService = exerciseVideoReviewService;
    }
}
