package com.book_story.contoller;

import java.util.List;

import com.book_story.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

import com.book_story.models.entity.Menu;

@RequiredArgsConstructor
@Controller
public class MenuController {
    private final MenuService menuService;

    @ResponseBody
    @GetMapping("/menu")
    public List<Menu> menu() {
        List<Menu> list = menuService.findAll();
        return list;
    }
}
