package com.example.blog.post

import com.example.blog.post.payload.PostCreationPayload
import com.example.blog.post.payload.PostUpdatePayload
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*

@Service
class PostService(
    private val postRepository: PostRepository
){
    fun getAllPosts() : List<Post> = this.postRepository.findAll()


    fun getById(id: String) : Post? = this.postRepository.findById(id).orElseThrow()

    @Transactional
    fun create(data : PostCreationPayload) {
        this.postRepository.save(data.toEntity())
    }

    @Transactional
    fun update(id:String, data : PostUpdatePayload){
        getById(id)?.apply { update(data.toData()) }
    }

    @Transactional
    fun delete(id: String){
        getById(id)?.apply { delete(id) }
    }
}