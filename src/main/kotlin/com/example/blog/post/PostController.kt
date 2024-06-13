package com.example.blog.post

import com.example.blog.post.payload.PostCreationPayload
import com.example.blog.post.payload.PostUpdatePayload
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/posts")
class PostController (
    private val postService: PostService
){
    @GetMapping
    fun getAllPosts() : List<Post> = postService.getAllPosts()

    @GetMapping("/{id}")
    fun getPostById(@PathVariable id : String) : ResponseEntity<Post> {
        val post = postService.getById(id)
        return if(post!=null){
            ResponseEntity.ok(post)
        }else{
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun createPost(@RequestBody data : PostCreationPayload){
        postService.create(data)
    }

    @PatchMapping("/{id}")
    fun updatePost(@PathVariable id: String,@RequestBody data : PostUpdatePayload){
        postService.update(id, data)
    }

    @DeleteMapping("/{id}")
    fun deletePost(@PathVariable id : String){
        postService.delete(id)
    }
}