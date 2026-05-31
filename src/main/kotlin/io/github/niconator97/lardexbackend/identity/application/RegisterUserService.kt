package io.github.niconator97.lardexbackend.identity.application

import io.github.niconator97.lardexbackend.identity.domain.User
import io.github.niconator97.lardexbackend.identity.persistence.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.UUID

@Service
class RegisterUserService(
    private val repository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun register(command: RegisterUserCommand): User {
        val householdId = if (command.createHousehold) {
            UUID.randomUUID()
        } else {
            null
        }

        val user = User(
            userId = UUID.randomUUID(),
            firstName = command.firstname,
            lastName = command.lastname,
            email = command.email.lowercase(),
            householdId = householdId,
            passwordHash = requireNotNull(passwordEncoder.encode(command.password)),
            createdAt = Instant.now(),
        )

        return repository.save(user)
    }
}