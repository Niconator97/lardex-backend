package io.github.niconator97.lardexbackend.inventory.application

import io.github.niconator97.lardexbackend.inventory.domain.InventoryItem
import io.github.niconator97.lardexbackend.inventory.persistance.InventoryItemRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class GetInventoryItemsService (
    private val repository: InventoryItemRepository
) {
    fun getAllByHouseholdId(householdId: UUID): List<InventoryItem> =
        repository.findAllByHouseholdId(householdId)
}