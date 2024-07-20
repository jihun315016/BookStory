package com.book_story.service;

import java.util.List;

import com.book_story.models.entity.Menu;
import com.book_story.repository.MenuRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MenuService {
    private final MenuRepository menuRepository;

    public List<Menu> findAll() {
        List<Menu> list = menuRepository.findAll();
        return list;
    }
}
