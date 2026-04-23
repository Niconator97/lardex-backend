package io.github.niconator97.lardexbackend.inventory.api

import io.github.niconator97.lardexbackend.infrastructure.security.CurrentUserContextProvider
import io.github.niconator97.lardexbackend.inventory.application.CreateInventoryItemCommand
import io.github.niconator97.lardexbackend.inventory.application.CreateInventoryItemService
import io.github.niconator97.lardexbackend.inventory.application.GetInventoryItemsService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/api/v1/inventory/items")
class InventoryController(
    private val createInventoryItemService: CreateInventoryItemService,
    private val getInventoryItemsService: GetInventoryItemsService,
    private val currentUserContextProvider: CurrentUserContextProvider
) {

    @Operation(
        summary = "Creates new item for home inventory",
        description = "Creates new item for an authenticated user's household inventory"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "201", description = "Item successfully created"),
            ApiResponse(responseCode = "400", description = "Item could not be created")
        ]
    )
    @PostMapping
    fun create(
        @Valid @RequestBody request: CreateInventoryItemRequest
    ): ResponseEntity<InventoryItemResponse> {
        val currentUser = currentUserContextProvider.getCurrentUser()

        val created = createInventoryItemService.create(
            CreateInventoryItemCommand(
                householdId = currentUser.householdId,
                name = request.name,
                brand = request.brand,
                quantity = request.quantity,
                packageUnit = request.packageUnit,
                sizePerUnit = request.sizePerUnit,
                sizeUnit = request.sizeUnit,
                category = request.category,
                storageLocation = request.storageLocation,
                note = request.note,
                createdBy = currentUser.userId
            )
        )
        val response = InventoryItemResponse.from(created)
        return ResponseEntity
            .created(URI.create("/api/v1/inventory/items/${created.id}"))
            .body(response)
    }

    @Operation(
        summary = "List of inventory items",
        description = "Returns a list of all items for authenticated user's household inventory"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Items returned successfully"),
        ]
    )
    @GetMapping
    fun getAll(): List<InventoryItemResponse> {
        val currentUser = currentUserContextProvider.getCurrentUser()

        return getInventoryItemsService.getAllByHouseholdId(currentUser.householdId)
            .map(InventoryItemResponse::from)
    }
}