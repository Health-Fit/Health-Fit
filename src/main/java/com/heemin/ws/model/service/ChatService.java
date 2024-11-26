package com.heemin.ws.model.service;

import com.heemin.ws.model.dao.ChatDao;
import com.heemin.ws.model.dto.Response;
import com.heemin.ws.model.dto.chat.Chat;
import com.heemin.ws.model.dto.chat.GroupChat;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatDao chatDao;

    public ChatService(ChatDao chatDao) {
        this.chatDao = chatDao;
    }

    public Response readByVideoID(long videoId, LocalDateTime time) {
        List<Chat> chatList = chatDao.selectByExerciseVideoId(videoId, time);
        if (chatList.size() > 0) {
            return new Response(chatList, 200);
        }
        return new Response(200);
    }

    public Response sendByVideoId(long memberId, long videoId, Chat chat) {
        chat.setMemberId(memberId);
        chat.setExerciseVideoId(videoId);
        chatDao.insert(chat);

        return new Response(201);
    }

    public Response readByGroupId(long groupId, long idx) {
        List<GroupChat> chatList = chatDao.selectByGroupId(groupId, idx);
        if (chatList.size() > 0) {
            return new Response(chatList, 200);
        }
        return new Response(200);
    }

    public Response sendByGroupId(long memberId, long groupId, GroupChat groupChat) {
        groupChat.setGroupId(groupId);
        groupChat.setMemberId(memberId);
        groupChat.setSentAt(LocalDateTime.now());
        chatDao.insertGroupChat(groupChat);

        return new Response(201);
    }
}
