package cxplugins.cxfundamental.minecraft.swing

import cxplugins.cxfundamental.minecraft.math.geometry.Vector2I
import cxplugins.cxfundamental.minecraft.server.CXItemStack
import org.bukkit.Material
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

open class SwingButton(
    override val position: Vector2I, item: ItemStack = CXItemStack(
        Material.STONE_BUTTON,
        1,
        "Button",
        "Button"

    )
) : SwingItemHolder(position, item) {
    override fun onClick(event: InventoryClickEvent) {
        super.onClick(event)
        if (event.isShiftClick && event.isLeftClick) {
            onLeftShiftClick(event)
        }
        if (event.isLeftClick) {
            onLeftClick(event)
            return
        }
        if (event.isRightClick) {
            onRightClick(event)
            return
        }

    }

    open fun onLeftClick(event: InventoryClickEvent) {
        if (leftClickLambda != null) leftClickLambda!!(event)
    }

    open fun onRightClick(event: InventoryClickEvent) {
        if (rightClickLambda != null) rightClickLambda!!(event)
    }

    open fun onLeftShiftClick(event: InventoryClickEvent) {
        if (leftShiftClickLambda != null) leftShiftClickLambda!!(event)
    }

    open fun onRightShiftClick(event: InventoryClickEvent) {
        if (rightShiftClickLambda != null) rightShiftClickLambda!!(event)
    }

    var leftClickLambda: ((event: InventoryClickEvent) -> Unit)? = null
    var rightClickLambda: ((event: InventoryClickEvent) -> Unit)? = null
    var leftShiftClickLambda: ((event: InventoryClickEvent) -> Unit)? = null
    var rightShiftClickLambda: ((event: InventoryClickEvent) -> Unit)? = null
}