package com.book_story.contoller;

import com.book_story.models.entity.User;
import com.book_story.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @ResponseBody
    @GetMapping("/user")
    public List<User> findAll() {
        return userService.findAll();
    }

    @ResponseBody
    @PostMapping("/user")
    public User save_user(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return user;
    }
}
