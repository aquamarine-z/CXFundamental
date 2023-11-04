package cxplugins.cxfundamental.minecraft.swing.dsl

import cxplugins.cxfundamental.minecraft.math.geometry.Vector2I
import cxplugins.cxfundamental.minecraft.swing.*

fun SwingContainer.multipagePanel(x: Int, y: Int, width: Int, height: Int, content: SwingMultipagePanel.() -> Unit) {
    val panelToAdd = SwingMultipagePanel(Vector2I(x, y), width, height)
    panelToAdd.apply(content)
    this.setComponent(panelToAdd)
}

fun SwingContainer.panel(x: Int, y: Int, width: Int, height: Int, content: SwingPanel.() -> Unit) {
    val panelToAdd = SwingPanel(Vector2I(x, y), width, height)
    panelToAdd.apply(content)
    this.setComponent(panelToAdd)
}

fun SwingContainer.size(s: Int) {
    this.height = s
    this.width = s
}

fun SwingContainer.size(width: Int, height: Int) {
    this.width = width
    this.height = height
}


fun SwingContainer.button(x: Int, y: Int, content: SwingButton.() -> Unit) {
    val buttonToAdd = SwingButton(Vector2I(x, y))
    buttonToAdd.apply(content)
    this.setComponent(buttonToAdd)
}

fun SwingContainer.itemHolder(x: Int, y: Int, content: SwingItemHolder.() -> Unit) {
    val itemHolderToAdd = SwingItemHolder(Vector2I(x, y))
    itemHolderToAdd.apply(content)
    this.setComponent(itemHolderToAdd)
}
