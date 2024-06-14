package com.example.blog.tag.dto

import com.example.blog.tag.Tag

data class TagResponseDto(
    val id : String,
    val key: String,
    val value: String
){
    constructor(tag: Tag) : this(tag.id,tag.key,tag.value)
}