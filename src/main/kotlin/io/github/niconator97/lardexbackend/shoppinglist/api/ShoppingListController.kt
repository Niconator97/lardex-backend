package io.github.niconator97.lardexbackend.shoppinglist.api

import io.github.niconator97.lardexbackend.infrastructure.security.CurrentUserContextProvider
import io.github.niconator97.lardexbackend.inventory.persistance.InventoryItemRepository
import io.github.niconator97.lardexbackend.shoppinglist.application.CreateShoppingListItemCommand
import io.github.niconator97.lardexbackend.shoppinglist.application.CreateShoppingListItemService
import io.github.niconator97.lardexbackend.shoppinglist.application.GetShoppingListItemsService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/api/v1/shoppingList/items")
class ShoppingListController (
    private val currentUserContextProvider: CurrentUserContextProvider,
    private val createShoppingListItemService: CreateShoppingListItemService,
    private val getShoppingListItemsService: GetShoppingListItemsService,
) {

    @PostMapping
    fun create(
        @Valid @RequestBody request: CreateShoppingListItemRequest
    ): ResponseEntity<ShoppingListItemResponse> {
        val currentUser = currentUserContextProvider.getCurrentUser()

        val created = createShoppingListItemService.create(
            CreateShoppingListItemCommand(
                householdId = currentUser.householdId,
                name = request.name,
                brand = request.brand,
                quantity = request.quantity,
                packageUnit = request.packageUnit,
                sizePerUnit = request.sizePerUnit,
                sizeUnit = request.sizeUnit,
                category = request.category,
                note = request.note,
                status = request.status,
                createdBy = currentUser.userId
            )
        )
        val response = ShoppingListItemResponse.from(created)
        return ResponseEntity
            .created(URI.create("/api/v1/shopinglist/items/${created.id}"))
            .body(response)
    }

    @GetMapping
    fun getAll(): List<ShoppingListItemResponse> {
        val currentUser = currentUserContextProvider.getCurrentUser()

        return getShoppingListItemsService.getShoppingListItems(currentUser.householdId)
            .map(ShoppingListItemResponse::from)
    }
}