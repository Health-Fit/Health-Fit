package com.heemin.ws.model.dto.auth;

import com.heemin.ws.model.dto.video.ExerciseVideo;
import java.util.List;

public class LoginResponse {

    private Jwt jwt;
    private List<ExerciseVideo> videos;

    public LoginResponse() {
    }

    public LoginResponse(Jwt jwt, List<ExerciseVideo> videos) {
        this.jwt = jwt;
        this.videos = videos;
    }

    public Jwt getJwt() {
        return jwt;
    }

    public void setJwt(Jwt jwt) {
        this.jwt = jwt;
    }

    public List<ExerciseVideo> getVideos() {
        return videos;
    }

    public void setVideos(List<ExerciseVideo> videos) {
        this.videos = videos;
    }
}
