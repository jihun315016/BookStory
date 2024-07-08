package com.book_story.models.dto.aladin;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class ItemListCondition {
    private String ttbkey;

    private String queryType;

    private String cover;

    private int maxResult;

    private int start;

    private String searchTarget;

    private String output;

    private String version;
}