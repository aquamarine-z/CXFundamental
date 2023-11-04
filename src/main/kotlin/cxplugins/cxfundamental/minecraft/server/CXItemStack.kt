package cxplugins.cxfundamental.minecraft.server

import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import java.util.*

/**
 * 对ItemStack类有所扩展的类
 */
class CXItemStack : ItemStack {
    /**
     * 将展示名和注释通过CXColor.toColor处理再进行构造的构造器
     * @param ID 此物品的ID号
     * @param amount 此物品的数量
     * @param display 物品的展示名
     * @param Lore 此物品的介绍 用'|'来分行
     * @param durability 此物品的磨损度
     */
    constructor(id: Int, amount: Int, display: String, lores: Array<out String>, durability: Short = 0) {
        this.typeId = id
        this.amount = amount
        val IM = this.itemMeta
        IM.displayName = display.replace("&".toRegex(), "§")
        val L = lores.toMutableList()
        for (i in L.indices) {
            L[i] = L[i].replace("&".toRegex(), "§")
        }
        IM.lore = L
        this.durability = durability
        this.itemMeta = IM
    }

    /**
     * 将展示名和注释通过CXColor.toColor处理再进行构造的构造器
     * @param id 此物品的ID号
     * @param amount 此物品的数量
     * @param display 物品的展示名
     * @param lore 此物品的介绍 用'|'来分行
     * @param durability 此物品的磨损度
     */
    constructor(id: Int, amount: Int, display: String, lore: String, durability: Short = 0) {
        this.typeId = id
        this.amount = amount
        val IM = this.itemMeta
        IM.displayName = display.replace("&".toRegex(), "§")
        val L = Arrays.asList(*lore.split("\\|".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())
        for (i in L.indices) {
            L[i] = L[i].replace("&".toRegex(), "§")
        }
        IM.lore = L
        this.durability = durability.toShort()
        this.itemMeta = IM
    }

    /**
     * 将展示名和注释通过CXColor.toColor处理再进行构造的构造器
     * @param material 此物品的材质
     * @param amount 此物品的数量
     * @param display 物品的展示名
     * @param Lore 此物品的介绍 用'|'来分行
     * @param durability 此物品的磨损度
     */
    constructor(material: Material, amount: Int, display: String, lores: Array<out String>, durability: Short = 0) {
        this.type = material
        this.amount = amount
        val IM = this.itemMeta
        IM.displayName = display.replace("&".toRegex(), "§")
        val L = lores.toMutableList()
        for (i in L.indices) {
            L[i] = L[i].replace("&".toRegex(), "§")
        }
        IM.lore = L
        this.durability = durability.toShort()
        this.itemMeta = IM
    }

    /**
     * 将展示名和注释通过CXColor.toColor处理再进行构造的构造器
     * @param material 此物品的材质
     * @param amount 此物品的数量
     * @param display 物品的展示名
     * @param lore 此物品的介绍 用'|'来分行
     * @param durability 此物品的磨损度
     */
    constructor(material: Material, amount: Int, display: String, lore: String, durability: Short = 0) {
        this.type = material
        this.amount = amount
        val IM = this.itemMeta
        IM.displayName = display.replace("&".toRegex(), "§")
        val L = Arrays.asList(*lore.split("\\|".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())
        for (i in L.indices) {
            L[i] = L[i].replace("&".toRegex(), "§")
        }
        IM.lore = L
        this.durability = durability.toShort()
        this.itemMeta = IM
    }

    /**
     * 忽略数量判断两个物品是否相同
     *
     * @param arg0 另外一个物品
     * @return 若相等 则返回true 否则返回false
     */
    fun equalsIgnoreAmount(arg0: ItemStack): Boolean {
        val a = this.clone()
        val b = arg0.clone()
        return a == b
    }

    /**
     * 关于创建物品 判断物品相同等方法的静态类
     */
    companion object {
        /**
         * 将展示名和注释通过CXColor.toColor处理再生成物品
         * @param id 此物品的ID号
         * @param amount 此物品的数量
         * @param display 物品的展示名
         * @param lore 此物品的介绍 用'|'来分行
         * @param durability 此物品的磨损度
         */
        @JvmStatic
        fun create(id: Int, amount: Int, display: String, lore: String, durability: Int): ItemStack {
            val Result = ItemStack(id, amount)
            val IM = Result.itemMeta
            IM.displayName = display.replace("&".toRegex(), "§")
            val L = Arrays.asList(*lore.split("\\|".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())
            for (i in L.indices) {
                L[i] = L[i].replace("&".toRegex(), "§")
            }
            IM.lore = L
            Result.itemMeta = IM
            Result.durability = durability.toShort()
            return Result
        }

        /**
         * 将展示名和注释通过CXColor.toColor处理再生成物品
         * @param id 此物品的ID号
         * @param amount 此物品的数量
         * @param display 物品的展示名
         * @param lore 此物品的介绍 用'|'来分行
         */
        @JvmStatic
        fun create(id: Int, amount: Int, display: String, lore: String): ItemStack {
            val Result = ItemStack(id, amount)
            val IM = Result.itemMeta
            IM.displayName = display.replace("&".toRegex(), "§")
            val L = Arrays.asList(*lore.split("\\|".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())
            for (i in L.indices) {
                L[i] = L[i].replace("&".toRegex(), "§")
            }
            IM.lore = L
            Result.itemMeta = IM
            return Result
        }

        /**
         * 将某个物品扣除一定数量
         *
         * @param item 此物品
         * @param amount 扣除的数量
         * @return 扣除之后的物品
         */
        @JvmStatic
        fun cost(item: ItemStack, amount: Int): ItemStack {
            val Result = item
            Result.amount = item.amount - amount
            return Result
        }

        /**
         * 忽略数量判断两个物品是否相同
         *
         * @param arg0 另外一个物品
         * @return 若相等 则返回true 否则返回false
         */
        @JvmStatic
        fun equalsIgnoreAmount(a: ItemStack, b: ItemStack): Boolean {
            var a = a
            var b = b
            a = a.clone()
            b = b.clone()
            a.amount = 1
            b.amount = 1
            return a == b
        }
    }

}

