package com.heemin.ws.controller;

import static com.heemin.ws.controller.MemberManager.getMemberId;

import com.heemin.ws.model.dto.Response;
import com.heemin.ws.model.dto.chat.GroupChat;
import com.heemin.ws.model.dto.group.ExerciseGroup;
import com.heemin.ws.model.dto.requests.group.GroupSearchCondition;
import com.heemin.ws.model.service.ChatService;
import com.heemin.ws.model.service.ExerciseGroupService;
import java.util.List;
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
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
@RequestMapping("/api/groups")
public class ExerciseGroupController {
    private final ExerciseGroupService exerciseGroupService;
    private final ChatService chatService;
    private final long TIMEOUT_VALUE = 10000; // 10초
    private final Map<DeferredResult<ResponseEntity>, Data> chatRequests = new ConcurrentHashMap<>();

    public ExerciseGroupController(ExerciseGroupService exerciseGroupService, ChatService chatService) {
        this.exerciseGroupService = exerciseGroupService;
        this.chatService = chatService;
    }

    // 조건으로 그룹 리스트 검색하기
    @PostMapping("")
    public ResponseEntity<?> getListByCondition(@RequestBody GroupSearchCondition groupSearchCondition) {
        List<ExerciseGroup> groups = exerciseGroupService.getListByCondition(groupSearchCondition);
        if (groups == null || groups.isEmpty()) {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<ExerciseGroup>>(groups, HttpStatus.OK);
    }

    // 입력받은 정보를 토대로 새로운 그룹 생성하기
    @PostMapping("/add")
    public ResponseEntity<?> addGroup(@RequestBody ExerciseGroup exerciseGroup, NativeWebRequest webRequest) {
        System.out.println(exerciseGroup);
        long memberId = getMemberId(webRequest);
        // 로그인 되지 않은 경우 그룹을 생성하지 못하게 막기
        if (memberId == -1) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }

        // 현재 로그인된 아이디가 리더 멤버가 될 수 있도록 설정
        exerciseGroup.setLeaderMemberId(memberId);
        if (exerciseGroupService.addGroup(exerciseGroup)) {
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 그룹 아이디에 해당하는 그룹에 현재 로그인된 아이디의 멤버 추가하기
    @PostMapping("/{groupId}")
    public ResponseEntity<?> joinGroup(@PathVariable long groupId, NativeWebRequest webRequest) {
        long memberId = getMemberId(webRequest);
        if (memberId == -1) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }

        // 현재 로그인된 아이디가 해당 그룹에 속할 수 있도록 설정
        if (exerciseGroupService.joinGroup(memberId, groupId)) {
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 현재 내가 속해있는 그룹 조회
    @GetMapping("")
    public ResponseEntity<?> getListMyGroup(NativeWebRequest webRequest) {
        long memberId = getMemberId(webRequest);
        // 현재 로그인이 되어있지 않으면 내가 속해있는 그룹을 볼 수 없다.
        if (memberId == -1) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }

        List<ExerciseGroup> groups = exerciseGroupService.getByMemberId(memberId);
        if (groups == null || groups.isEmpty()) {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<ExerciseGroup>>(groups, HttpStatus.OK);
    }

    // 그룹 별 채팅 조회
    @GetMapping("/chats/{groupId}")
    public DeferredResult<ResponseEntity> readChats(@PathVariable long groupId,
                                       @RequestParam(required = false, defaultValue = "0") long idx) {
        DeferredResult<ResponseEntity> deferredResult = new DeferredResult<>(TIMEOUT_VALUE,
                ResponseEntity.status(HttpStatus.NO_CONTENT).body("타임 아웃 : 새로운 채팅 내역 없음"));

        chatRequests.put(deferredResult, new Data(groupId, idx));

        // 콜백 함수 지정 (time out과 상관없이 실행)
        deferredResult.onCompletion(() -> chatRequests.remove(deferredResult));

        Response chats = chatService.readByGroupId(groupId, idx);

        if (chats.hasData()) { // 새로운 채팅 있으면, 결과 담아서 바로 반환
            deferredResult.setResult(chats.getResponse());
        }

        return deferredResult;
    }

    // 그룹 별 채팅 전송
    @PostMapping("/chats/{groupId}")
    public ResponseEntity<?> sendChat(@RequestBody GroupChat chat, @PathVariable long groupId, NativeWebRequest nativeWebRequest) {
        long memberId = MemberManager.getMemberId(nativeWebRequest);

        chatService.sendByGroupId(memberId, groupId, chat);

        // 해당 그룹 채팅에 새 채팅이 올라오면, 대기하고 있는 deferredResult 반환시키기
        for (var entry : chatRequests.entrySet()) {
            if (entry.getValue().groupId == groupId) {
                Response chats = chatService.readByGroupId(groupId, entry.getValue().getIdx());
                entry.getKey().setResult(chats.getResponse());
            }
        }
        return ResponseEntity.ok().build();
    }

    private static class Data {
        private long groupId;
        private long idx;

        public Data() {
        }

        public Data(long groupId, long idx) {
            this.groupId = groupId;
            this.idx = idx;
        }

        public long getGroupId() {
            return groupId;
        }

        public void setGroupId(long groupId) {
            this.groupId = groupId;
        }

        public long getIdx() {
            return idx;
        }

        public void setIdx(long idx) {
            this.idx = idx;
        }
    }
}
