package cxplugins.cxfundamental.minecraft.swing

import cxplugins.cxfundamental.minecraft.math.geometry.Vector2I
import cxplugins.cxfundamental.minecraft.server.CXItemStack
import org.bukkit.Material
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

open class SwingItemHolder(
    override val position: Vector2I, var item: ItemStack = CXItemStack(
        Material.STONE_BUTTON,
        1,
        "Button",
        "Swing Button Component"

    )
) : SwingComponent, Clickable {

    override fun repaint(): Array<Array<ItemStack?>> {
        return arrayOf(arrayOf(item))
    }

    override fun relocate(x: Int, y: Int) {
        this.position.x = x
        this.position.y = y
    }

    override fun relocate(point: Vector2I) {
        this.position.x = point.x
        this.position.y = point.y
    }

    override var height: Int = 1
    override var width: Int = 1
    override fun onClick(event: InventoryClickEvent) {
        event.isCancelled = true
    }
}