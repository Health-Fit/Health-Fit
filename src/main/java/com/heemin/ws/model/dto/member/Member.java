package com.heemin.ws.model.dto.member;

import java.time.LocalDate;

public class Member {
    private long id;
    private String email;
    private String password;

    private String name;
    private String gender;
    private String profileImg;
    private LocalDate birth;
    private String tel;
    private boolean deleted;
    private LocalDate deleteDate;
    private LocalDate regDate;
    private int memberAuthId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public LocalDate getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(LocalDate deleteDate) {
        this.deleteDate = deleteDate;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public int getMemberAuthId() {
        return memberAuthId;
    }

    public void setMemberAuthId(int memberAuthId) {
        this.memberAuthId = memberAuthId;
    }
}
