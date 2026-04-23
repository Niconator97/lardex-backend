package io.github.niconator97.lardexbackend.inventory.api

import io.github.niconator97.lardexbackend.shared.domain.PackageUnit
import io.github.niconator97.lardexbackend.shared.domain.SizeUnit
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.NotBlank

@Schema(description = "Request payload for creating an inventory item")
data class CreateInventoryItemRequest (

    @field:NotBlank
    @Schema(example = "Salami")
    val name: String,

    val brand: String?,

    @field:NotNull
    @field:Min(1)
    val quantity: Int,

    @field:NotNull
    val packageUnit: PackageUnit,

    @Schema(description = "Size of package unit. For example a BOTTLE of 1,5 litres. Can be null (example 1 PACK)")
    val sizePerUnit: Double?,

    @Schema(
        description = "Unit of sizePerUnit. Null if no size is specified.",
        example = "GRAM"
    )
    val sizeUnit: SizeUnit?,

    val category: String?,

    val storageLocation: String?,

    val note: String?
)