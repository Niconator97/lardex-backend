package io.github.niconator97.lardexbackend.infrastructure.security

import java.util.UUID

//TODO: Rework for real authentification

data class CurrentUserContext(
    val userId: UUID,
    val householdId: UUID
)