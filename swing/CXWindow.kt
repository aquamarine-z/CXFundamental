package cxplugins.cxfundamental.minecraft.swing

import org.bukkit.inventory.Inventory

typealias SwingWindow = CXWindow

interface CXWindow : CXContainer {
    fun constructInventory(): Inventory
    var title: String
}