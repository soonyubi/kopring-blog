package com.example.blog.user

import com.example.blog.user.dto.UserCreationPayload
import com.example.blog.user.dto.UserResponseDto
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController(
    private val userService: UserService
){
    @PostMapping
    fun createUser(payload : UserCreationPayload) : UserResponseDto = UserResponseDto(userService.create(payload))

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id : String) = userService.delete(id)
}



