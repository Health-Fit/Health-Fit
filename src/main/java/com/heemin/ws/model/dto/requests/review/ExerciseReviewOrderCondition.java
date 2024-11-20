package com.heemin.ws.model.dto.requests.review;

public class ExerciseReviewOrderCondition {
    private String orderBy;
    private String orderDir;

    public ExerciseReviewOrderCondition() {
    }

    public ExerciseReviewOrderCondition(String orderBy, String orderDir) {
        this.orderBy = orderBy;
        this.orderDir = orderDir;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderDir() {
        return orderDir;
    }

    public void setOrderDir(String orderDir) {
        this.orderDir = orderDir;
    }

    @Override
    public String toString() {
        return "ExerciseReviewOrderCondition{" +
                "orderBy='" + orderBy + '\'' +
                ", orderDir='" + orderDir + '\'' +
                '}';
    }
}
