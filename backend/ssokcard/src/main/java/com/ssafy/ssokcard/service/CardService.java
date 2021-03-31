package com.ssafy.ssokcard.service;

import com.ssafy.ssokcard.model.card.Card;
import com.ssafy.ssokcard.model.card.CardRepository;
import com.ssafy.ssokcard.model.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepo;

    public BasicResponse findCard(long id) {
        BasicResponse result = new BasicResponse();
        List<Card> cardList = cardRepo.findByCategoryId(id);

        if(cardList.size() == 0 || cardList == null){
            result.status = false;
            result.data = "카드 조회 실패";
            result.object = null;

            return result;
        }

        Collections.shuffle(cardList);
        result.status = true;
        result.data = "카드 조회 성공";
        result.object = cardList;

        return result;
    }
}
