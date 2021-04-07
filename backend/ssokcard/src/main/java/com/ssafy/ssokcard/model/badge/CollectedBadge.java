package com.ssafy.ssokcard.model.badge;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Builder
@ToString
@Setter
@Getter
@Entity
@IdClass(CollectedBadgePK.class)
public class CollectedBadge {

    @Id
    private long userId;

    @Id
    private long badgeId;

    @Id
    private long categoryId;

    public CollectedBadge() {
    }

    public CollectedBadge(long userId, long badgeId, long categoryId) {
        this.userId = userId;
        this.badgeId = badgeId;
        this.categoryId = categoryId;
    }
}
