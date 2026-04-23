package io.github.niconator97.lardexbackend.shoppinglist.persistence

import io.github.niconator97.lardexbackend.shoppinglist.domain.ShoppingListItem
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface ShoppingListItemRepository : CrudRepository<ShoppingListItem, UUID> {
    fun findAllByHouseholdId(householdId: UUID): List<ShoppingListItem>
}