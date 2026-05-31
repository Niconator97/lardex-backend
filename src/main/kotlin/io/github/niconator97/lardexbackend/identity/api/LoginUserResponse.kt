package io.github.niconator97.lardexbackend.identity.api

data class LoginUserResponse(
    val accessToken: String,
    val tokenType: String = "Bearer"
)