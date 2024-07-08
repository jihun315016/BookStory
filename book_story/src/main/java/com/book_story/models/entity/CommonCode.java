package com.book_story.models.entity;

import jakarta.persistence.*;
import com.book_story.models.key.CommonCodePK;
import lombok.Getter;

@Entity
@Table(name = "common_code")
@IdClass(CommonCodePK.class)
@Getter
public class CommonCode {
    @Id
    @Column(name = "code_group")
    private String codeGroup;

    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

     @Column(name = "description")
    private String description;
}
