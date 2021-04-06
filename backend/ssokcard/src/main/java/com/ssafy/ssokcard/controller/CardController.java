package com.ssafy.ssokcard.controller;

import com.ssafy.ssokcard.dto.GameResultDto;
import com.ssafy.ssokcard.model.response.BasicResponse;
import com.ssafy.ssokcard.service.BadgeService;
import com.ssafy.ssokcard.service.CardService;
import com.ssafy.ssokcard.service.LogService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardService cardService;

    @Autowired
    private LogService logService;

    @Autowired
    private BadgeService badgeService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "카테고리 아이디 (1: animals, 2: fruits)"),
            @ApiImplicitParam(name = "code", value = "언어 코드 (zh: 중국, en-US: 미국, ja-JP: 일본, es-ES: 스페인, vi-VN: 베트남, ko-KR: 대한민국, fr-FR: 프랑스)")
    })
    @ApiOperation(value = "카드 불러오기")
    @GetMapping("/{id}/{code}")
    public Object findCard(@PathVariable("id")long id, @PathVariable("code")String languageCode) {
        return cardService.findCard(id, languageCode);
    }

    @ApiOperation(value = "게임 결과 저장하기")
    @PostMapping
    public Object insertResult(@RequestBody GameResultDto gameResultDto){
        // 로그 저장
        BasicResponse logResult = new BasicResponse();
        logResult = logService.insertLog(gameResultDto);

        if(logResult.status == false){
            return logResult;
        }

        // 뱃지 생성 여부 판단 및 생성
        BasicResponse badgeResult = new BasicResponse();
        badgeResult = badgeService.insertBadge(gameResultDto);

        if(badgeResult.status == false) {
            return badgeResult;
        }

        return badgeResult;
    }

}
