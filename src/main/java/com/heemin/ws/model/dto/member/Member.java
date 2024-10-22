package com.heemin.ws.model.dto.member;

import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Member {
    private long id;
    private String email;
    private String password;

    private String name;
    private String gender;
    private String profileImg;
    private Date birth;
    private boolean deleted;
    private Date deleteDate;
    private Date regDate;
    private int memberAuth;

    public long getId() {
        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        if (birth == null)
            return;
        this.birth = birth;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        if (deleteDate == null)
            return;
        this.deleteDate = deleteDate;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        if (regDate == null)
            return;
        this.regDate = regDate;
    }

    public int getMemberAuth() {
        return memberAuth;
    }

    public void setMemberAuth(int memberAuth) {
        this.memberAuth = memberAuth;
    }
}
