package com.example.blog.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, String>{}