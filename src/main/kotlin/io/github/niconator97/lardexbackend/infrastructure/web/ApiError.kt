package io.github.niconator97.lardexbackend.infrastructure.web

data class ApiError(
    val error: String,
    val details: List<ApiErrorDetail> = emptyList()
)

data class ApiErrorDetail(
    val field: String?,
    val message: String,
)
