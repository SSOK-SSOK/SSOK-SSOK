package com.ssafy.ssokcard.controller;

import com.ssafy.ssokcard.dto.GameResultDto;
import com.ssafy.ssokcard.model.response.BasicResponse;
import com.ssafy.ssokcard.service.BadgeService;
import com.ssafy.ssokcard.service.CardService;
import com.ssafy.ssokcard.service.LogService;
import io.swagger.annotations.ApiImplicitParam;
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

    @ApiImplicitParam(name = "id", value = "카테고리 아이디 (1: animals, 2: fruits)")
    @ApiOperation(value = "카드 불러오기")
    @GetMapping("/{id}")
    public Object findCard(@PathVariable("id")long id) {
        return cardService.findCard(id);
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
