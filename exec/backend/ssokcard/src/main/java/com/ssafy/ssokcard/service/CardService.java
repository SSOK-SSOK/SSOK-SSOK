package com.ssafy.ssokcard.service;

import com.ssafy.ssokcard.model.card.Card;
import com.ssafy.ssokcard.model.card.CardRepository;
import com.ssafy.ssokcard.model.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepo;

    public BasicResponse findCard(long id, String languageCode) {
        BasicResponse result = new BasicResponse();
        if(languageCode == null) {
            result.status = false;
            result.data = "카드 조회 실패";
            result.object = null;

            return result;
        }

        List<Card> cardList = cardRepo.findByCategoryIdAndLanguage(id, languageCode);
        if(cardList.size() == 0 || cardList == null){
            result.status = false;
            result.data = "카드 조회 실패";
            result.object = null;

            return result;
        }

        Collections.shuffle(cardList);
        // ------- 카드 5장 보내는 경우 ------------
        ArrayList<Card> list = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
             list.add(cardList.get(i));
        }
        //--------------------------------------

        result.status = true;
        result.data = "카드 조회 성공";
//        result.object = cardList; // 카드 15장 보내는 경우
        result.object = list; // 카드 5장 보내는 경우

        return result;
    }
}
