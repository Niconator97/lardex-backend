package io.github.niconator97.lardexbackend.inventory.application

import io.github.niconator97.lardexbackend.inventory.domain.InventoryItem
import io.github.niconator97.lardexbackend.inventory.persistance.InventoryItemRepository
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.UUID

@Service
class CreateInventoryItemService(
    private val repository: InventoryItemRepository
) {

    fun create(command: CreateInventoryItemCommand): InventoryItem {
        val now = Instant.now()

        val item = InventoryItem(
            id = UUID.randomUUID(),
            householdId = command.householdId,
            name = command.name,
            brand = command.brand,
            quantity = command.quantity,
            packageUnit = command.packageUnit,
            sizePerUnit = command.sizePerUnit,
            sizeUnit = command.sizeUnit,
            category = command.category,
            storageLocation = command.storageLocation,
            note = command.note,
            createdBy = command.createdBy,
            createdAt = now,
            updatedAt = now
        )

        return repository.save(item)
    }
}