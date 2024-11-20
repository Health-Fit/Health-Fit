package com.heemin.ws.model.dao;

import com.heemin.ws.model.dto.chat.Chat;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChatDao {
    int insert(Chat chat);
    List<Chat> selectByExerciseVideoId(@Param("exerciseVideoId") long exerciseVideoId,
                                       @Param("time") LocalDateTime time);
}
