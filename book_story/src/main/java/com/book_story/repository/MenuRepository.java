package com.book_story.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.book_story.models.entity.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> { }
