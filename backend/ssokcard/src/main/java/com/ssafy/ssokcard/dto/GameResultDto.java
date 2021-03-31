package com.ssafy.ssokcard.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
public class GameResultDto {

    private long userId;
    private int score;
    private long categoryId;
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
