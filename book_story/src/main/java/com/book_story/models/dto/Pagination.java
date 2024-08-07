package com.book_story.models.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Pagination {
    private int itemsPerPage;
    private int currentPage;
    private int pagesPerView;
    private int startPage;
    private int endPage;
    private int totalPage;
}

