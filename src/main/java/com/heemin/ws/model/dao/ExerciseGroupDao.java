package com.heemin.ws.model.dao;

import com.heemin.ws.model.dto.group.ExerciseGroup;
import com.heemin.ws.model.dto.requests.group.GroupSearchCondition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExerciseGroupDao {
    List<ExerciseGroup> selectByCondition(GroupSearchCondition condition);
    List<ExerciseGroup> selectByMember(long memberId);
    ExerciseGroup selectById(long id);
    Long selectGroupMemberCnt(long id);
    List<Long> selectGroupMembers(long groupId);

    int insert(ExerciseGroup exerciseGroup);
    int insertMember(@Param("memberId") long memberId, @Param("groupId") long groupId);
}
