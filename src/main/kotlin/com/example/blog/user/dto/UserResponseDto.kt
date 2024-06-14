package com.example.blog.user.dto

import com.example.blog.user.User

data class UserResponseDto(
    val id : String,
    val name : String
){
    constructor(user : User) : this(user.name, user.id)
}