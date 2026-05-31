package io.github.niconator97.lardexbackend.identity.application

import io.github.niconator97.lardexbackend.identity.persistence.UserRepository
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.util.UUID
import kotlin.test.assertEquals

class RegisterUserServiceTest {

    private val repository: UserRepository = mock()

    private val service = RegisterUserService(repository)

    @Test
    fun `should register user`() {
        val command = RegisterUserCommand(
            firstname = "John",
            lastname = "Doe",
            email = "john.doe@mail.com",
            password = "1234",
            createHousehold = true
        )

        whenever(repository.save(org.mockito.kotlin.any()))
            .thenAnswer { it.arguments[0] }

        val result = service.register(command)

        assertEquals("John",  result.firstName)
        assertEquals("Doe",  result.lastName)

        verify(repository).save(org.mockito.kotlin.any())
    }
}