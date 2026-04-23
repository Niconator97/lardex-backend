package io.github.niconator97.lardexbackend.shoppinglist.api

import io.github.niconator97.lardexbackend.shared.domain.PackageUnit
import io.github.niconator97.lardexbackend.shared.domain.SizeUnit
import io.github.niconator97.lardexbackend.shoppinglist.domain.ShoppingListItemStatus
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class CreateShoppingListItemRequest (

    @field:NotBlank
    val name: String,

    val brand: String?,

    @field:NotNull
    @field:Min(1)
    val quantity: Int,

    @field:NotNull
    val packageUnit: PackageUnit,

    val sizePerUnit: Double?,

    val sizeUnit: SizeUnit?,

    val category: String?,

    val status: ShoppingListItemStatus,

    val note: String?
)