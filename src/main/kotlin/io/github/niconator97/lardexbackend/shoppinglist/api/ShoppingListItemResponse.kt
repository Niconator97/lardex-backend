package io.github.niconator97.lardexbackend.shoppinglist.api

import io.github.niconator97.lardexbackend.shoppinglist.domain.ShoppingListItem
import java.time.Instant
import java.util.UUID

data class ShoppingListItemResponse (
    val id: UUID,
    val householdId: UUID,
    val name: String,
    val brand: String?,
    val quantity: Int,
    val packageUnit: String,
    val sizePerUnit: Double?,
    val sizeUnit: String?,
    val category: String?,
    val note: String?,
    val createdBy: UUID,
    val createdAt: Instant,
    val updatedAt: Instant
    ) {
    companion object {
        fun from(item: ShoppingListItem): ShoppingListItemResponse =
            ShoppingListItemResponse(
                id = item.id,
                householdId = item.householdId,
                name = item.name,
                brand = item.brand,
                quantity = item.quantity,
                packageUnit = item.packageUnit.name,
                sizePerUnit = item.sizePerUnit,
                sizeUnit = item.sizeUnit?.name,
                category = item.category,
                note = item.note,
                createdBy = item.createdBy,
                createdAt = item.createdAt,
                updatedAt = item.updatedAt
            )
    }
}