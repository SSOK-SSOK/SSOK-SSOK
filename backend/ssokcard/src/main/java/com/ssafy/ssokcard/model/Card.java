package com.ssafy.ssokcard.model;

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
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String path;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Card() {
    }

    public Card(long id, String path, Category category) {
        this.id = id;
        this.path = path;
        this.category = category;
    }
}
