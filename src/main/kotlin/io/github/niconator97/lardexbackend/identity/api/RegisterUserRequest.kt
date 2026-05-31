package io.github.niconator97.lardexbackend.identity.api

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

class RegisterUserRequest(

    @field:NotBlank
    val firstName: String,

    @field:NotBlank
    val lastName: String,

    @field:Email
    val email: String,

    @field:NotBlank
    val password: String,

    @field:NotBlank
    val createHousehold: Boolean
)