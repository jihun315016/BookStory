package com.book_story.models.mapping;

import java.time.LocalDateTime;

public interface ReviewMapping {
    Long getId();

    String getTitle();

    String getUserId();

    LocalDateTime getCreateDate();

    String getBookId();

    String getBookName();
}
