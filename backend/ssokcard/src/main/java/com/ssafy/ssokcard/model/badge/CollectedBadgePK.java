package com.ssafy.ssokcard.model.badge;

import lombok.Data;

import java.io.Serializable;

public class CollectedBadgePK implements Serializable {
    private long userId;
    private long badgeId;
    private long categoryId;
}
