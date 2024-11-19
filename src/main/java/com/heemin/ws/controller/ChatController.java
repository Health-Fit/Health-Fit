package com.heemin.ws.controller;

import com.heemin.ws.model.dto.Response;
import com.heemin.ws.model.service.ChatService;
import com.heemin.ws.support.Auth;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RequestMapping("/api/chats")
@RestController
public class ChatController {

    private final ChatService chatService;
    private final long TIMEOUT_VALUE = 10000; // 10초
    private final Map<DeferredResult<ResponseEntity>, Long> chatRequests = new ConcurrentHashMap<>(); // {채팅 결과 : 비디오Id}

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/{videoId}")
    public DeferredResult<ResponseEntity> read(@PathVariable long videoId, @RequestParam LocalDateTime time) {
        DeferredResult<ResponseEntity> deferredResult = new DeferredResult<>(TIMEOUT_VALUE,
                ResponseEntity.status(HttpStatus.OK).body(List.of()));

        chatRequests.put(deferredResult, videoId);

        // 콜백 함수 지정 (time out과 상관없이 실행)
        deferredResult.onCompletion(() -> chatRequests.remove(deferredResult));

        Response chats = chatService.getChats(videoId, time);

        if (chats.hasData()) { // 새로운 채팅 있으면, 결과 담아서 바로 반환
            deferredResult.setResult(chats.getResponse());
        }

        return deferredResult;
    }
}
