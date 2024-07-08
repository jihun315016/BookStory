package com.book_story.models.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

public class CommonCodePK implements Serializable {
    @Column(name = "code_group")
    private String codeGroup;

    @Column(name = "code")
    private String code;
}
