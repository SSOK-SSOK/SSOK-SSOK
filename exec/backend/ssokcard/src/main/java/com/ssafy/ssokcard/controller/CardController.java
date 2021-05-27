package com.ssafy.ssokcard.controller;

import com.ssafy.ssokcard.dto.GameResultDto;
import com.ssafy.ssokcard.service.CardService;
import com.ssafy.ssokcard.service.LogService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardService cardService;

    @Autowired
    private LogService logService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "카테고리 아이디 (1: animals, 2: fruits)"),
            @ApiImplicitParam(name = "code", value = "언어 코드 (ko-KR: 대한민국, en-US: 미국, zh: 중국, ja-JP: 일본, vi-VN: 베트남, fr-FR: 프랑스, es-ES: 스페인)")
    })
    @ApiOperation(value = "카드 불러오기")
    @GetMapping("/{id}/{code}")
    public Object findCard(@PathVariable("id") long id, @PathVariable("code") String languageCode) {
        return cardService.findCard(id, languageCode);
    }

    @ApiOperation(value = "게임 결과 저장하기")
    @PostMapping
    public Object insertResult(@RequestBody GameResultDto gameResultDto) {
        return logService.insertResult(gameResultDto);
    }

}
