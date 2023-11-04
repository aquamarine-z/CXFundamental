package cxplugins.cxfundamental.minecraft.swing.dsl

import cxplugins.cxfundamental.minecraft.kotlindsl.toColor
import cxplugins.cxfundamental.minecraft.server.CXItemStack
import cxplugins.cxfundamental.minecraft.swing.SwingItemContainer
import org.bukkit.Material
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

fun SwingItemContainer.itemChange(lambda: ((event: InventoryClickEvent) -> Unit)) {
    itemChangeLambda = lambda
}

fun SwingItemContainer.defaultItemInside() {
    this.itemInside = CXItemStack(Material.STONE, 1, "Default Item", "Default Item Lore", 0)
}

private fun SwingItemContainer.checkItemExist() {
    if (itemInside == null) defaultItemInside()
}

fun SwingItemContainer.title(title: String) {
    checkItemExist()
    this.itemInside?.itemMeta?.displayName = title.toColor()
}

fun SwingItemContainer.description(content: String) {
    checkItemExist()
    this.itemInside?.itemMeta?.lore = content.split("|")
}

fun SwingItemContainer.description(content: List<String>) {
    checkItemExist()
    this.itemInside?.itemMeta?.lore = content
}

fun SwingItemContainer.id(id: Int) {
    checkItemExist()
    this.itemInside?.typeId = id
}

fun SwingItemContainer.type(type: Material) {
    checkItemExist()
    this.itemInside?.type = type
}

fun SwingItemContainer.durability(number: Short) {
    checkItemExist()
    this.itemInside?.durability = number
}

fun SwingItemContainer.amount(amount: Int) {
    checkItemExist()
    this.itemInside?.amount = amount
}

fun SwingItemContainer.itemMeta(itemMetaContent: ItemMeta.() -> Unit) {
    checkItemExist()
    val meta = this.itemInside?.itemMeta
    meta?.apply(itemMetaContent)
    this.itemInside?.itemMeta = meta
}

fun SwingItemContainer.item(itemContent: ItemStack.() -> Unit) {
    checkItemExist()
    this.itemInside?.apply(itemContent)
}