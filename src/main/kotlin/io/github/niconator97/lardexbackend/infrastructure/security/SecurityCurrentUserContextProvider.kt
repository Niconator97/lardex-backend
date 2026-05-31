package io.github.niconator97.lardexbackend.infrastructure.security

import io.github.niconator97.lardexbackend.identity.persistence.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class SecurityCurrentUserContextProvider(
    private val userRepository: UserRepository
) : CurrentUserContextProvider {

    override fun getCurrentUser(): CurrentUserContext {
        val principal = SecurityContextHolder.getContext()
            .authentication
            ?.principal as? AuthenticatedUser
            ?: throw IllegalStateException("No authenticated user found")

        val user = userRepository.findById(principal.userId)
            .orElseThrow { IllegalStateException("Authenticated user not found") }

        return CurrentUserContext(
            userId = user.userId,
            householdId = user.householdId
        )
    }
}