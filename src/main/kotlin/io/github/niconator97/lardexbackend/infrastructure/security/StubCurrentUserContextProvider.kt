package io.github.niconator97.lardexbackend.infrastructure.security

import org.springframework.stereotype.Component
import java.util.UUID

//TODO: Rework for real authentification

@Component
class StubCurrentUserContextProvider : CurrentUserContextProvider {
    override fun getCurrentUser(): CurrentUserContext =
        CurrentUserContext(
            userId = UUID.fromString("11111111-1111-1111-1111-111111111111"),
            householdId = UUID.fromString("22222222-2222-2222-2222-222222222222")
        )
}