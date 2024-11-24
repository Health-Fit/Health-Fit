package com.heemin.ws.model.dao;

import com.heemin.ws.model.dto.chat.Chat;
import com.heemin.ws.model.dto.chat.GroupChat;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChatDao {
    int insert(Chat chat);
    List<Chat> selectByExerciseVideoId(@Param("exerciseVideoId") long exerciseVideoId,
                                       @Param("time") LocalDateTime time);

    int insertGroupChat(GroupChat chat);
    List<GroupChat> selectByGroupId(@Param("groupId") long groupId,
                                    @Param("idx") long idx);
}
