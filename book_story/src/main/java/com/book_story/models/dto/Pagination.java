package com.book_story.models.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Pagination {
    private int itemsPerPage;
    private int currentPage;
    private int totalPage;
    private int pagesPerView;
}

