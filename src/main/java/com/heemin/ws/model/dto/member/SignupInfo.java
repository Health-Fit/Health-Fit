package com.heemin.ws.model.dto.member;

import java.util.List;

public class SignupInfo {

    private List<Integer> categoryIds;
    private String nickname;

    public List<Integer> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Integer> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
