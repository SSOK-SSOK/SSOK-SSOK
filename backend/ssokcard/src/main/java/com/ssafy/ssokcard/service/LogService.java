package com.ssafy.ssokcard.service;

import com.ssafy.ssokcard.dto.GameResultDto;
import com.ssafy.ssokcard.model.log.CardGameLogRepository;
import com.ssafy.ssokcard.model.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    @Autowired
    private CardGameLogRepository cardGameLogRepository;

    public BasicResponse insertLog(GameResultDto gameResultDto){

    }

}
