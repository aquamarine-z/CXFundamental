package cxplugins.cxfundamental.minecraft.server

import org.bukkit.inventory.ItemStack

/**
 * 对对话框对话的颜色处理提供的类
 *
 */
class CXColor {


    /**
     * 对对话框对话的颜色处理提供的类 静态方法
     *
     *
     */
    companion object {
        /**
        红色
         */
        val RED = "§4"

        /**
        深蓝色
         */
        val DARK_BLUE = "§1"

        /**
        绿色
         */
        val GREEN = "§2"

        /**
        蓝色
         */
        val BLUE = "§3"

        /**
        深粉色
         */
        val DEEP_PINK = "§5"

        /**
        金色
         */
        val GOLDEN = "§6"

        /**
        灰色
         */
        val GRAY = "§7"

        /**
        黑色
         */
        val BLACK = "§0"

        /**
        深灰色
         */
        val DARK_GRAY = "§8"

        /**
        紫色
         */
        val PURPLE = "§9"

        /**
        天蓝色
         */
        val LBLUE = "§b"

        /**
        樱桃红
         */
        val LPINK = "§c"

        /**
        中划线
         */
        val LMIDLINE = "§m"

        /**
        下划线
         */
        val LUNDERLINE = "§n"

        /**
        淡黄色
         */
        val LYELLOW = "§e"

        /**
        白色
         */
        val LWHITE = "§f"

        /**
        加粗字体
         */
        val LBOLD = "§l"

        /**
        浅绿色
         */
        val LGREEN = "§a"

        /**
        淡紫色
         */
        val LPURPLE = "§d"

        /**
        颜色代码'§'
         */
        val colorCode = "§"

        /**
         * 将字符串进行颜色处理 即将'&'换为'§'
         *
         * @param from 需要处理的字符串
         * @return 处理过的字符串
         */
        @JvmStatic
        fun toColor(from: String): String {
            return from.replace("&".toRegex(), "§")
        }

        /**
         * 将物品的标题以及介绍进行颜色处理 即将'&'换为'§'
         *
         * @param from 需要处理的物品
         * @return 处理过的物品
         */
        @JvmStatic
        fun toColor(from: ItemStack): ItemStack {
            CXReplace.add("&", "§")
            val Result = CXReplace.replace(from)
            CXReplace.remove("&")
            return Result
        }
    }

}
