package com.ssafy.ssokcard.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GameResultDto {

    @ApiModelProperty(example = "(int)")
    private long userId;
    @ApiModelProperty(example = "0 ~ 15 (int)")
    private int score;
    @ApiModelProperty(example = "1 ~ 2 (int)")
    private long categoryId;
    @ApiModelProperty(example = "ko-KR(대한민국), en-US(미국), zh(중국), ja-JP(일본), vi-VN(베트남), fr-FR(프랑스), es-ES(스페인) (String)")
    private String language;

    public GameResultDto() {
    }

    @Builder
    public GameResultDto(long userId, int score, long categoryId, String language) {
        this.userId = userId;
        this.score = score;
        this.categoryId = categoryId;
        this.language = language;
    }
}
