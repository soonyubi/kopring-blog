package com.example.blog.post.dto

import com.example.blog.post.Comment
import com.example.blog.post.Post
import com.example.blog.post.PostInformation
import com.example.blog.tag.dto.TagResponseDto
import com.example.blog.user.dto.UserResponseDto

data class PostResponseDto(
    val id : String,
    val writer: UserResponseDto,
    val title : String,
    val content: String,
    val information: PostInformationDto,
    val tags : Set<TagResponseDto>,
    val comments : List<CommentResponseDto>
){
    constructor(post : Post) : this(
        id= post.id,
        writer= UserResponseDto(post.writer),
        title =post.title,
        content = post.content,
        information = PostInformationDto(post.information),
        tags = post.tags.map { TagResponseDto(it) }.toSet(),
        comments = post.comments.map { CommentResponseDto(it) }
    )
}

data class CommentResponseDto(
    val content : String,
    val writer : UserResponseDto
){
    constructor(comment : Comment) : this(comment.content, UserResponseDto(comment.writer))
}

data class PostInformationDto(
    val ranking: Int,
    val link : String?
){
    constructor(information: PostInformation) : this(link = information.link, ranking = information.ranking)
}

