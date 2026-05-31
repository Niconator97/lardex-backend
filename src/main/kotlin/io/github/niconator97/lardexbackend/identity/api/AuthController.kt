package io.github.niconator97.lardexbackend.identity.api

import io.github.niconator97.lardexbackend.identity.application.LoginUserCommand
import io.github.niconator97.lardexbackend.identity.application.LoginUserService
import io.github.niconator97.lardexbackend.identity.application.RegisterUserCommand
import io.github.niconator97.lardexbackend.identity.application.RegisterUserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(
    private val registerUserService: RegisterUserService,
    private val loginUserService: LoginUserService
) {

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    fun register(
        @Valid @RequestBody request: RegisterUserRequest
    ): RegisterUserResponse {
        val user = registerUserService.register(
            RegisterUserCommand(
                firstname = request.firstName,
                lastname = request.lastName,
                email = request.email,
                password = request.password,
                createHousehold = request.createHousehold
            )
        )

        return RegisterUserResponse.fromUser(user)
    }

    @PostMapping("/login")
    fun login(
        @Valid @RequestBody request: LoginUserRequest
    ): LoginUserResponse {
        val token = loginUserService.login(
            LoginUserCommand(
                email = request.email,
                password = request.password
            )
        )

        return LoginUserResponse(accessToken = token)
    }
}