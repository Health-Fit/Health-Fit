package com.heemin.ws.model.dto.requests.group;

import java.time.LocalDateTime;

public class GroupSearchCondition {
    private long categoryId;
    private LocalDateTime time;
    private float left;
    private float right;
    private float bottom;
    private float top;

    public GroupSearchCondition() {
    }

    public GroupSearchCondition(long categoryId, LocalDateTime time, float left, float right, float bottom, float top) {
        this.categoryId = categoryId;
        this.time = time;
        this.left = left;
        this.right = right;
        this.bottom = bottom;
        this.top = top;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public float getLeft() {
        return left;
    }

    public void setLeft(float left) {
        this.left = left;
    }

    public float getRight() {
        return right;
    }

    public void setRight(float right) {
        this.right = right;
    }

    public float getBottom() {
        return bottom;
    }

    public void setBottom(float bottom) {
        this.bottom = bottom;
    }

    public float getTop() {
        return top;
    }

    public void setTop(float top) {
        this.top = top;
    }

    @Override
    public String toString() {
        return "GroupSearchCondition{" +
                "categoryId=" + categoryId +
                ", time=" + time +
                ", left=" + left +
                ", right=" + right +
                ", bottom=" + bottom +
                ", top=" + top +
                '}';
    }
}
