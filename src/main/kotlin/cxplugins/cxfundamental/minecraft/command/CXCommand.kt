package cxplugins.cxfundamental.minecraft.command
import org.bukkit.entity.Player
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Villager
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.MerchantInventory

/**
 * ������ķ����ṩ����
 */
public object CXCommand {
    /**
     * �����Ƿ�ӵ��Ȩ�� ִ��ĳ����
     *
     * @param p �����
     * @param Command ��Ҫִ�е�����(�����'/')
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
     * @param Command ��������ִ̨�еĴ���(�����'/')
     */
    fun sendCommandToConsole(Command: String) {
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), Command)
    }
}
