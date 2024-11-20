package com.heemin.ws.model.service;

import com.heemin.ws.model.dao.ChatDao;
import com.heemin.ws.model.dto.Response;
import com.heemin.ws.model.dto.chat.Chat;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatDao chatDao;

    public ChatService(ChatDao chatDao) {
        this.chatDao = chatDao;
    }

    public Response read(long videoId, LocalDateTime time) {
        List<Chat> chatList = chatDao.selectByExerciseVideoId(videoId, time);
        if (chatList.size() > 0) {
            return new Response(chatDao.selectByExerciseVideoId(videoId, time), 200);
        }
        return new Response(200);
    }

    public Response send(long memberId, long videoId, Chat chat) {
        chat.setMemberId(memberId);
        chat.setExerciseVideoId(videoId);
        chatDao.insert(chat);

        return new Response(201);
    }
}
