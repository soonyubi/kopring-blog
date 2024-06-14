package com.example.blog.tag

import com.example.blog.common.PrimaryKeyEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name="tag")
class Tag(
    key : String,
    value: String
) : PrimaryKeyEntity(){
    @Column(nullable = false, name = "`key`")
    var key : String = key
        protected set

    @Column(nullable = false, name = "`value`")
    var value : String = value
        protected set
}