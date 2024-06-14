package com.example.blog.user.dto

import com.example.blog.user.User

data class UserCreationPayload(
    private val name : String
){
    fun toEntity() : User = User(name=name)
}