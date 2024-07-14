package com.book_story.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true) // 이거 해야 컨트롤러에서 @PreAuthorize 애너테이션 쓸 수 있음
public class SecurityConfig {
    @Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // CSRF 공격 방지를 위해 랜덤한 토큰 생성
    @Bean
    public CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        // CSRF 활성화 설정
        httpSecurity.csrf(csrf -> csrf.csrfTokenRepository(csrfTokenRepository())
                .ignoringRequestMatchers("/account/login")
        );
        
        // 인증 설정
        httpSecurity.authorizeHttpRequests(
                authorize -> authorize.requestMatchers("/**").permitAll()
        );

        // 로그인 설정
        httpSecurity.formLogin(
                login -> login.loginPage("/login")
                        .defaultSuccessUrl("/")
                        .failureUrl("/account/login?error")
        );

        // 로그아웃 설정
        httpSecurity.logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true));

        return httpSecurity.build();
    }
}
