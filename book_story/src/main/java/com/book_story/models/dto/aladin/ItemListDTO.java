package com.book_story.models.dto.aladin;

import lombok.Getter;
import lombok.ToString;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@ToString
public class ItemListDTO {
    // 검색 조건
    ItemListCondition contition;

    // 검색 결과 - 헤더
    @JsonProperty("totalResults")
    private int totalResults;

    @JsonProperty("startIndex")
    private int startIndex;

    @JsonProperty("itemsPerPage")
    private int itemsPerPage;

    // 겸색 결과 - 디테일(책 리스트)
    @JsonProperty("item")
    List<ItemListElement> item;
}


@Getter
@ToString
class ItemListElement {
    @JsonProperty("title")
    private String title;

    @JsonProperty("author")
    private String author;

    @JsonProperty("publisher")
    private String publisher;

    @JsonProperty("pubDate")
    private String pubDate;

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
}
