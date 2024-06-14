package com.example.blog.post

import com.example.blog.common.PrimaryKeyEntity
import com.example.blog.tag.Tag
import com.example.blog.user.User
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class Post(
    title: String,
    content: String,
    information: PostInformation,
    user : User,
    tags: Set<Tag>
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

    @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(
        name = "board_tag_assoc",
        joinColumns = [JoinColumn(name = "post_id")],
        inverseJoinColumns = [JoinColumn(name = "tag_id")]
    )
    protected var mutableTags: MutableSet<Tag> = tags.toMutableSet()
    val tags: Set<Tag> get() = mutableTags.toSet()

    @ElementCollection
    @CollectionTable(name = "post_comment")
    private val mutableComments : MutableList<Comment> = mutableListOf()
    val comments: List<Comment> get() = mutableComments.toList()

    @Embedded
    var information : PostInformation = information
        protected set

    fun update(data: PostUpdateData){
        this.title=data.title
        this.information=data.information
        this.content=data.content
    }

    fun addTag(tag : Tag){
        mutableTags.add(tag)
    }

    fun removeTag(tagId: String){
        mutableTags.removeIf { it.id == tagId }
    }

    fun addComment(comment : Comment){
        mutableComments.add(comment)
    }

    fun removeComment(comment: Comment){
        mutableComments.remove(comment)
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

@Embeddable
data class Comment(
    @Column(name = "content", length = 3000)
    private var _content : String,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "writer_id")
    private var _writer : User
){
    val content : String get() = _content
    val writer : User get() = _writer
}

data class PostUpdateData(
    val title:String,
    val content: String,
    val information: PostInformation
)