package cxplugins.cxfundamental.minecraft.swing

import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

interface ItemContainable : Clickable {
    var itemInside: ItemStack?
    fun onItemChange(event: InventoryClickEvent)
}