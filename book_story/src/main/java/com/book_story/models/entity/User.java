package com.book_story.models.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="user")
public class User {
    @Id
    @Column(name="id")
    private String id;

    @Column(name="password")
    private String password;
}
