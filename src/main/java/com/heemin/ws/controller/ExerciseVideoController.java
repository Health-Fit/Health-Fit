package com.heemin.ws.controller;

import com.heemin.ws.model.service.ExerciseVideoService;
import org.springframework.stereotype.Controller;

@Controller
public class ExerciseVideoController {

    private ExerciseVideoService exerciseVideoService;

    public ExerciseVideoController(ExerciseVideoService exerciseVideoService){
        this.exerciseVideoService = exerciseVideoService;
    }
}
