package com.book_story.models.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name="menu")
public class Menu {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "level")
    private int level;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "path")
    private String path;

    @Column(name = "parent_menu_id")
    private int parentMenuId;
}
