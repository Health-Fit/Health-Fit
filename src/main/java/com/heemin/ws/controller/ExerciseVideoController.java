package com.heemin.ws.controller;

import com.heemin.ws.model.dto.SearchCondition;
import com.heemin.ws.model.dto.member.Member;
import com.heemin.ws.model.dto.requests.video.VideoBlock;
import com.heemin.ws.model.dto.requests.video.VideoLike;
import com.heemin.ws.model.dto.video.ExerciseVideo;
import com.heemin.ws.model.service.ExerciseVideoReviewService;
import com.heemin.ws.model.service.ExerciseVideoService;
import com.heemin.ws.model.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;


import static com.heemin.ws.controller.MemberManager.getMemberId;

@RestController
@RequestMapping("/api/videos")
public class ExerciseVideoController {

    private final OpenAiChatModel openAiChatModel;
    MemberService memberService;
    ExerciseVideoService videoService;
    ExerciseVideoReviewService reviewService;

    public ExerciseVideoController(MemberService memberService, ExerciseVideoService videoService,
                                   ExerciseVideoReviewService reviewService, OpenAiChatModel openAiChatModel) {
        this.memberService = memberService;
        this.videoService = videoService;
        this.reviewService = reviewService;
        this.openAiChatModel = openAiChatModel;
    }

    @GetMapping("/ai")
    public ResponseEntity<?> getAi() {
        String response = openAiChatModel.call("다음 카테고리에 알맞은 운동 영상 유튜브 링크를 알려줘. 아래에 제시된 국가의 영상을 보여줘. 형식에만 맞춰주고 검색결과에 대한 앞 뒤 설명은 하지 말아줘.\n" +
                "국가 : 한국\n" +
                "카테고리 : 헬스, 전신운동\n" +
                "답변개수 : 최대 10\n" +
                "\n" +
                "출력 형식은 아래와 같아\n" +
                "(번호없이)(유튜브링크),(영상 제목)");
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    // 운동 영상 목록 조회 ( + 검색)
    @PostMapping("")
    public ResponseEntity<?> getList(@RequestBody SearchCondition searchCondition, NativeWebRequest webRequest) {
        long memberId = getMemberId(webRequest);
        // 유저 정보를 넣어서 유저가 좋아요 표시를 했는지 받아오기
        List<ExerciseVideo> videos = videoService.getVideoByCondition(memberId, searchCondition);
        if (videos == null || videos.isEmpty())
            return new ResponseEntity<String>("등록된 비디오 영상 자료가 없습니다.", HttpStatus.NO_CONTENT);
        return new ResponseEntity<List<ExerciseVideo>>(videos, HttpStatus.OK);
    }

    // 회원가입시 사용할 샘플 비디오 목록 반환
    @GetMapping("/ex")
    public ResponseEntity<?> getExample() {
        List<ExerciseVideo> videos = videoService.getVideoSample();
        return new ResponseEntity<List<ExerciseVideo>>(videos, HttpStatus.OK);
    }

    //영상 상세정보를 받기 위한 API
    @GetMapping("/{id}")
    public ResponseEntity<?> getDetail(@PathVariable long id, NativeWebRequest webRequest) {
        // 현재 로그인한 멤버 id 받아오기
        long memberId = getMemberId(webRequest);

        ExerciseVideo video = videoService.getVideoById(id, memberId);
        System.out.println(video);
        if (video == null)
            return new ResponseEntity<String>("등록된 비디오 영상 자료가 없습니다.", HttpStatus.NOT_FOUND);
        return new ResponseEntity<ExerciseVideo>(video, HttpStatus.OK);
    }

    // 사용자 권한에 따른 영상정보 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable long id, NativeWebRequest webRequest) {
        // 현재 로그인 정보가 없는 경우 권한없음 표시
        long memberId = getMemberId(webRequest);
        // 로그인이 안되어있는 경우 권한 없음으로 표시
        if (memberId == -1)
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);

        // 현재 로그인한 관리자 정보 받아오기
        Member curMem = memberService.getById(memberId);
        // 관리자가 아닌 경우 authorized 되지 않았음을 보내줌
        if (curMem == null || curMem.getMemberAuthId() != 1)
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);

        // 제대로 삭제되었는지 여부 보여줌
        if (videoService.removeVideo(id))
            return new ResponseEntity<Void>(HttpStatus.OK);
        return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 영상 좋아요 관리 API
    @PutMapping("/like")
    public ResponseEntity<?> setLike(@RequestBody VideoLike videoLike, NativeWebRequest webRequest) {
        long memberId = getMemberId(webRequest);

        // 로그인하지 않은 경우 BAD REQUEST로 돌림
        if (memberId == -1)
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

        // 현재 로그인한 유저 memberId로 영상 좋아요 표시
        if (videoService.setVideoLike(memberId, videoLike.getVideoId(), videoLike.isLike()))
            return new ResponseEntity<Void>(HttpStatus.OK);
        else
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 영상 싫어요 관리 API
    @PutMapping("/block")
    public ResponseEntity<?> setBlock(@RequestBody VideoBlock videoBlock, NativeWebRequest webRequest) {
        long memberId = getMemberId(webRequest);
        // 로그인하지 않은 경우 BAD REQUEST로 돌림
        if (memberId == -1)
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

        // 현재 로그인한 정보로 영상 싫어요 표시
        if (videoService.setVideoBlock(memberId, videoBlock.getVideoId(), videoBlock.isBlock()))
            return new ResponseEntity<Void>(HttpStatus.OK);
        else
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 본인이 찜한 영상 받아오기
    @GetMapping("/like")
    public ResponseEntity<?> getMyLikeList(NativeWebRequest webRequest) {
        long memberId = getMemberId(webRequest);
        // 로그인하지 않은 경우 BAD REQUEST로 돌림
        if (memberId == -1)
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

        // 현재 로그인한 정보를 토대로 찜한 영상 받아오기
        List<ExerciseVideo> videos = videoService.getVideoByLike(memberId);
        if (videos == null || videos.isEmpty())
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<List<ExerciseVideo>>(videos, HttpStatus.OK);

    }

    // 지정한 멤버가 찜한 영상 받아오기
    @GetMapping("/like/{memberId}")
    public ResponseEntity<?> getMemberLikeList(@PathVariable long memberId) {
        List<ExerciseVideo> videos = videoService.getVideoByLike(memberId);
        if (videos == null || videos.isEmpty())
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        else
            return new ResponseEntity<List<ExerciseVideo>>(videos, HttpStatus.OK);
    }

//	public long getMemberId(NativeWebRequest webRequest){
//		HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
//		Object loginMember = request.getAttribute("memberId");
//		long memberId = -1;
//		if (loginMember != null)
//			memberId = Long.valueOf(loginMember.toString());
//		return memberId;
//	}
}
