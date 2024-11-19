package com.heemin.ws.controller;

import com.heemin.ws.model.dto.Response;
import com.heemin.ws.model.dto.chat.Chat;
import com.heemin.ws.model.service.ChatService;
import com.heemin.ws.support.Auth;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RequestMapping("/api/chats")
@RestController
public class ChatController {

    private final ChatService chatService;
    private final long TIMEOUT_VALUE = 10000; // 10초
    private final Map<DeferredResult<ResponseEntity>, Data> chatRequests = new ConcurrentHashMap<>(); // {채팅 결과 : 비디오Id}

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/{videoId}")
    public DeferredResult<ResponseEntity> read(@PathVariable long videoId, @RequestParam LocalDateTime time) {
        DeferredResult<ResponseEntity> deferredResult = new DeferredResult<>(TIMEOUT_VALUE,
                ResponseEntity.status(HttpStatus.NO_CONTENT).body("타임 아웃 : 새로운 채팅 내역 없음"));

        chatRequests.put(deferredResult, new Data(videoId, time));

        // 콜백 함수 지정 (time out과 상관없이 실행)
        deferredResult.onCompletion(() -> chatRequests.remove(deferredResult));

        Response chats = chatService.read(videoId, time);

        if (chats.hasData()) { // 새로운 채팅 있으면, 결과 담아서 바로 반환
            deferredResult.setResult(chats.getResponse());
        }

        return deferredResult;
    }

    @PostMapping("/{videoId}")
    public ResponseEntity<?> send(@RequestBody Chat chat, @PathVariable long videoId,
                                  @Auth Long memberId) {

        chatService.send(memberId, videoId, chat);

        // 해당 운동 영상에 채팅이 올라오면, 대기하고 있는 deferredResult 반환시키기
        for (var entry : chatRequests.entrySet()) {
            if (entry.getValue().getVideoId() == videoId) {
                Response chats = chatService.read(videoId, entry.getValue().getTime());
                entry.getKey().setResult(chats.getResponse());
            }
        }
        return ResponseEntity.ok().build();
    }

    private static class Data {
        private long videoId;
        private LocalDateTime time;

        public Data() {
        }

        public Data(long videoId, LocalDateTime time) {
            this.videoId = videoId;
            this.time = time;
        }

        public long getVideoId() {
            return videoId;
        }

        public void setVideoId(long videoId) {
            this.videoId = videoId;
        }

        public LocalDateTime getTime() {
            return time;
        }

        public void setTime(LocalDateTime time) {
            this.time = time;
        }
    }
}
