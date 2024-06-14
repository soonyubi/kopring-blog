package com.example.blog.tag

import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface TagRepository : JpaRepository<Tag, String>{
    fun findByKeyAndValue(key : String, value: String) : Optional<Tag>
}

