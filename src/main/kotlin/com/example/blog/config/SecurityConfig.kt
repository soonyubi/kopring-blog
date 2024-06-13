package com.example.blog.config
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf().disable()  // CSRF 비활성화
            .authorizeHttpRequests { auth ->
                auth.anyRequest().permitAll()  // 모든 요청에 대해 인증 없이 접근 허용
            }
            .httpBasic().disable()  // HTTP Basic 인증 비활성화
        return http.build()
    }
}