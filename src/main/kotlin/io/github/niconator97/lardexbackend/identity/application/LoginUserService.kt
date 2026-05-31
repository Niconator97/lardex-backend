package io.github.niconator97.lardexbackend.identity.application

import io.github.niconator97.lardexbackend.identity.persistence.UserRepository
import io.github.niconator97.lardexbackend.infrastructure.security.JwtTokenService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class LoginUserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenService: JwtTokenService
) {

    fun login(command: LoginUserCommand): String {
        val user = userRepository.findByEmail(command.email.lowercase())
            ?: throw InvalidCredentialsException()

        val passwordMatches = passwordEncoder.matches(
            command.password,
            user.passwordHash
        )

        if (!passwordMatches) {
            throw InvalidCredentialsException()
        }

        return jwtTokenService.createAccessToken(user.userId)
    }
}