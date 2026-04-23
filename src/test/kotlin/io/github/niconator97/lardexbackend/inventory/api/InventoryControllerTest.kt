package io.github.niconator97.lardexbackend.inventory.api

import io.github.niconator97.lardexbackend.infrastructure.security.CurrentUserContext
import io.github.niconator97.lardexbackend.infrastructure.security.CurrentUserContextProvider
import io.github.niconator97.lardexbackend.inventory.application.CreateInventoryItemCommand
import io.github.niconator97.lardexbackend.inventory.application.CreateInventoryItemService
import io.github.niconator97.lardexbackend.inventory.application.GetInventoryItemsService
import io.github.niconator97.lardexbackend.inventory.domain.InventoryItem
import io.github.niconator97.lardexbackend.shared.domain.PackageUnit
import io.github.niconator97.lardexbackend.shared.domain.SizeUnit
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.whenever
import org.mockito.kotlin.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import tools.jackson.module.kotlin.jacksonObjectMapper
import java.time.Instant
import java.util.UUID

@WebMvcTest(InventoryController::class)
@AutoConfigureMockMvc(addFilters = false)
class InventoryControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockitoBean
    lateinit var createInventoryItemService: CreateInventoryItemService

    @MockitoBean
    lateinit var getInventoryItemsService: GetInventoryItemsService

    @MockitoBean
    lateinit var currentUserContextProvider: CurrentUserContextProvider

    private val objectMapper = jacksonObjectMapper()

    @Test
    fun `should create item using current user context`() {
        val householdId = UUID.randomUUID()
        val userId = UUID.randomUUID()
        val itemId = UUID.randomUUID()
        val now = Instant.now()

        whenever(currentUserContextProvider.getCurrentUser())
            .thenReturn(CurrentUserContext(userId, householdId))

        val request = CreateInventoryItemRequest(
            name = "Salami",
            brand = "Gut & Günstig",
            quantity = 3,
            packageUnit = PackageUnit.PACK,
            sizePerUnit = 100.0,
            sizeUnit = SizeUnit.GRAM,
            category = "MEAT",
            storageLocation = "FRIDGE",
            note = null
        )

        val saved = InventoryItem(
            id = itemId,
            householdId = householdId,
            name = "Salami",
            brand = "Gut & Günstig",
            quantity = 3,
            packageUnit = PackageUnit.PACK,
            sizePerUnit = 100.0,
            sizeUnit = SizeUnit.GRAM,
            category = "MEAT",
            storageLocation = "FRIDGE",
            note = null,
            createdBy = userId,
            createdAt = now,
            updatedAt = now
        )

        whenever(createInventoryItemService.create(any())).thenReturn(saved)

        mockMvc.post("/api/v1/inventory/items") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(request)
        }
            .andExpect {
                status { isCreated() }
                header { string("Location", "/api/v1/inventory/items/$itemId") }
                jsonPath("$.id") { value(itemId.toString()) }
                jsonPath("$.name") { value("Salami") }
            }

        verify(createInventoryItemService).create(
            eq(
                CreateInventoryItemCommand(
                    householdId = householdId,
                    createdBy = userId,
                    name = "Salami",
                    brand = "Gut & Günstig",
                    quantity = 3,
                    packageUnit = PackageUnit.PACK,
                    sizePerUnit = 100.0,
                    sizeUnit = SizeUnit.GRAM,
                    category = "MEAT",
                    storageLocation = "FRIDGE",
                    note = null
                )
            )
        )
    }

    @Test
    fun `should return all items for current household`() {
        val householdId = UUID.randomUUID()
        val userId = UUID.randomUUID()

        whenever(currentUserContextProvider.getCurrentUser())
            .thenReturn(CurrentUserContext(userId, householdId))

        val item1 = InventoryItem(
            id = UUID.randomUUID(),
            householdId = householdId,
            name = "Salami",
            brand = "Gut & Günstig",
            quantity = 3,
            packageUnit = PackageUnit.PACK,
            sizePerUnit = 100.0,
            sizeUnit = SizeUnit.GRAM,
            category = "MEAT",
            storageLocation = "FRIDGE",
            note = null,
            createdBy = userId,
            createdAt = Instant.now(),
            updatedAt = Instant.now()
        )

        val item2 = InventoryItem(
            id = UUID.randomUUID(),
            householdId = householdId,
            name = "Milch",
            brand = "Ja!",
            quantity = 2,
            packageUnit = PackageUnit.BOTTLE,
            sizePerUnit = 1.0,
            sizeUnit = SizeUnit.LITER,
            category = "DAIRY",
            storageLocation = "FRIDGE",
            note = null,
            createdBy = userId,
            createdAt = Instant.now(),
            updatedAt = Instant.now()
        )

        whenever(getInventoryItemsService.getAllByHouseholdId(householdId))
            .thenReturn(listOf(item1, item2))

        mockMvc.get("/api/v1/inventory/items")
            .andExpect {
                status { isOk() }
                jsonPath("$.length()") { value(2) }
                jsonPath("$[0].name") { value(item1.name) }
                jsonPath("$[1].name") { value(item2.name) }
            }
    }
}