package cxplugins.cxfundamental.minecraft.swing.dsl

import cxplugins.cxfundamental.minecraft.swing.SwingButton
import org.bukkit.event.inventory.InventoryClickEvent


fun SwingButton.onLeftClick(action: (event: InventoryClickEvent) -> Unit) {
    this.leftClickLambda = action
}

fun SwingButton.onRightClick(action: (event: InventoryClickEvent) -> Unit) {
    this.rightClickLambda = action
}

fun SwingButton.onLeftShiftClick(action: (event: InventoryClickEvent) -> Unit) {
    this.leftShiftClickLambda = action
}

fun SwingButton.onRightShiftClick(action: (event: InventoryClickEvent) -> Unit) {
    this.rightShiftClickLambda = action
}