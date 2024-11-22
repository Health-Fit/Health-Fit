package com.heemin.ws.model.dto.chat;

import java.time.LocalDateTime;

public class Chat {

    private long id;
    private long exerciseVideoId;
    private long memberId;
    private String nickname;
    private String message;
    private LocalDateTime sentAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }
}
