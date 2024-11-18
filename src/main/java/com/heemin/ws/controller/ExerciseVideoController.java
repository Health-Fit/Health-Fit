package com.heemin.ws.controller;

import com.heemin.ws.model.service.ExerciseVideoService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExerciseVideoController {

    private ExerciseVideoService exerciseVideoService;

    public ExerciseVideoController(ExerciseVideoService exerciseVideoService){
        this.exerciseVideoService = exerciseVideoService;
    }
}
