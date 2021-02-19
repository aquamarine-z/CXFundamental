package cxplugins.cxfundamental.minecraft.command
import org.bukkit.entity.Player
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Villager
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.MerchantInventory

/**
 * 对命令的发送提供的类
 */
public object CXCommand {
    /**
     * 忽略是否拥有权限 执行某命令
     *
     * @param p 该玩家
     * @param Command 需要执行的命令(无需加'/')
     */
    fun runWithoutPermission(p: Player, Command: String) {

        var Command = Command
        if (!p.isOp) {
            try {
                p.isOp = true
                p.performCommand(Command)
            } catch (e: Exception) {
                p.sendMessage("error$e")
                return
            } finally {
                p.isOp = false
            }
        } else {
            p.performCommand(Command)
        }
    }

    /**
     * Send command to console
     *
     * @param Command 服务器后台执行的代码(无需加'/')
     */
    fun sendCommandToConsole(Command: String) {
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), Command)
    }
}
