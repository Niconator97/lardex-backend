package io.github.niconator97.lardexbackend.shoppinglist.application

import io.github.niconator97.lardexbackend.shared.domain.PackageUnit
import io.github.niconator97.lardexbackend.shared.domain.SizeUnit
import io.github.niconator97.lardexbackend.shoppinglist.domain.ShoppingListItemStatus
import java.util.UUID

data class CreateShoppingListItemCommand (
    val householdId: UUID,
    val name: String,
    val brand: String?,
    val quantity: Int,
    val packageUnit: PackageUnit,
    val sizePerUnit: Double?,
    val sizeUnit: SizeUnit?,
    val category: String?,
    val note: String?,
    val status: ShoppingListItemStatus,
    val createdBy: UUID
)