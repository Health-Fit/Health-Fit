package com.heemin.ws.controller;

import com.heemin.ws.model.dto.group.ExerciseGroup;
import com.heemin.ws.model.dto.requests.group.GroupSearchCondition;
import com.heemin.ws.model.service.ExerciseGroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;

import static com.heemin.ws.controller.MemberManager.getMemberId;

@RestController
@RequestMapping("/api/groups")
public class ExerciseGroupController {
    ExerciseGroupService exerciseGroupService;
    public ExerciseGroupController(ExerciseGroupService exerciseGroupService){
        this. exerciseGroupService = exerciseGroupService;
    }

    // 조건으로 그룹 리스트 검색하기
    @PostMapping("")
    public ResponseEntity<?> getListByCondition(@RequestBody GroupSearchCondition groupSearchCondition){
        List<ExerciseGroup> groups = exerciseGroupService.getListByCondition(groupSearchCondition);
        if (groups == null || groups.isEmpty())
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<List<ExerciseGroup>>(groups, HttpStatus.OK);
    }

    // 입력받은 정보를 토대로 새로운 그룹 생성하기
    @PostMapping("/add")
    public ResponseEntity<?> addGroup(@RequestBody ExerciseGroup exerciseGroup, NativeWebRequest webRequest){
        System.out.println(exerciseGroup);
        long memberId = getMemberId(webRequest);
        // 로그인 되지 않은 경우 그룹을 생성하지 못하게 막기
        if (memberId == -1)
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

        // 현재 로그인된 아이디가 리더 멤버가 될 수 있도록 설정
        exerciseGroup.setLeaderMemberId(memberId);
        if (exerciseGroupService.addGroup(exerciseGroup))
            return new ResponseEntity<Void>(HttpStatus.OK);
        return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 그룹 아이디에 해당하는 그룹에 현재 로그인된 아이디의 멤버 추가하기
    @PostMapping("/{groupId}")
    public ResponseEntity<?> joinGroup(@PathVariable long groupId, NativeWebRequest webRequest){
        long memberId = getMemberId(webRequest);
        if (memberId == -1)
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

        // 현재 로그인된 아이디가 해당 그룹에 속할 수 있도록 설정
        if (exerciseGroupService.joinGroup(memberId, groupId))
            return new ResponseEntity<Void>(HttpStatus.OK);
        return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 현재 내가 속해있는 그룹 조회
    @GetMapping("")
    public ResponseEntity<?> getListMyGroup(NativeWebRequest webRequest){
        long memberId = getMemberId(webRequest);
        // 현재 로그인이 되어있지 않으면 내가 속해있는 그룹을 볼 수 없다.
        if (memberId == -1)
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

        List<ExerciseGroup> groups = exerciseGroupService.getByMemberId(memberId);
        if (groups == null || groups.isEmpty())
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<List<ExerciseGroup>>(groups, HttpStatus.OK);
    }
}
