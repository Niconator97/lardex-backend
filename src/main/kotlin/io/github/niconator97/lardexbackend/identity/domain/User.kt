package io.github.niconator97.lardexbackend.identity.domain

import org.springframework.data.annotation.Id
import java.time.Instant
import java.util.UUID

data class User (
    @Id
    val userId: UUID,
    val firstName: String,
    val lastName: String,
    val householdId: UUID?,
    val email: String,
    val passwordHash: String,
    val createdAt: Instant
)