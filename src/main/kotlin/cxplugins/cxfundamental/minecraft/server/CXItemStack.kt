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
     * @param Amount 此物品的数量
     * @param Display 物品的展示名
     * @param Lore 此物品的介绍 用'|'来分行
     * @param Dura 此物品的磨损度
     */
    constructor(id: Int, Amount: Int, Display: String, lores: Array<out String>, Dura: Short = 0) {
        this.typeId = id
        this.amount = Amount
        val IM = this.itemMeta
        IM.displayName = Display.replace("&".toRegex(), "§")
        val L = lores.toMutableList()
        for (i in L.indices) {
            L[i] = L[i].replace("&".toRegex(), "§")
        }
        IM.lore = L
        this.durability = Dura
        this.itemMeta = IM
    }

    /**
     * 将展示名和注释通过CXColor.toColor处理再进行构造的构造器
     * @param ID 此物品的ID号
     * @param Amount 此物品的数量
     * @param Display 物品的展示名
     * @param Lore 此物品的介绍 用'|'来分行
     * @param Dura 此物品的磨损度
     */
    constructor(ID: Int, Amount: Int, Display: String, Lore: String, Dura: Short = 0) {
        this.typeId = ID
        this.amount = Amount
        val IM = this.itemMeta
        IM.displayName = Display.replace("&".toRegex(), "§")
        val L = Arrays.asList(*Lore.split("\\|".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())
        for (i in L.indices) {
            L[i] = L[i].replace("&".toRegex(), "§")
        }
        IM.lore = L
        this.durability = Dura.toShort()
        this.itemMeta = IM
    }

    /**
     * 将展示名和注释通过CXColor.toColor处理再进行构造的构造器
     * @param material 此物品的材质
     * @param Amount 此物品的数量
     * @param Display 物品的展示名
     * @param Lore 此物品的介绍 用'|'来分行
     * @param Dura 此物品的磨损度
     */
    constructor(material: Material, Amount: Int, Display: String, lores: Array<out String>, Dura: Short = 0) {
        this.type = material
        this.amount = Amount
        val IM = this.itemMeta
        IM.displayName = Display.replace("&".toRegex(), "§")
        val L = lores.toMutableList()
        for (i in L.indices) {
            L[i] = L[i].replace("&".toRegex(), "§")
        }
        IM.lore = L
        this.durability = Dura.toShort()
        this.itemMeta = IM
    }

    /**
     * 将展示名和注释通过CXColor.toColor处理再进行构造的构造器
     * @param material 此物品的材质
     * @param Amount 此物品的数量
     * @param Display 物品的展示名
     * @param Lore 此物品的介绍 用'|'来分行
     * @param Dura 此物品的磨损度
     */
    constructor(material: Material, Amount: Int, Display: String, Lore: String, Dura: Short = 0) {
        this.type = material
        this.amount = Amount
        val IM = this.itemMeta
        IM.displayName = Display.replace("&".toRegex(), "§")
        val L = Arrays.asList(*Lore.split("\\|".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())
        for (i in L.indices) {
            L[i] = L[i].replace("&".toRegex(), "§")
        }
        IM.lore = L
        this.durability = Dura.toShort()
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
         * @param ID 此物品的ID号
         * @param Amount 此物品的数量
         * @param Display 物品的展示名
         * @param Lore 此物品的介绍 用'|'来分行
         * @param Dura 此物品的磨损度
         */
        @JvmStatic
        fun create(ID: Int, Amount: Int, Display: String, Lore: String, Dura: Int): ItemStack {
            val Result = ItemStack(ID, Amount)
            val IM = Result.itemMeta
            IM.displayName = Display.replace("&".toRegex(), "§")
            val L = Arrays.asList(*Lore.split("\\|".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())
            for (i in L.indices) {
                L[i] = L[i].replace("&".toRegex(), "§")
            }
            IM.lore = L
            Result.itemMeta = IM
            Result.durability = Dura.toShort()
            return Result
        }

        /**
         * 将展示名和注释通过CXColor.toColor处理再生成物品
         * @param ID 此物品的ID号
         * @param Amount 此物品的数量
         * @param Display 物品的展示名
         * @param Lore 此物品的介绍 用'|'来分行
         */
        @JvmStatic
        fun create(ID: Int, Amount: Int, Display: String, Lore: String): ItemStack {
            val Result = ItemStack(ID, Amount)
            val IM = Result.itemMeta
            IM.displayName = Display.replace("&".toRegex(), "§")
            val L = Arrays.asList(*Lore.split("\\|".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())
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
         * @param Item 此物品
         * @param Amount 扣除的数量
         * @return 扣除之后的物品
         */
        @JvmStatic
        fun cost(Item: ItemStack, Amount: Int): ItemStack {
            val Result = Item
            Result.amount = Item.amount - Amount
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

