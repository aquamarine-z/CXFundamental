package cxplugins.cxfundamental.minecraft.command

import org.bukkit.World
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

/**
 * ������������ʱ  �ṩ��ֵ
 */
class CXCommandInformation(var sender: CommandSender, var cmd: Command, var label: String, var args: Array<String>?) {
    var playerArgs = ArrayList<Player>()
    var worldArgs = ArrayList<World>()
    var integerArgs = ArrayList<Int>()
    var numberArgs = ArrayList<Double>()
    var booleanArgs = ArrayList<Boolean>()
    var onlinePlayerArgs = ArrayList<Player>()
    var senderPlayer: Player? = null
}