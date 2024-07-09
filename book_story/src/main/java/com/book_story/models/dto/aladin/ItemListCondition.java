package com.book_story.models.dto.aladin;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class ItemListCondition {
    // 요청하는 파라미터 대소문자가 뒤죽박죽
    private String TTBKey;

    private String QueryType;

    private String SearchTarget;

    private int MaxResults;

    private int Start;

    private String Cover;

    private String Output;

    private String Version;
}