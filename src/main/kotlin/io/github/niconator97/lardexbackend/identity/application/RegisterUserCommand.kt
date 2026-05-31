package io.github.niconator97.lardexbackend.identity.application

data class RegisterUserCommand(
    val firstname: String,
    val lastname: String,
    val email: String,
    val password: String,
    val createHousehold: Boolean
)