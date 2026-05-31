package io.github.niconator97.lardexbackend.infrastructure.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.Date
import java.util.UUID
import javax.crypto.SecretKey

@Service
class JwtTokenService {

    //TODO: Change before prod. release
    private val secretKey: SecretKey =
        Keys.hmacShaKeyFor("CHANGE_ME_CHANGE_ME_CHANGE_ME_CHANGE_ME_123456".toByteArray())

    fun createAccessToken(userId: UUID): String {
        val now = Instant.now()
        val expiresAt = now.plusSeconds(60 * 60)

        return Jwts.builder()
            .subject(userId.toString())
            .issuedAt(Date.from(now))
            .expiration(Date.from(expiresAt))
            .signWith(secretKey)
            .compact()
    }

    fun extractUserId(token: String): UUID {
        val claims: Claims = Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .payload

        return UUID.fromString(claims.subject)
    }
}