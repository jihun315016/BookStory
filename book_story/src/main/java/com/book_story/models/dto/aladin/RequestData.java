package com.book_story.models.dto.aladin;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class RequestData {
    private String url;
    private String method;
    private String userAgent;
}
