package cxplugins.cxfundamental.minecraft.swing

import cxplugins.cxfundamental.minecraft.math.geometry.Vector2I
import org.bukkit.inventory.ItemStack

interface SwingComponent {
    var id: String?
    fun repaint(): Array<Array<ItemStack?>>
    val position: Vector2I
    fun relocate(x: Int, y: Int)
    fun relocate(point: Vector2I)
    var height: Int
    var width: Int
}