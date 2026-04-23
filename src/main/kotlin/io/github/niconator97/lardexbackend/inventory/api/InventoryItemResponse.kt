package io.github.niconator97.lardexbackend.inventory.api

import io.github.niconator97.lardexbackend.inventory.domain.InventoryItem
import java.time.Instant
import java.util.UUID

data class InventoryItemResponse (
    val id: UUID,
    val householdId: UUID,
    val name: String,
    val brand: String?,
    val quantity: Int,
    val packageUnit: String,
    val sizePerUnit: Double?,
    val sizeUnit: String?,
    val category: String?,
    val storageLocation: String?,
    val note: String?,
    val createdBy: UUID,
    val createdAt: Instant,
    val updatedAt: Instant
) {
    companion object {
        fun from(item: InventoryItem): InventoryItemResponse =
            InventoryItemResponse(
                id = item.id,
                householdId = item.householdId,
                name = item.name,
                brand = item.brand,
                quantity = item.quantity,
                packageUnit = item.packageUnit.name,
                sizePerUnit = item.sizePerUnit,
                sizeUnit = item.sizeUnit?.name,
                category = item.category,
                storageLocation = item.storageLocation,
                note = item.note,
                createdBy = item.createdBy,
                createdAt = item.createdAt,
                updatedAt = item.updatedAt
            )
    }
}