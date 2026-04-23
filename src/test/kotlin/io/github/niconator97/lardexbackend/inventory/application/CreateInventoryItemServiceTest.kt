package io.github.niconator97.lardexbackend.inventory.application

import io.github.niconator97.lardexbackend.shared.domain.PackageUnit
import io.github.niconator97.lardexbackend.inventory.persistance.InventoryItemRepository
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.util.UUID
import kotlin.test.assertEquals

class LardexBackendInventoryItemServiceTest {
    private val repository: InventoryItemRepository = mock()
    private val service = CreateInventoryItemService(repository)

    @Test
    fun `should create inventory item`() {
        val command = CreateInventoryItemCommand(
            householdId = UUID.randomUUID(),
            name = "Salami",
            brand = "Schlecht & Teuer",
            quantity = 3,
            packageUnit = PackageUnit.PACK,
            sizePerUnit = 100.0,
            sizeUnit = null,
            category = "MEAT",
            storageLocation = "FRIDGE",
            note = null,
            createdBy = UUID.randomUUID()
        )

        whenever(repository.save(org.mockito.kotlin.any()))
            .thenAnswer { it.arguments[0] }

        val result = service.create(command)

        assertEquals("Salami", result.name)
        assertEquals(100.0, result.sizePerUnit)

        verify(repository).save(org.mockito.kotlin.any())
    }
}