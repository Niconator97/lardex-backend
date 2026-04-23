package io.github.niconator97.lardexbackend.shoppinglist.application

import io.github.niconator97.lardexbackend.shoppinglist.domain.ShoppingListItem
import io.github.niconator97.lardexbackend.shoppinglist.persistence.ShoppingListItemRepository
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.UUID

@Service
class CreateShoppingListItemService (
    private val repository: ShoppingListItemRepository
) {
    fun create(command: CreateShoppingListItemCommand): ShoppingListItem {
        val now = Instant.now()

        val item = ShoppingListItem(
            id = UUID.randomUUID(),
            householdId = command.householdId,
            name = command.name,
            brand = command.brand,
            quantity = command.quantity,
            packageUnit = command.packageUnit,
            sizePerUnit = command.sizePerUnit,
            sizeUnit = command.sizeUnit,
            category = command.category,
            note = command.note,
            status = command.status,
            createdBy = command.createdBy,
            createdAt = now,
            updatedAt = now
        )

        return repository.save(item)
    }
}