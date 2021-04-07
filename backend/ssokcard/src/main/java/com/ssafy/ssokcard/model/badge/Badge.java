package com.ssafy.ssokcard.model.badge;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Builder
@ToString
@Setter
@Getter
@Entity
public class Badge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String language;

    public Badge() {
    }

    public Badge(long id, String language) {
        this.id = id;
        this.language = language;
    }
}
