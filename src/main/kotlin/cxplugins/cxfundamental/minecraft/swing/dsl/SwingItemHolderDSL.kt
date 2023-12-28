package cxplugins.cxfundamental.minecraft.swing.dsl

import cxplugins.cxfundamental.minecraft.swing.SwingItemHolder
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta


var SwingItemHolder.title: String
    get() = this.item.itemMeta.displayName ?: ""
    set(value) {
        this.item.itemMeta.displayName = value
    }
var SwingItemHolder.description: List<String>
    get() {
        return this.item.itemMeta.lore ?: mutableListOf<String>()
    }
    set(value: List<String>) {
        this.item.itemMeta.lore = value
    }
var SwingItemHolder.typeId: Int
    get() = this.item.typeId
    set(value) {
        this.item.typeId = value
    }
var SwingItemHolder.type: Material
    get() = this.item.type
    set(value) {
        this.item.type = value
    }
var SwingItemHolder.durability
    get() = this.item.durability
    set(value) {
        this.item.durability = value
    }
var SwingItemHolder.meta
    get() = this.item.itemMeta
    set(value) {
        this.item.itemMeta = value
    }
var SwingItemHolder.amount
    get() = this.item.amount
    set(value) {
        this.item.amount = value
    }

fun SwingItemHolder.title(title: String) {
    this.title = title
}
fun SwingItemHolder.description(content: String) {
    this.item.itemMeta.lore = content.split("|")
}

fun SwingItemHolder.description(content: List<String>) {
    this.item.itemMeta.lore = content
}

fun SwingItemHolder.typeId(id: Int) {
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