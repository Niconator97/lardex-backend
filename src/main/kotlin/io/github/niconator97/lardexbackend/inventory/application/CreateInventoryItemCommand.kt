package io.github.niconator97.lardexbackend.inventory.application

import io.github.niconator97.lardexbackend.shared.domain.PackageUnit
import io.github.niconator97.lardexbackend.shared.domain.SizeUnit
import java.util.UUID

data class CreateInventoryItemCommand (
    val householdId: UUID,
    val name: String,
    val brand: String?,
    val quantity: Int,
    val packageUnit: PackageUnit,
    val sizePerUnit: Double?,
    val sizeUnit: SizeUnit?,
    val category: String?,
    val storageLocation: String?,
    val note: String?,
    val createdBy: UUID
)