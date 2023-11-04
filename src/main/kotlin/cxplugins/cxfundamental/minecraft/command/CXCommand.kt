package cxplugins.cxfundamental.minecraft.command

import org.bukkit.Bukkit
import org.bukkit.entity.Player

/**
 * ������ķ����ṩ����
 */
object CXCommand {
    /**
     * �����Ƿ�ӵ��Ȩ�� ִ��ĳ����
     *
     * @param p �����
     * @param command ��Ҫִ�е�����(�����'/')
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
     * @param command ��������ִ̨�еĴ���(�����'/')
     */
    fun sendCommandToConsole(command: String) {
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command)
    }
}
