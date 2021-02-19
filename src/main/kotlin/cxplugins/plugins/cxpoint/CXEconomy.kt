package cxplugins.plugins.cxpoint

import cxplugins.cxfundamental.minecraft.file.CXYamlConfiguration
import cxplugins.cxfundamental.minecraft.server.CXGuide
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import cxplugins.*
import kotlin.collections.ArrayList
/*
* Error Methods:
* static getPointNameList()
* static hasPoint()*/
/**
 * 操作玩家银行账户以及货币列表的类
 */
class CXEconomy {

    /**
     * 静态方法
     */
    companion object{
        /**
         * 表示此服务器已创建的所有货币名的列表
         */

        @JvmStatic
        val pointNameList: ArrayList<String>
            /**
             * 获取此服务器已创建的所有货币名的列表
             */
            get() {

                var Config = CXYamlConfiguration("CXPlugins", "CXPoints.yml")

                val Names = ArrayList<String>(Config.getKeys(false));

                return Names
            }
        /**
         * 扣除某个玩家一定的某种货币
         * @param arg0 玩家名字
         * @param arg1 货币名称
         * @param arg2 扣除金额
         * @return 是否扣除成功
         */
        @JvmStatic
        fun cost(arg0: String, arg1: String, arg2: Double): Boolean {
            if(!hasPoint(arg1)){
                return false
            }
            val Config=CXYamlConfiguration()
            Config.load("CXPlugins\\Players", "$arg0.yml")
            val AccountAmount = Config.getDouble("CXPoint.$arg1", 0.0)
            if (AccountAmount < arg2)
                return false
            else {
                Config.set("CXPoint.$arg1", AccountAmount - arg2)
                Config.save()
                val Event = PluginSetEconomyEvent()
                Bukkit.getServer().pluginManager.callEvent(Event)
                return true
            }
        }
        /**
         * 扣除某个玩家一定的某种货币
         * @param arg0 玩家
         * @param arg1 货币名称
         * @param arg2 扣除金额
         * @return 是否扣除成功
         */
        @JvmStatic
        fun cost(arg0: Player, arg1: String, arg2: Double): Boolean {
            //CXYamlConfiguration Config=CXGuide.GYamlConfiguration();
            return cost(arg0.name, arg1, arg2)
        }
        /**
         * 给予某个玩家一定的某种货币
         * @param arg0 玩家名字
         * @param arg1 货币名称
         * @param arg2 货币金额
         * @return 是否给予成功
         */
        @JvmStatic
        fun give(arg0: String, arg1: String, arg2: Double): Boolean {
            if(!hasPoint(arg1)){
                return false
            }
            val Config=CXYamlConfiguration("CXPlugins\\Players", "$arg0.yml")
            //Config.load()
            val AccountAmount = Config.getDouble("CXPoint.$arg1", 0.0)
            Config.set("CXPoint.$arg1", AccountAmount + arg2)
            Config.save()
            val Event = PluginSetEconomyEvent()

            Bukkit.getServer().pluginManager.callEvent(Event)
            return true
        }
        /**
         * 设置某个玩家一定的某种货币
         * @param arg0 玩家名字
         * @param arg1 货币名称
         * @param arg2 货币金额
         * @return 是否设置成功
         */
        @JvmStatic
        fun set(arg0: String, arg1: String, arg2: Double): Boolean {
            if(!hasPoint(arg1)){
                return false
            }
            val Config=CXYamlConfiguration("CXPlugins\\Players", "$arg0.yml")
            //Config.load()

            Config.set("CXPoint.$arg1", arg2)
            Config.save()

            val Event = PluginSetEconomyEvent()

            Bukkit.getServer().pluginManager.callEvent(Event)
            return true
        }
        /**
         * 设置某个玩家一定的某种货币
         * @param arg0 玩家
         * @param arg1 货币名称
         * @param arg2 货币金额
         * @return 是否设置成功
         */
        @JvmStatic
        fun set(arg0: Player, arg1: String, arg2: Double): Boolean {
            return set(arg0.name, arg1, arg2)
        }
        /**
         * 给予某个玩家一定的某种货币
         * @param arg0 玩家
         * @param arg1 货币名称
         * @param arg2 货币金额
         * @return 是否给予成功
         */
        @JvmStatic
        fun give(arg0: Player, arg1: String, arg2: Double): Boolean {
            //CXYamlConfiguration Config=CXGuide.GYamlConfiguration();
            return give(arg0.name, arg1, arg2)
        }
        /**
         * 判断服务器内是否创建了某种货币
         * @param arg0 货币名称
         * @return 若存在 返回true 否则返回false
         */
        @JvmStatic
        fun hasPoint(arg0: String): Boolean {
            var Config = CXYamlConfiguration("CXPlugins", "CXPoints.yml")

            val Names = ArrayList<String>(Config.getKeys(false));
            return if (Names.contains(arg0))
                true
            else
                false
        }

        /**
         * 删除已经创建的某种货币
         *
         * @param arg0 货币名字
         * @return 是否删除成功 成功则返回true 否则返回false
         */
        @JvmStatic
        fun delete(arg0: String): Boolean {
            if(!hasPoint(arg0)){
                return false
            }
            var Config = CXYamlConfiguration("CXPlugins", "CXPoints.yml")
            //Config.load("CXPlugins", "CXPoints.yml")
            Config.set(arg0, null)
            Config.save()
            val Event = PluginSetEconomyEvent()

            Bukkit.getServer().pluginManager.callEvent(Event)
            return true
        }
        /**
         * 设置某种货币能否支付给其他玩家
         *
         * @param arg0 货币名字
         * @param arg1 能否被支付
         * @return 是否设置成功 成功则返回true 否则返回false
         */
        @JvmStatic
        fun setPayable(arg0: String, arg1: Boolean): Boolean {
            if(!hasPoint(arg0)){
                return false
            }
            var Config = CXYamlConfiguration("CXPlugins", "CXPoints.yml")
            //Config.load("CXPlugins", "CXPoints.yml")
            Config.set("$arg0.Payable", arg1)
            Config.save()
            val Event = PluginSetEconomyEvent()

            Bukkit.getServer().pluginManager.callEvent(Event)
            return true
        }
        /**
         * 判断某种货币能否支付给其他玩家
         *
         * @param arg0 货币名字
         * @return 是否能够支付 能则返回true 不能返回false
         */
        @JvmStatic
        fun getPayable(arg0: String): Boolean {
            if(!hasPoint(arg0)){
                return false
            }
            var Config = CXYamlConfiguration("CXPlugins", "CXPoints.yml")
            return Config.getBoolean("$arg0.Payable")
        }
        /**
         * 创建某种货币
         *
         * @param arg0 货币名字
         * @param arg1 能否被支付
         * @return 是否创建成功 成功返回true 否则返回false
         */
        @JvmStatic
        fun create(arg0: String, arg1: Boolean=false): Boolean {

            var Config = CXYamlConfiguration("CXPlugins", "CXPoints.yml")
            //Config.load()
            Config.set("$arg0.Payable", arg1)
            Config.save()
            val Event = PluginSetEconomyEvent()
            Bukkit.getServer().pluginManager.callEvent(Event)
            return true
        }

        /**
         * 将某个玩家的一定数量的某种货币支付给另外一个玩家
         *
         * @param From 支付的玩家
         * @param To 支付的目标
         * @param PointName 支付的货币名字
         * @param Amount 金额
         * @return 是否支付成功
         */
        @JvmStatic
        fun pay(From: String, To: String, PointName: String, Amount: Double): Boolean {
            if(!hasPoint(PointName)){
                return false
            }
            //var Config = CXYamlConfiguration("CXPlugins\\Players", "$From.yml")

            //Config.load()
            if (Amount <= 0) return false
            if (get(From, PointName) <= Amount)
                return false
            else {
                cost(From, PointName, Amount)
                give(To, PointName, Amount)
            }
            return true

        }
        /**
         * 将某个玩家的一定数量的某种货币支付给另外一个玩家
         *
         * @param From 支付的玩家
         * @param To 支付的目标
         * @param PointName 支付的货币名字
         * @param Amount 金额
         * @return 是否支付成功
         */
        @JvmStatic
        fun pay(From: Player, To: Player, PointName: String, Amount: Double): Boolean {
            return pay(From.name, To.name, PointName, Amount)
        }

        /**
         * 获取某玩家某种货币的存款
         *
         * @param arg0 玩家名
         * @param arg1 货币名
         * @return 玩家的存款金额
         */
        @JvmStatic
        fun get(arg0: String, arg1: String): Double {
            if(!hasPoint(arg1)){
                return 0.0
            }
            var Config = CXYamlConfiguration("CXPlugins\\Players", "$arg0.yml")
            //Config.load()
            return Config.getDouble("CXPoint.$arg1", 0.0)
        }
        /**
         * 获取某玩家某种货币的存款
         *
         * @param arg0 玩家
         * @param arg1 货币名
         * @return 玩家的存款金额
         */
        @JvmStatic
        fun get(arg0: Player, arg1: String): Double {
            return get(arg0.name, arg1)
        }
    }


}

