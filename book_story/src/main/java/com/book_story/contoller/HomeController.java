package com.book_story.contoller;

import com.book_story.models.dto.aladin.ItemListDTO;
import com.book_story.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final BookService bookService;

    @GetMapping("/")
    public String Index(Model model) throws IOException {
        List<ItemListDTO> list = bookService.findItemList();
        model.addAttribute("data", list);
        return "home/index.html";
    }
}
