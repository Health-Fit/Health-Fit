package com.heemin.ws.dto.review;

import com.heemin.ws.dto.member.Member;
import com.heemin.ws.dto.video.ExerciseVideo;
import java.util.Date;

public class ExerciseVideoReview {
    private long id;
    private Member member;
    private ExerciseVideo exerciseVideo;
    private String content;
    private long likeCnt;
    private long blockCnt;
    private Date regDate;
    private Date updateDate;
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

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
