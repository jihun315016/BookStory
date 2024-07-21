package com.book_story.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.book_story.models.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>
{
//    @Query("SELECT r.id, r.title, r.userId, r.createDate, r.bookId, r.bookName FROM Review r")
//    List<ReviewDTO> findAllReview();

    <T> List<T> findAllBy(Class<T> type);

    //List<ReviewMapping> findAllBy();
}

