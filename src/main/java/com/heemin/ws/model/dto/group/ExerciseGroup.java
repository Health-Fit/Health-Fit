package com.heemin.ws.model.dto.group;

import java.time.LocalDateTime;
import java.util.List;

public class ExerciseGroup {
    private long id;
    private String name;
    private float lat;
    private float lon;
    private long leaderMemberId;
    private long exerciseCategoryId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int maxMember;
    private String descript;
    private LocalDateTime regDate;
    private List<Long> memberIds; // 현재 어떤 유저들이 멤버로 존재하는지

    public ExerciseGroup() {
    }

    public ExerciseGroup(long id, String name, float lat, float lon, long leaderMemberId, long exerciseCategoryId, LocalDateTime startDate, LocalDateTime endDate, int maxMember, String descript, LocalDateTime regDate) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.leaderMemberId = leaderMemberId;
        this.exerciseCategoryId = exerciseCategoryId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxMember = maxMember;
        this.descript = descript;
        this.regDate = regDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public long getLeaderMemberId() {
        return leaderMemberId;
    }

    public void setLeaderMemberId(long leaderMemberId) {
        this.leaderMemberId = leaderMemberId;
    }

    public long getExerciseCategoryId() {
        return exerciseCategoryId;
    }

    public void setExerciseCategoryId(long exerciseCategoryId) {
        this.exerciseCategoryId = exerciseCategoryId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public int getMaxMember() {
        return maxMember;
    }

    public void setMaxMember(int maxMember) {
        this.maxMember = maxMember;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    public List<Long> getMemberIds() {
        return memberIds;
    }

    public void setMemberIds(List<Long> memberIds) {
        this.memberIds = memberIds;
    }

    @Override
    public String toString() {
        return "ExerciseGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                ", leaderMemberId=" + leaderMemberId +
                ", exerciseCategoryId=" + exerciseCategoryId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", maxMembr=" + maxMember +
                ", descript='" + descript + '\'' +
                ", regDate=" + regDate +
                '}';
    }
}
