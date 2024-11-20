package com.heemin.ws.model.dto.video;

import com.heemin.ws.model.dto.category.ExerciseCategory;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ExerciseVideo {
    private long id;
    private String title;
    private String url;
    private long viewCnt;
    private long reviewCnt;
    private LocalDateTime regDate;
    private boolean deleted;
    private boolean liked;		// 좋아요 표시 여부
    private boolean blocked;	// 싫어요 표시 여부
    private List<ExerciseCategory> categories;

    public ExerciseVideo() {

    }

    public ExerciseVideo(String title, String url, List<ExerciseCategory> categories) {
        this.title = title;
        this.url = url;
        this.categories = categories;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getViewCnt() {
        return viewCnt;
    }

    public void setViewCnt(long viewCnt) {
        this.viewCnt = viewCnt;
    }

    public long getReviewCnt() {
        return reviewCnt;
    }

    public void setReviewCnt(long reviewCnt) {
        this.reviewCnt = reviewCnt;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public List<ExerciseCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<ExerciseCategory> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Video [id=" + id + ", title=" + title + ", url=" + url + ", viewCnt=" + viewCnt + ", reviewCnt="
                + reviewCnt + ", regDate=" + regDate + ", deleted=" + deleted + ", liked=" + liked + ", categories="
                + categories + "]";
    }
}

