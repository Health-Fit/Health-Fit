package com.heemin.ws.model.dto.member;

import java.util.List;

public class SignupInfo {

    private List<Integer> categories;
    private String nickname;

    public List<Integer> getCategories() {
        return categories;
    }

    public void setCategories(List<Integer> categories) {
        this.categories = categories;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
