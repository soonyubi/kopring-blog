package com.example.blog.tag.dto

import com.example.blog.tag.Tag


data class TagCreationPayload(
    val key : String,
    val value: String
){
    fun toEntity()  = Tag(key, value)
}