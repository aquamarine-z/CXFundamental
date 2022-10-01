package cxplugins.cxfundamental.minecraft.swing

import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
typealias SwingButtonEvent=CXButtonEvent
data class CXButtonEvent(val event: InventoryClickEvent,val frame:CXFrame,val player: Player,val button:CXButton) {
}