package com.example.blog.post

import com.example.blog.post.dto.CommentCreationPayload
import com.example.blog.post.dto.PostCreationPayload
import com.example.blog.post.dto.PostResponseDto
import com.example.blog.post.dto.PostUpdatePayload
import com.example.blog.tag.dto.TagCreationPayload
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/posts")
class PostController (
    private val postService: PostService
){
    @GetMapping
    fun getAllPosts() : List<PostResponseDto> {
        val posts =  postService.getAllPosts()
        return posts.map { PostResponseDto(it) }
    }

    @GetMapping("/{id}")
    fun getPostById(@PathVariable id : String) : PostResponseDto = PostResponseDto(postService.getById(id))

    @PostMapping
    fun createPost(@RequestBody data : PostCreationPayload) : PostResponseDto = PostResponseDto(postService.create(data))

    @PatchMapping("/{id}")
    fun updatePost(@PathVariable id: String,@RequestBody data : PostUpdatePayload) : PostResponseDto = PostResponseDto(postService.update(id, data))

    @DeleteMapping("/{id}")
    fun deletePost(@PathVariable id : String){
        postService.delete(id)
    }

    @PostMapping("/{id}/tags")
    fun createTags(@PathVariable id : String,@RequestBody tags : TagCreationPayload) : PostResponseDto = PostResponseDto(postService.addTag(id, tags))

    @DeleteMapping("/{id}/tags/{tagId}")
    fun deleteTags(@PathVariable id : String, @PathVariable tagId : String) : PostResponseDto = PostResponseDto(postService.removeTag(id,tagId))

    @PostMapping("/{id}/comments")
    fun createComment(@PathVariable id : String, @RequestBody data : CommentCreationPayload) : PostResponseDto = PostResponseDto(postService.addComment(id, data))
}