package com.example.blog.common

import jakarta.persistence.Column
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.springframework.data.domain.Persistable
import java.util.UUID
import de.huxhorn.sulky.ulid.ULID
import jakarta.persistence.PostLoad
import jakarta.persistence.PostPersist
import org.hibernate.proxy.HibernateProxy
import java.io.Serializable
import java.util.Objects

@MappedSuperclass
abstract class PrimaryKeyEntity : Persistable<String> {
    @Id
    @Column(columnDefinition = "CHAR(36)")
    private val id : String = ULID().nextValue().toString()

    @Transient
    private var _isNew : Boolean = true

    override fun getId() = id

    override fun isNew() = _isNew

    override fun equals(other: Any?): Boolean {
        if(other == null){
            return false
        }

        if(other !is HibernateProxy && this::class != other::class){
            return false
        }

        return id == getIdentifier(other)
    }

    private fun getIdentifier(obj: Any) : Serializable {
        return if(obj is HibernateProxy){
            obj.hibernateLazyInitializer.identifier as Serializable
        }else{
            (obj as PrimaryKeyEntity).id
        }
    }

    override fun hashCode(): Int = Objects.hashCode(id)

    @PostPersist
    @PostLoad
    protected fun load(){
        _isNew = false
    }
}