package com.book_story.models.dto.aladin;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RequestData {
    private String url;
    private String method;
    private String userAgent;
}
