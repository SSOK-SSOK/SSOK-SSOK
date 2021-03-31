package com.ssafy.ssokcard.service;

import com.ssafy.ssokcard.dto.GameResultDto;
import com.ssafy.ssokcard.model.CardGameLog;
import com.ssafy.ssokcard.model.log.CardGameLogRepository;
import com.ssafy.ssokcard.model.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    @Autowired
    private CardGameLogRepository cardGameLogRepo;

    public BasicResponse insertLog(GameResultDto gameResultDto) {
        BasicResponse result = new BasicResponse();

        if(gameResultDto == null){
            result.status = false;
            result.data = "로그 저장 실패";
            result.object = null;

            return result;
        }

        CardGameLog cardGameLog = new CardGameLog();
        cardGameLog.setLanguage(gameResultDto.getLanguage());
        cardGameLog.setScore(gameResultDto.getScore());
        cardGameLog.setUserId(gameResultDto.getUserId());
        cardGameLogRepo.save(cardGameLog);

        result.status = true;
        result.data = "로그 저장 성공";
        result.object = null;

        return result;
    }

}
