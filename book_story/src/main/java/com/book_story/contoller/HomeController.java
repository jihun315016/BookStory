package com.book_story.contoller;

import com.book_story.models.dto.aladin.ItemListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.book_story.service.HomeService;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final HomeService homeService;

    @GetMapping("/")
    public String Index(Model model) throws IOException {
        List<ItemListDTO> list = homeService.findData();
        model.addAttribute("data", list);
        return "home/index.html";
    }
}
