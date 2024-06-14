package com.example.blog.post

import com.example.blog.common.PrimaryKeyEntity
import com.example.blog.user.User
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class Post(
    title: String,
    content: String,
    information: PostInformation,
    user : User
) : PrimaryKeyEntity() {
    @Column(nullable = false)
    var createdAt : LocalDateTime = LocalDateTime.now()
        protected set

    @Column(nullable = false)
    var title: String = title
        protected set

    @Column(nullable = false)
    var content: String = content
        protected set

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false)
    var writer : User = user
        protected  set

    @Embedded
    var information : PostInformation = information
        protected set

    fun update(data: PostUpdateData){
        this.title=data.title
        this.information=data.information
        this.content=data.content
    }

    init {
        writer.writePost(this)
    }
}

@Embeddable
data class PostInformation(
    @Column
    val link: String?,

    @Column(nullable = false)
    val ranking: Int
)

data class PostUpdateData(
    val title:String,
    val content: String,
    val information: PostInformation
)