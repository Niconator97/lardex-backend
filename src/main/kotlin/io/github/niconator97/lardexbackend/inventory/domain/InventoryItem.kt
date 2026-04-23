package io.github.niconator97.lardexbackend.inventory.domain

import io.github.niconator97.lardexbackend.shared.domain.PackageUnit
import io.github.niconator97.lardexbackend.shared.domain.SizeUnit
import org.springframework.data.annotation.Id
import java.time.Instant
import java.util.UUID

data class InventoryItem (
    @Id
    val id: UUID,
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
    val createdBy: UUID,
    val createdAt: Instant,
    val updatedAt: Instant
    )