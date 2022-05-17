package com.example.uyangi.domain.user

import java.util.*
import com.example.uyangi.domain.jpa.BaseEntity
import javax.persistence.*

@Entity(name = "user")
class User(
    var email: String,
    var password: String,
    var name: String,
    var fcmToken: String?
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var createdAt: Date? = null
    var updatedAt: Date? = null

    @PrePersist
    fun prePersist() {
        createdAt = Date()
        updatedAt = Date()
    }

    @PreUpdate
    fun preUpdate() {
        updatedAt = Date()
    }

}