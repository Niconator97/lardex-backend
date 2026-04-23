package io.github.niconator97.lardexbackend.infrastructure.security


//TODO: Rework for real authentification

interface CurrentUserContextProvider {
    fun getCurrentUser(): CurrentUserContext
}