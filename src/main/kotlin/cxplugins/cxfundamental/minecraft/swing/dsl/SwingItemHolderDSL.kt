package cxplugins.cxfundamental.minecraft.swing.dsl

import cxplugins.cxfundamental.minecraft.kotlindsl.toColor
import cxplugins.cxfundamental.minecraft.swing.SwingItemHolder
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

fun SwingItemHolder.title(title: String) {
    this.item.itemMeta.displayName = title.toColor()
}

fun SwingItemHolder.description(content: String) {
    this.item.itemMeta.lore = content.split("|")
}

fun SwingItemHolder.description(content: List<String>) {
    this.item.itemMeta.lore = content
}

fun SwingItemHolder.id(id: Int) {
    this.item.typeId = id
}

fun SwingItemHolder.type(type: Material) {
    this.item.type = type
}

fun SwingItemHolder.durability(number: Short) {
    this.item.durability = number
}

fun SwingItemHolder.amount(amount: Int) {
    this.item.amount = amount
}

fun SwingItemHolder.itemMeta(itemMetaContent: ItemMeta.() -> Unit) {
    val meta = this.item.itemMeta
    meta.apply(itemMetaContent)
    this.item.itemMeta = meta
}

fun SwingItemHolder.item(itemContent: ItemStack.() -> Unit) {
    this.item.apply(itemContent)
}