package com.example.blog.post.payload

import com.example.blog.post.Post
import com.example.blog.post.PostInformation
import com.example.blog.post.PostUpdateData

data class PostCreationPayload(
    val title: String,
    val content:String,
    val information: PostInformationPayload
){
    fun toEntity() = Post(
        title=title,
        content=content,
        information=information.toEntity()
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