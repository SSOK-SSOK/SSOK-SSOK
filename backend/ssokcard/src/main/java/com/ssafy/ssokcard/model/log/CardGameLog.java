package com.ssafy.ssokcard.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@ToString
@Setter
@Getter
@Entity
public class CardGameLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @CreationTimestamp
    private LocalDateTime date;

    private int score;

    private Long userId;

    private String language;

    public CardGameLog() {

    }

    public CardGameLog(long id, LocalDateTime date, int score, Long userId, String language) {
        this.id = id;
        this.date = date;
        this.score = score;
        this.userId = userId;
        this.language = language;
    }
}
