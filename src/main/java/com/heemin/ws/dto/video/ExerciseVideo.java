package com.heemin.ws.dto.video;

import com.heemin.ws.dto.category.ExerciseCategory;
import java.util.Date;
import java.util.List;

public class ExerciseVideo {
    private long id;
    private String url;
    private long viewCnt;
    private long reviewCnt;
    private Date regDate;
    private boolean deleted;
    private List<ExerciseCategory> categories;

    public List<ExerciseCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<ExerciseCategory> categories) {
        this.categories = categories;
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

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
