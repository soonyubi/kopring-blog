package com.example.blog.post.dto

import com.example.blog.post.Comment
import com.example.blog.post.Post
import com.example.blog.post.PostInformation
import com.example.blog.post.PostUpdateData
import com.example.blog.tag.Tag
import com.example.blog.tag.dto.TagCreationPayload
import com.example.blog.user.User
import com.example.blog.user.dto.UserCreationPayload

data class PostCreationPayload(
    val title: String,
    val content:String,
    val information: PostInformationPayload,
    val writerId : String,
    val tags : Set<TagCreationPayload>
){
    fun toEntity(writer : User,tags: Set<Tag>) = Post(
        title=title,
        content=content,
        information=information.toEntity(),
        writer = writer,
        tags = tags
    )
}

data class PostUpdatePayload(
    val title : String,
    val content: String,
    val information: PostInformationPayload
){
    fun toData() = PostUpdateData(
        title = title,
        content=content,
        information=information.toEntity()
    )
}

data class PostInformationPayload(
    val link: String?,
    val ranking: Int
){
    fun toEntity() : PostInformation = PostInformation(
        link=link,
        ranking=ranking
    )
}

data class CommentCreationPayload(
    val content: String,
    val writerId : String
)
