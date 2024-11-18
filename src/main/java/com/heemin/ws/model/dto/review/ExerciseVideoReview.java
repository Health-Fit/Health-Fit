package com.heemin.ws.model.dto.review;

import com.heemin.ws.model.dto.member.Member;
import com.heemin.ws.model.dto.video.ExerciseVideo;
import java.time.LocalDate;

public class ExerciseVideoReview {
    private long id;
    private Member member;
    private ExerciseVideo exerciseVideo;
    private String content;
    private long likeCnt;
    private long blockCnt;
    private LocalDate regDate;
    private LocalDate updateDate;
    private boolean deleted;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public ExerciseVideo getExerciseVideo() {
        return exerciseVideo;
    }

    public void setExerciseVideo(ExerciseVideo exerciseVideo) {
        this.exerciseVideo = exerciseVideo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getLikeCnt() {
        return likeCnt;
    }

    public void setLikeCnt(long likeCnt) {
        this.likeCnt = likeCnt;
    }

    public long getBlockCnt() {
        return blockCnt;
    }

    public void setBlockCnt(long blockCnt) {
        this.blockCnt = blockCnt;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
