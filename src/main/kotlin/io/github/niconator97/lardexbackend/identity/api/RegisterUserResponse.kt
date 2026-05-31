package io.github.niconator97.lardexbackend.identity.api

import io.github.niconator97.lardexbackend.identity.domain.User
import java.util.UUID

data class RegisterUserResponse(
    val userId : UUID,
    val firstname : String,
    val lastname : String,
    val householdId : UUID?
) {
    companion object {
        fun fromUser(user : User) : RegisterUserResponse =
            RegisterUserResponse(
                userId = user.userId,
                firstname = user.firstName,
                lastname = user.lastName,
                householdId = user.householdId
            )
    }
}