package com.book_story.service;

import com.book_story.models.entity.User;
import com.book_story.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BS_UserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findById(username);
        System.out.println("=====");
        System.out.println(username);
        System.out.println(user);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("그런 아이디 없어요.");
        }
        System.out.println("hello");
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        System.out.println("world");
        // 사용자 아이디, 비밀번호, 권한 리스트
        return new org.springframework.security.core.userdetails.User(user.get().getId(), user.get().getPassword(), authorities);
    }
}
