package io.github.niconator97.lardexbackend.infrastructure.security

import java.util.UUID

//More attributes may be added as needed

data class AuthenticatedUser(
    val userId: UUID
)
