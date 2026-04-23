package io.github.niconator97.lardexbackend.infrastructure.web

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import com.fasterxml.jackson.databind.exc.InvalidFormatException
@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationException(
        ex: MethodArgumentNotValidException
    ): ResponseEntity<ApiError> {

        val details = ex.bindingResult.allErrors.map { error ->
            val field = (error as? FieldError)?.field

            ApiErrorDetail(
                field = field,
                message = error.defaultMessage ?: "invalid value"
            )
        }

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(
                ApiError(
                    error = "Validation failed",
                    details = details
                )
            )
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleJsonParseError(
        ex: HttpMessageNotReadableException
    ): ResponseEntity<ApiError> {
        val cause = ex.cause

        if (cause is InvalidFormatException) {
            val field = cause.path.lastOrNull()?.fieldName
            val targetType = cause.targetType

            if (targetType.isEnum) {
                val allowedValues = targetType.enumConstants
                    ?.joinToString(", ") { it.toString() }
                    ?: ""

                return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(
                        ApiError(
                            error = "Malformed request",
                            details = listOf(
                                ApiErrorDetail(
                                    field = field,
                                    message = "invalid value. Allowed values: '$allowedValues'",
                                )
                            )
                        )
                    )
            }
        }
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(
                ApiError(
                    error = "Malformed request",
                    details = listOf(
                        ApiErrorDetail(
                            field = null,
                            message = "request body could not be parsed"
                        )
                    )
                )
            )
    }
}