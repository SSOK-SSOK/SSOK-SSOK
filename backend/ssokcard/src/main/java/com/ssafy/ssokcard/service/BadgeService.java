package com.ssafy.ssokcard.service;

import com.ssafy.ssokcard.dto.GameResultDto;
import com.ssafy.ssokcard.model.badge.Badge;
import com.ssafy.ssokcard.model.badge.BadgeRepository;
import com.ssafy.ssokcard.model.badge.CollectedBadge;
import com.ssafy.ssokcard.model.badge.CollectedBadgeRepository;
import com.ssafy.ssokcard.model.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BadgeService {

    @Autowired
    private CollectedBadgeRepository collectedBadgeRepo;

    @Autowired
    private BadgeRepository badgeRepo;

    public BasicResponse insertBadge(GameResultDto gameResultDto) {
        BasicResponse result = new BasicResponse();

        if(gameResultDto == null){
            result.status = false;
            result.data = "뱃지 저장 실패";
            result.object = null;

            return result;
        }

        if(gameResultDto.getScore() < 12) {
            result.status = true;
            result.data = "점수 미달";
            result.object = null;

            return result;
        }

        // 유저가 보유한 뱃지 목록 = CollectedBadgeList
        List<CollectedBadge> CollectedBadgeList = collectedBadgeRepo.findByUserId(gameResultDto.getUserId());

        // '현재 진행한 게임의 카테고리와 언어' 와 '유저가 보유한 뱃지' 를 비교
        // 카테고리와 언어가 일치하면 보유한 것으로 판단
        for (CollectedBadge collectedBadge : CollectedBadgeList) {
            if(collectedBadge.getCategoryId() == gameResultDto.getCategoryId()) {
                Badge badge = badgeRepo.findByLanguage(gameResultDto.getLanguage());

                if(badge.getId() == collectedBadge.getBadgeId()){
                    result.status = true;
                    result.data = "이미 뱃지 보유";
                    result.object = null;

                    return result;
                }
            }
        }

        CollectedBadge collectedBadge = new CollectedBadge();
        collectedBadge.setUserId(gameResultDto.getUserId());
        collectedBadge.setCategoryId(gameResultDto.getCategoryId());
        collectedBadge.setBadgeId(badgeRepo.findByLanguage(gameResultDto.getLanguage()).getId());
        collectedBadgeRepo.save(collectedBadge);

        result.status = true;
        result.data = "뱃지 저장 성공";
        result.object = null;

        return result;
    }
}
