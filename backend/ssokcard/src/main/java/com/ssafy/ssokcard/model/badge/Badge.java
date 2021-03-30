package com.ssafy.ssokcard.model.badge;

import com.ssafy.ssokcard.model.category.Category;
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

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Badge() {
    }

    public Badge(long id, String language, Category category) {
        this.id = id;
        this.language = language;
        this.category = category;
    }
}
