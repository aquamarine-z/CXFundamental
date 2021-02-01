package cxplugins.cxfundamental.minecraft.server

import java.util.ArrayList

import java.util.HashMap
import org.bukkit.inventory.ItemStack

/**
 * 为一些数据的替换提供的类
 *
 */
object CXReplace {

    private var Replaces: MutableMap<String, String> = HashMap()
    @JvmStatic
            /**
             * 添加一个替换项
             *
             * @param Key 被替换的内容
             * @param Value 替换的内容
             */
    fun add(Key: String, Value: String) {
        Replaces[Key] = Value
    }
    @JvmStatic
            /**
             * 删除一个替换项
             *
             * @param Key 被删除的替换内容
             */
    fun remove(Key: String) {
        Replaces.remove(Key)
    }
    @JvmStatic
            /**
             * 将一个字符串进行一次替换操作
             *
             * @param R 被替换的字符串
             * @return 替换后的字符串
             */
    fun replace(R: String): String {
        var R = R
        val From = ArrayList(Replaces.keys)

        for (i in From.indices) {
            R = R.replace(From[i].toRegex(), Replaces[From[i]].toString())
        }
        return R

    }
    /**
     * 将一个列表进行一次替换操作
     *
     * @param R 被替换的列表
     * @return 替换后的列表
     */
    @JvmStatic
    fun replace(R: MutableList<String>): List<String> {
        val From = ArrayList(Replaces.keys)
        for (j in R.indices) {
            for (i in From.indices) {
                R[j] = R[j].replace(From[i].toRegex(), Replaces[From[i]].toString())
            }
        }
        return R
    }
    /**
     * 将一个物品的介绍与名字进行一次替换操作
     *
     * @param Item 被替换的物品
     * @return 替换后的物品
     */
    @JvmStatic
    fun replace(Item: ItemStack): ItemStack {
        val IM = Item.itemMeta
        IM.displayName = replace(IM.displayName)
        IM.lore = replace(IM.lore)
        Item.itemMeta = IM
        return Item
    }
}
