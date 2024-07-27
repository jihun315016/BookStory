package com.book_story.models.dto.aladin;

import java.util.List;
import lombok.Getter;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@ToString
public class ItemSearchDTO {
    // 검색 조건
    ItemSearchCondition condition;

    // 검색 결과 - 헤더
    @JsonProperty("totalResults")
    private int totalResults;

    @JsonProperty("startIndex")
    private int startIndex;

    @JsonProperty("itemsPerPage")
    private int itemsPerPage;

    @JsonProperty("query")
    private String query;

    // 겸색 결과 - 디테일(책 리스트)
    @JsonProperty("item")
    List<ItemSearchElement> item;
}


@Getter
@ToString
class ItemSearchElement {
    @JsonProperty("title")
    private String title;

    @JsonProperty("author")
    private String author;

    @JsonProperty("pubDate")
    private String pubDate;

    @JsonProperty("description")
    private String description;

    @JsonProperty("isbn")
    private String isbn;

    @JsonProperty("isbn13")
    private String isbn13;

    @JsonProperty("itemId")
    private String itemId;

    @JsonProperty("cover")
    private String cover;

    @JsonProperty("categoryId")
    private String categoryId;

    @JsonProperty("categoryName")
    private String categoryName;

    @JsonProperty("publisher")
    private String publisher;
}
