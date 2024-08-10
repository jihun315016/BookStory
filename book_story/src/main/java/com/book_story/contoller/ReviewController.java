package com.book_story.contoller;

import com.book_story.models.mapping.ReviewMapping;
import com.book_story.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/review")
    public String index(Model model) {
        List<ReviewMapping> list = reviewService.findAllReview();
        model.addAttribute("data", list);
        return "review/index.html";
    }

    @GetMapping("/review/write")
    public String write() {
        return "review/write.html";
    }
}
