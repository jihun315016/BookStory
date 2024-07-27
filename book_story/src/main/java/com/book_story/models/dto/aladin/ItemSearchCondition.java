package com.book_story.models.dto.aladin;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class ItemSearchCondition {
    private String ttbkey;

    private String query;

    private String queryType;

    private String searchTarget;

    private int maxResults;

    private int start;

    private String cover;

    private String output;

    private String version;
}
