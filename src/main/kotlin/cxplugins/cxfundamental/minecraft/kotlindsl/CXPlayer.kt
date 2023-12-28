package cxplugins.cxfundamental.minecraft.kotlindsl


import cxplugins.cxfundamental.minecraft.file.CXPlayerDataEditor
import cxplugins.cxfundamental.minecraft.file.CXYamlConfiguration
import cxplugins.plugins.cxpoint.CXEconomy
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable

/**
 * 将此玩家同步地踢出服务器
 * 可以在异步线程中调用
 * @param reason 被踢出的原因
 * @param plugin 执行同步操作的插件
 */
fun Player.kickSynchronously(reason: String, plugin: JavaPlugin) {
    var thread = object : BukkitRunnable() {
        override fun run() {
            this@kickSynchronously.kickPlayer(reason)
        }
    }
    thread.runTask(plugin)
}

/**
 * 获取玩家存储于Players文件夹中配置文件中的数据
 * (详情请见CXPlayerDataEditor)
 * @param path 数据的路径
 * @return 数据的值
 */
fun Player.getData(path: String): Any? {
    return CXPlayerDataEditor.get(this, path)
}

/**
 * 更改玩家存储于Players文件夹中配置文件中的数据
 * (详情请见CXPlayerDataEditor)
 * @param path 数据的路径
 * @param value 更改的值
 */
fun Player.editData(path: String, value: Any?) {
    CXPlayerDataEditor.edit(this, path, value)

}

/**
 * 获取玩家存储于Players文件夹中的配置文件
 * (详情请见CXPlayerDataEditor)
 * @return 此玩家的配置文件
 */
fun Player.getPlayerDataConfiguration(): CXYamlConfiguration {
    return CXPlayerDataEditor.getPlayerConfiguration(this)
}

/**
 * 判断玩家配置文件中是否有某一个数据
 * (详情请见CXPlayerDataEditor)
 * @param path 该数据的路径
 * @return 若有此数据 则返回true 否则返回false
 */
fun Player.hasData(path: String): Boolean {
    return CXPlayerDataEditor.hasData(this, path)
}

/**
 * 判断玩家在Players文件夹中是否有配置文件
 * (详情请见CXPlayerDataEditor)
 * @return 若有此文件 则返回true 否则返回false
 */
fun Player.hasDataFile(): Boolean {
    return CXPlayerDataEditor.hasPlayerData(this.displayName + ".yml")
}

/**
 * 使玩家的银行账户中扣除一定的某一种货币
 * (详情请见CXEconomy)
 * @param pointName 货币名称
 * @param amount 数量
 * @return 是否扣除成功 通常返回true
 */
fun Player.costPoint(pointName: String, amount: Double): Boolean {
    return CXEconomy.cost(this, pointName, amount)
}

/**
 * 使玩家的银行账户中添加一定的某一种货币
 *(详情请见CXEconomy)
 * @param pointName 货币名称
 * @param amount 数量
 * @return 是否添加成功 通常返回true
 */
fun Player.givePoint(pointName: String, amount: Double): Boolean {
    return CXEconomy.give(this, pointName, amount)
}

/**
 * 使玩家的银行账户某一种货币的数量设置为一定值
 *(详情请见CXEconomy)
 * @param pointName 货币名称
 * @param amount 数量
 * @return 是否设置成功 通常返回true
 */
fun Player.setPoint(pointName: String, amount: Double): Boolean {
    return CXEconomy.set(this, pointName, amount)
}

/**
 * 获取玩家的银行账户某一种货币的数量
 *(详情请见CXEconomy)
 * @param pointName 货币名称
 * @return 银行账户中的货币数量
 */
fun Player.getPoint(pointName: String): Double {
    return CXEconomy.get(this, pointName)
}

/**
 * 使玩家向另一玩家支付一定数量的某种货币
 *(详情请见CXEconomy)
 * @param pointName 货币的名字
 * @param amount 支付的数量
 * @param destination 支付的目标
 * @return 若此玩家货币数量不足 则返回false 否则返回true
 */
fun Player.payPoint(pointName: String, amount: Double, destination: Player): Boolean {
    return CXEconomy.pay(this, destination, pointName, amount)
}
