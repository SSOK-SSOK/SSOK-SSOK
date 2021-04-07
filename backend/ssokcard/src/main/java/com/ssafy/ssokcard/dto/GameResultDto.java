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
    @ApiModelProperty(example = "korea, us, china, japan, france, spain, vietnam (String)")
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
