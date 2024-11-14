package com.heemin.ws.model.dto.video;

import com.heemin.ws.model.dto.category.ExerciseCategory;
import java.time.LocalDate;
import java.util.List;

public class ExerciseVideo {
    private long id;
    private String title;
    private String url;
    private long viewCnt;
    private long reviewCnt;
    private LocalDate regDate;
    private boolean deleted;
    private List<ExerciseCategory> categories;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public List<ExerciseCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<ExerciseCategory> categories) {
        this.categories = categories;
    }
}
