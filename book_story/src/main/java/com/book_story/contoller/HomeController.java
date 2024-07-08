package com.book_story.contoller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.book_story.service.HomeService;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final HomeService homeService;

    @GetMapping("/")
    public String Index() throws IOException {
        //ItemListDTO itemListDTO =
                homeService.findData();
        return "home/index";
    }
}
