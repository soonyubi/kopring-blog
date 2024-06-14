package com.example.blog.post

import com.example.blog.post.dto.CommentCreationPayload
import com.example.blog.post.dto.PostCreationPayload
import com.example.blog.post.dto.PostUpdatePayload
import com.example.blog.tag.Tag
import com.example.blog.tag.TagRepository
import com.example.blog.tag.dto.TagCreationPayload
import com.example.blog.user.User
import com.example.blog.user.UserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class PostService(
    private val postRepository: PostRepository,
    private val userRepository: UserRepository,
    private val tagRepository: TagRepository
){
    fun getAllPosts() : List<Post> = this.postRepository.findAll()


    fun getById(id: String) : Post = this.postRepository.findById(id).orElseThrow()

    @Transactional
    fun create(data : PostCreationPayload) : Post {
        val user = getUserById(data.writerId)
        val tags = data.tags.map { findOrCreateTag(it) }.toSet()
        return this.postRepository.save(data.toEntity(user, tags))
    }

    @Transactional
    fun update(id:String, data : PostUpdatePayload) : Post{
        return getById(id).apply { update(data.toData()) }
    }

    @Transactional
    fun delete(id: String){
        getById(id).apply { delete(id) }
    }

    @Transactional
    fun addTag(id : String, data : TagCreationPayload) : Post {
        return getById(id).apply { addTag(findOrCreateTag(data)) }
    }

    fun removeTag(id : String, tagId : String) : Post {
        return getById(id).apply { removeTag(id, tagId) }
    }

    fun addComment(id: String, data : CommentCreationPayload) : Post {
        return getById(id).apply {
            val user = getUserById(data.writerId)
            val comment = Comment(data.content, user)
            addComment(comment)
        }
    }

    private fun getUserById(writerId: String): User = userRepository.findById(writerId).orElseThrow()

    private fun findOrCreateTag(command: TagCreationPayload): Tag =
        tagRepository.findByKeyAndValue(command.key, command.value).orElse(command.toEntity())
}