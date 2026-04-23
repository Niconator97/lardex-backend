package io.github.niconator97.lardexbackend.shoppinglist.application

import io.github.niconator97.lardexbackend.inventory.domain.InventoryItem
import io.github.niconator97.lardexbackend.shoppinglist.domain.ShoppingListItem
import io.github.niconator97.lardexbackend.shoppinglist.persistence.ShoppingListItemRepository
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.UUID

@Service
class GetShoppingListItemsService (
    private val repository: ShoppingListItemRepository
) {
    fun getShoppingListItems(householdId: UUID): List<ShoppingListItem> =
        repository.findAllByHouseholdId(householdId)
}