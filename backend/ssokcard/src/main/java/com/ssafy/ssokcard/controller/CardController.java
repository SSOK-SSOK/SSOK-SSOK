package com.ssafy.ssokcard.controller;

import com.ssafy.ssokcard.dto.GameResultDto;
import com.ssafy.ssokcard.service.CardService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "카드게임")
@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping("/{id}")
    public Object findCard(@PathVariable("id")long id) {
        return cardService.findCard(id);
    }

    public Object insertResult(@RequestBody GameResultDto gameResultDto){
        // 로그 저장
        cardService

        // 점수로 뱃지 생성 여부 판단
    }
}
