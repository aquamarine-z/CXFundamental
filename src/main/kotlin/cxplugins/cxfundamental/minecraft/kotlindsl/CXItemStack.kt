package cxplugins.cxfundamental.minecraft.kotlindsl

import cxplugins.cxfundamental.minecraft.file.CXYamlConfiguration
import cxplugins.cxfundamental.minecraft.server.CXItemStack
import cxplugins.cxfundamental.minecraft.server.nms.CraftItemStack
import cxplugins.cxfundamental.minecraft.server.nms.NBTEditor
import cxplugins.cxfundamental.minecraft.server.nms.NMSItemStack
import cxplugins.cxfundamental.minecraft.server.nms.itemnbt.AttributeModifier
import cxplugins.cxfundamental.minecraft.server.nms.itemnbt.AttributeOperation
import cxplugins.cxfundamental.minecraft.server.nms.itemnbt.ItemAttribute
import cxplugins.cxfundamental.minecraft.server.nms.itemnbt.NBTTagCompound
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

/**
 * 将此物品的数量减少指定的值
 *
 * @param Amount 减少的数量
 */
fun ItemStack.cost(Amount: Int) {
    this.amount = this.amount - Amount
}

/**
 * 生成一个此物品的拷贝 其中数量为参数amount
 *
 * @param amount 生成物品的数量
 * @return 物品的拷贝
 */
fun ItemStack.cloneWithAmount(amount: Int): ItemStack {
    var item = this.clone()
    item.amount = amount
    return item
}
/*
class ItemStack:ItemStacks(){
    companion object{
        @JvmStatic
        fun create(id:Int,amount:Int,display:String,lore:String,durability:Short=0):ItemStacks{
            var item=ItemStacks(id,amount,durability)
            var meta=item.itemMeta
            meta.displayName=display.toColor()
            meta.lore=lore.toColor().split("|").toMutableList()
            item.itemMeta=meta
            return item
        }
    }
}*/
/**
 * 在忽略数量的情况下 判断自身是否与参数Item相等
 *
 * @param Item 判断的物品
 * @return 若相等 则返回true 否则返回false
 */

fun ItemStack.equalsIgnoreAmount(Item: ItemStack): Boolean {
    var a = this
    var b = Item

    a = a.clone()
    b = b.clone()
    a.amount = 1
    b.amount = 1
    return a == b
}

/**
 * 在物品介绍中追加内容
 *
 * @param lore 追加的内容
 * @param index 追加的位置 默认为末尾
 */
fun ItemStack.appendLore(lore: String, index: Int = if (this.itemMeta.lore != null) this.itemMeta.lore.size else 0) {
    var meta = this.itemMeta
    var lores = meta.lore
    if (lores == null) lores = ArrayList<String>()
    try {
        lores.add(index, lore.toColor())
    } catch (exception: Exception) {
        return
    }
    meta.lore = lores
    this.itemMeta = meta
}

/**
 * 在物品介绍中删除内容
 *
 * @param line 删除的行数 默认为末尾
 */
fun ItemStack.removeLore(line: Int = this.itemMeta.lore.size - 1) {
    if (this.itemMeta.lore == null) return
    else if (this.itemMeta.lore.size <= line) return
    try {
        var meta = this.itemMeta
        var lores = meta.lore
        lores.removeAt(line)
        meta.lore = lores
        this.itemMeta = meta
    } catch (exception: Exception) {
        return
    }
}

/**
 * 设置物品展示名
 *
 * @param name 物品的展示名
 */
fun ItemStack.setDisplayName(name: String) {
    var meta = this.itemMeta
    meta.displayName = name
    this.itemMeta = meta
}

/**
 * 将此物品拷贝一份并设置NBT数据
 *
 * @param compound NBTCompound数据
 * @return 此物品设置NBT数据之后的拷贝
 */
fun ItemStack.setNBTTagToCopy(compound: Any?): ItemStack {
    var nmsItemStack = CraftItemStack.asNMSCopy(null, this)
    NMSItemStack.setTag(nmsItemStack, compound)
    return CraftItemStack.asBukkitCopy(null, nmsItemStack) as ItemStack
}

/**
 * 将此物品拷贝一份并设置NBT数据
 *
 * @param value 设置的数据值
 * @param path 数据的路径
 * @return 此物品设置NBT数据之后的拷贝
 */
fun ItemStack.setNBTValueToCopy(value: Any?, vararg path: String): ItemStack {
    return NBTEditor.setItemStackNBT(this, value, *path)
}

/**
 * 设置此物品的NBT数据
 *
 * @param compound NBTCompound数据
 */
fun ItemStack.setNBTTag(compound: Any?) {
    val item = this.setNBTTagToCopy(compound)
    this.itemMeta = item.itemMeta
}

/**
 * 设置此物品的NBT数据
 *
 * @param value 设置的数据值
 * @param path 数据的路径

 */
fun ItemStack.setNBTValue(value: Any?, vararg path: String) {
    val item = this.setNBTValueToCopy(value, *path)
    this.itemMeta = item.itemMeta
}

fun ItemStack.setNBTValueBySerialization(value: Any?, vararg path: String) {
    val configuration = CXYamlConfiguration()
    configuration.loadFromString("")
    configuration["Value"] = value
    val serialized = configuration.saveToString()
    this.setNBTValue(serialized, *path)
}

fun ItemStack.getNBTValueBySerialization(vararg path: String): Any? {
    val configuration = CXYamlConfiguration()
    try {
        configuration.loadFromString((this.getNBTValue(*path) as? String) ?: "")
        return configuration["Value"]
    } catch (exception: Exception) {
        return null
    }


}

/**
 * 获取此物品的某个NBT数据
 *
 * @param path 此数据的路径
 * @return 获取的NBT数据
 */
fun ItemStack.getNBTValue(vararg path: String): Any? {
    return NBTEditor.getItemStackNBT(this, *path)
}

/**
 * 获取此物品的某个NBT
 *
 * @return 获取的NBT
 */
fun ItemStack.getNBTTag(): Any {
    var nmsItemStack = CraftItemStack.asNMSCopy(null, this)
    return NMSItemStack.getTag(nmsItemStack)

}

/**
 * 判断是否有某个NBT数据
 *
 * @param path 此数据的路径
 * @return 是否有此数据
 */
fun ItemStack.hasNBTValue(vararg path: String): Boolean {
    return getNBTValue(*path) != null
}

/**
 * Kotlin DSL对于ItemMeta的设置
 *
 * @param lambda 设置ItemMeta的lambda表达式
 */
fun CXItemStack.meta(lambda: ItemMeta.() -> Unit) {
    var meta = this.itemMeta
    meta.also(lambda)
    this.itemMeta = meta
}

/**
 * Kotlin DSL对于Attribute的设置
 *
 * @param lambda 设置Attribute的lambda表达式
 */
fun CXItemStack.attribute(lambda: ItemAttributeCompound.() -> Unit) {
    var compound = ItemAttributeCompound()
    compound.apply(lambda)
    var item = this.clone()
    for (modifier in compound.attributeList) {
        item = NBTEditor.setItemAttribute(item, modifier)
    }
    this.itemMeta = item.itemMeta.clone()

}

/**
 * Kotlin DSL对于物品伤害的设置
 *
 * @param lambda 设置属性的lambda表达式
 */
fun ItemAttributeCompound.attackDamage(lambda: AttributeModifier.() -> Unit) {
    var modifier =
        AttributeModifier("attackDamage", 0.0, ItemAttribute.GENERIC_ATTACK_DAMAGE, AttributeOperation.ADD_NUMBER)
    modifier.apply(lambda)
    this.attributeList.add(modifier)

}

/**
 * Kotlin DSL对于物品护甲值的设置
 *
 * @param lambda 设置属性的lambda表达式
 */
fun ItemAttributeCompound.amour(lambda: AttributeModifier.() -> Unit) {
    var modifier = AttributeModifier("amour", 0.0, ItemAttribute.GENERIC_AMOUR, AttributeOperation.ADD_NUMBER)
    modifier.apply(lambda)
    this.attributeList.add(modifier)

}

/**
 * Kotlin DSL对于物品攻速的设置
 *
 * @param lambda 设置属性的lambda表达式
 */
fun ItemAttributeCompound.attackSpeed(lambda: AttributeModifier.() -> Unit) {
    var modifier =
        AttributeModifier("attackSpeed", 0.0, ItemAttribute.GENERIC_ATTACK_SPEED, AttributeOperation.ADD_NUMBER)
    modifier.apply(lambda)
    this.attributeList.add(modifier)

}

/**
 * Kotlin DSL对于物品幸运的设置
 *
 * @param lambda 设置属性的lambda表达式
 */
fun ItemAttributeCompound.luck(lambda: AttributeModifier.() -> Unit) {
    var modifier = AttributeModifier("luck", 0.0, ItemAttribute.GENERIC_LUCK, AttributeOperation.ADD_NUMBER)
    modifier.apply(lambda)
    this.attributeList.add(modifier)
}

/**
 * Kotlin DSL对于物品移速的设置
 *
 * @param lambda 设置属性的lambda表达式
 */
fun ItemAttributeCompound.movementSpeed(lambda: AttributeModifier.() -> Unit) {
    var modifier =
        AttributeModifier("movementSpeed", 0.0, ItemAttribute.GENERIC_MOVEMENT_SPEED, AttributeOperation.ADD_NUMBER)
    modifier.apply(lambda)
    this.attributeList.add(modifier)
}

/**
 * Kotlin DSL对于物品最大生命值的设置
 *
 * @param lambda 设置属性的lambda表达式
 */
fun ItemAttributeCompound.maxHealth(lambda: AttributeModifier.() -> Unit) {
    var modifier = AttributeModifier("maxHealth", 0.0, ItemAttribute.GENERIC_MAX_HEALTH, AttributeOperation.ADD_NUMBER)
    modifier.apply(lambda)
    this.attributeList.add(modifier)
}

/**
 * Kotlin DSL对于物品NBT附加值的设置
 *
 * @param lambda 设置NBT附加值的lambda表达式
 */
fun CXItemStack.tag(lambda: NBTTagCompound.() -> Unit) {
    var compound = NBTTagCompound()
    compound.value = this.getNBTTag()
    compound.apply(lambda)
    var item = this.clone()
    var nmsItem = CraftItemStack.asNMSCopy(null, item)
    NMSItemStack.setTag(nmsItem, compound.value)
    item = CraftItemStack.asBukkitCopy(null, nmsItem) as ItemStack
    this.itemMeta = item.itemMeta.clone()
}

/**
 * Kotlin DSL拷贝一个物品并设置相关属性的方法
 *
 * @param lambda 设置物品属性的lambda表达式
 */
fun CXItemStack.Companion.copy(srcItemStack: CXItemStack, lambda: ItemStack.() -> Unit): CXItemStack {
    srcItemStack.apply(lambda)
    return srcItemStack
}

fun CXItemStack.edit(lambda: ItemStack.() -> Unit): Unit {
    var srcItemStack = this.clone()
    srcItemStack.apply(lambda)
    this.itemMeta = srcItemStack.itemMeta
}

/**
 * Kotlin DSL创建一个物品并设置相关属性的方法
 *
 * @param lambda 设置物品属性的lambda表达式
 */
fun CXItemStack.Companion.create(lambda: CXItemStack.() -> Unit): CXItemStack {
    var itemStack = CXItemStack(1, 1, "", "")
    itemStack.apply(lambda)
    return itemStack
}