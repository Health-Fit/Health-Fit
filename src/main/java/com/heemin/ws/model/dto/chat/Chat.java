package com.heemin.ws.model.dto.chat;

import java.time.LocalDateTime;

public class Chat {

    private long id;
    private long exerciseVideoId;
    private long memberId;
    private String message;
    private LocalDateTime sendAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getExerciseVideoId() {
        return exerciseVideoId;
    }

    public void setExerciseVideoId(long exerciseVideoId) {
        this.exerciseVideoId = exerciseVideoId;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getSendAt() {
        return sendAt;
    }

    public void setSendAt(LocalDateTime sendAt) {
        this.sendAt = sendAt;
    }
}
