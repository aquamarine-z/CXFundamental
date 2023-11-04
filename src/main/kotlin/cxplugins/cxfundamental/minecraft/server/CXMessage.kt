package cxplugins.cxfundamental.minecraft.server

import org.bukkit.Bukkit
import org.bukkit.entity.Player

/**
 *为服务器信息的发送提供的类
 */
class CXMessage {

    /**
     * 静态方法
     */
    companion object {
        @JvmStatic
                /**
                 * 插件的介绍 帮助
                 */
        var help: MutableList<String> = ArrayList()

        @JvmStatic
                /**
                 * 插件运行时向玩家提示的错误
                 */
        var errors: MutableMap<String, String> = HashMap()

        @JvmStatic
                /**
                 * 输出帮助 错误的时候插件的前缀
                 */
        var prefix = ""

        @JvmStatic
        /**
         * 报告执行进度的计数器
         */
        internal var Test_Report = 0

        @JvmStatic
                /**
                 * 添加一条帮助到帮助列表
                 *
                 * @param h 此条帮助
                 */
        fun addHelp(h: String) {
            help.add(h)
        }

        @JvmStatic
                /**
                 * 删除某一个帮助
                 *
                 * @param l 此帮助信息的位置
                 */
        fun removeHelp(l: Int) {
            help.removeAt(l)
        }

        @JvmStatic
                /**
                 * 将帮助信息发送到玩家
                 *
                 * @param p 玩家
                 */
        fun sendHelpToPlayer(p: Player) {

            for (f in finalHelp) {
                sendMessage(p, f)
            }
            return
        }

        //System.out.println(help.size());
        //System.out.println(a.size());
        @JvmStatic
        private val finalHelp: List<String>
            get() {
                val a = ArrayList<String>()
                for (s in help) {
                    a.add(prefix + s)
                }
                return a
            }

        @JvmStatic
        private fun getError(Key: String): String? {
            return errors[Key]
        }

        @JvmStatic
        private fun setError(Key: String, Value: String) {
            errors.put(Key, Value)
            return
        }

        @JvmStatic
        private fun setDefaultErrors() {
            /*Errors["InCorrectParameters"] = CXColor.toColor("&4[Error]The parameters you input is NOT correct!")
            Errors["FileNotFount"] = CXColor.toColor("&4[Error]Could'n find the config file(files)")
            Errors["NotPlayer"] = CXColor.toColor("&4[Error]You're not a player")
            Errors["HaveNotPermission"] = CXColor.toColor("&4[Error]Sorry you don't have the permission")*/
        }

        /**
         * 向某一个玩家发送一条经过CXColor.toColor处理的信息
         *
         * @param P 目标玩家
         * @param Message 发送的信息
         */
        @JvmStatic
        fun sendMessage(P: Player, Message: String) {
            P.sendMessage(CXColor.toColor(Message))
        }

        /**
         * 向后台发送信息
         *
         * @param Message 发送的信息
         */
        @JvmStatic
        fun sendToConsole(Message: String) {
            println("[CXMessage_ConsoleReporter]$Message")
        }

        /**
         * 发送全服公告
         *
         * @param Message 全服公告的内容
         */
        @JvmStatic
        fun announce(Message: String) {
            Bukkit.broadcastMessage(CXColor.toColor(Message))
        }

        /**
         * 向后台发送服务器进度报告
         */
        @JvmStatic
        fun processReport() {
            println("[CXMessage_ProcessReporter]$Test_Report")
            Test_Report++
        }

        /**
         *将进度重置为0
         *
         */
        @JvmStatic
        fun processReset() {
            Test_Report = 0
        }
    }
}
