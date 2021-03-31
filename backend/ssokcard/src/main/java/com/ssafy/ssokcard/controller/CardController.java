package com.ssafy.ssokcard.controller;

import com.ssafy.ssokcard.service.CardService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "카드게임")
@CrossOrigin(origins = { "*" })
@RestController
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping("/{id}")
    public Object findCard(@PathVariable("id")long id) {
        return cardService.findCard(id);
    }
}
