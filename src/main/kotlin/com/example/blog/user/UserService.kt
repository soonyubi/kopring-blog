package com.example.blog.user

import com.example.blog.user.dto.UserCreationPayload
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
){
    fun create(data : UserCreationPayload) : User{
        return userRepository.save(data.toEntity())
    }

    fun delete(id : String){
        userRepository.deleteById(id)
    }
}

