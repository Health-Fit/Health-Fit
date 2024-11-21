package com.heemin.ws.model.service;

import com.heemin.ws.model.dao.ExerciseGroupDao;
import com.heemin.ws.model.dto.group.ExerciseGroup;
import com.heemin.ws.model.dto.requests.group.GroupSearchCondition;
import com.heemin.ws.model.dto.review.ExerciseVideoReview;
import com.heemin.ws.model.dto.video.ExerciseVideo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseGroupService {
    ExerciseGroupDao exerciseGroupDao;

    public ExerciseGroupService(ExerciseGroupDao exerciseGroupDao) {
        this.exerciseGroupDao = exerciseGroupDao;
    }

    public List<ExerciseGroup> getListByCondition(GroupSearchCondition groupSearchCondition) {
        List<ExerciseGroup> groups = exerciseGroupDao.selectByCondition(groupSearchCondition);
        insertSubInfos(groups);
        return groups;
    }

    public List<ExerciseGroup> getByMemberId(long memberId) {
        List<ExerciseGroup> groups = exerciseGroupDao.selectByMember(memberId);
        insertSubInfos(groups);
        return groups;
    }

    public boolean addGroup(ExerciseGroup exerciseGroup) {
        return exerciseGroupDao.insert(exerciseGroup) == 1;
    }

    public boolean joinGroup(long memberId, long groupId) {
        ExerciseGroup group = exerciseGroupDao.selectById(groupId);
        // MaxMember를 넘지 않은 경우 추가가 가능하도록 설정
        if (group.getMaxMember() > exerciseGroupDao.selectGroupMemberCnt(groupId))
            return exerciseGroupDao.insertMember(memberId, groupId) == 1;
        // 참가에 실패한 경우 false로 설정
        return false;
    }

    // 기본 추가 정보 입력 (현재 그룹에 속한 멤버 id 입력)
    public void insertSubInfos(List<ExerciseGroup> groups) {
        insertMembers(groups);
    }

    public void insertSubInfos(ExerciseGroup group) {
        insertMembers(group);
    }

    public void insertMembers(List<ExerciseGroup> groups) {
        for (ExerciseGroup group : groups) {
            List<Long> members = exerciseGroupDao.selectGroupMembers(group.getId());
            group.setMemberIds(members);
        }
    }

    public void insertMembers(ExerciseGroup group) {
        List<Long> members = exerciseGroupDao.selectGroupMembers(group.getId());
        group.setMemberIds(members);
    }
}
