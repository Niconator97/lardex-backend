package io.github.niconator97.lardexbackend.identity.api

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class LoginUserRequest(
    @field:Email
    @field:NotBlank
    val email: String,

    @field:NotBlank
    val password: String
)