package io.github.niconator97.lardexbackend.inventory.persistance

import io.github.niconator97.lardexbackend.inventory.domain.InventoryItem
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface InventoryItemRepository : CrudRepository<InventoryItem, UUID> {
    fun findAllByHouseholdId(householdId: UUID): List<InventoryItem>
}