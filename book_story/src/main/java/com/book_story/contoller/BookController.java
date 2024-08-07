package com.book_story.contoller;

import java.io.IOException;

import com.book_story.models.dto.PagedData;
import com.book_story.models.dto.Pagination;
import com.book_story.models.dto.aladin.ItemSearchDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.book_story.service.BookService;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
public class BookController {
    private final BookService bookService;

    @GetMapping("/book")
    public String index() throws IOException {
        return "book/index.html";
    }

    @ResponseBody
    @GetMapping("/bookSearch")
    public PagedData index(Model model, @RequestParam(required = false) String searchText, int page) throws IOException {
        ItemSearchDTO data = new ItemSearchDTO();
        data = bookService.itemSearch(searchText, page);
        Pagination pagination = bookService.getPagination(data);

        PagedData pagedData = PagedData.builder()
                .pagination(pagination)
                .data(data)
                .build();

        return pagedData;
    }
}
