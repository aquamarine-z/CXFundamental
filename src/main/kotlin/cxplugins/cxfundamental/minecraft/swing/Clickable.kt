package cxplugins.cxfundamental.minecraft.swing

import org.bukkit.event.inventory.InventoryClickEvent

interface Clickable {
    fun onClick(event: InventoryClickEvent)
}