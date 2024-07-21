package com.book_story.service;

import com.book_story.models.mapping.ReviewMapping;
import com.book_story.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.*;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public List<ReviewMapping> findAllReview() {
        return reviewRepository.findAllBy(ReviewMapping.class);
    }
}
