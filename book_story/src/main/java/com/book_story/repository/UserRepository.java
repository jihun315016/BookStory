package com.book_story.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.book_story.models.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String>
{
    Optional<User> findById(String id);
}
