package io.github.niconator97.lardexbackend.identity.persistence

import io.github.niconator97.lardexbackend.identity.domain.User
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface UserRepository : CrudRepository<User, UUID> {
    fun findByUserId(userId: UUID): User?

    fun findByEmail(email: String): User?
}