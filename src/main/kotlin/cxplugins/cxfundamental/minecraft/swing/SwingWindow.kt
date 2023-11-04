package cxplugins.cxfundamental.minecraft.swing

import org.bukkit.inventory.Inventory

interface SwingWindow : SwingContainer {
    fun generateInventory(): Inventory
}