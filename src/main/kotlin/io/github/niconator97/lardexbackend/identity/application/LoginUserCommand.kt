package io.github.niconator97.lardexbackend.identity.application

data class LoginUserCommand(
    val email: String,
    val password: String
)