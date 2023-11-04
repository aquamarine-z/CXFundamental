package cxplugins.cxfundamental.minecraft.command

import org.bukkit.Bukkit
import org.bukkit.entity.Player

/**
 * 对命令的发送提供的类
 */
object CXCommand {
    /**
     * 忽略是否拥有权限 执行某命令
     *
     * @param p 该玩家
     * @param command 需要执行的命令(无需加'/')
     */

    fun runWithoutPermission(p: Player, command: String) {


        if (!p.isOp) {
            try {
                p.isOp = true
                p.performCommand(command)
            } catch (e: Exception) {
                p.sendMessage("error$e")
                return
            } finally {
                p.isOp = false
            }
        } else {
            p.performCommand(command)
        }
    }

    /**
     * Send command to console
     *
     * @param command 服务器后台执行的代码(无需加'/')
     */
    fun sendCommandToConsole(command: String) {
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command)
    }
}
