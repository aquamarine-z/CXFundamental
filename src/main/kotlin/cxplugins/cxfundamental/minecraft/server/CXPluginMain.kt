package cxplugins.cxfundamental.minecraft.server

import cxplugins.cxfundamental.minecraft.command.CommandException
import cxplugins.cxfundamental.minecraft.command.CommandSenderType

import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin


open class CXPluginMain(item: ItemStack?) : JavaPlugin() {

    private var noticePrefix: String = ("§3§l[${this.name}]:")

    var description = "A CXPlugin"
    var itemTypeInMenu = Material.WATER_BUCKET
    var itemInMenu: ItemStack? = null


    init {
        itemInMenu = if (item == null) {
            var itemStack = ItemStack(itemTypeInMenu, 1)
            itemStack
        } else {
            item
        }

    }

    override fun onEnable() {

    }


    open fun reload() {
        throw NoSuchMethodException()
    }

    fun setNoticePrefix(prefix: String) {
        this.noticePrefix = prefix
    }

    fun getNoticePrefix(): String = this.noticePrefix
    private fun sendReportToSender(sender: CommandSender, report: String) {
        if (sender is Player) {
            CXMessage.sendMessage(sender, report)
        } else {
            CXMessage.sendToConsole(report)
        }
    }

    override fun onCommand(
        sender: CommandSender?,
        command: Command?,
        label: String?,
        args: Array<String>?
    ): Boolean {
        try {
            return cxplugins.cxfundamental.minecraft.command.CPMLCommandExecutor.execute(
                sender!!,
                command!!,
                label!!,
                args!!
            )
        } catch (exception: CommandException) {
            var place = exception.place + 1
            when (exception.reason) {
                CommandException.Reason.BOOLEAN -> {
                    sendReportToSender(sender!!, "$noticePrefix 第${place}个参数必须为true或者false")
                    return true
                }

                CommandException.Reason.INTEGER -> {
                    sendReportToSender(sender!!, "$noticePrefix 第${place}个参数必须为一个整数")
                    return true
                }

                CommandException.Reason.DOUBLE -> {
                    sendReportToSender(sender!!, "$noticePrefix 第${place}个参数必须为一个数字")
                    return true
                }

                CommandException.Reason.FLOAT -> {
                    sendReportToSender(sender!!, "$noticePrefix 第${place}个参数必须为一个数字")
                    return true
                }

                CommandException.Reason.WORLD -> {
                    sendReportToSender(sender!!, "$noticePrefix 第${place}个参数必须为一个已经存在的世界存档的名字")
                    return true
                }

                CommandException.Reason.PLAYER -> {
                    sendReportToSender(sender!!, "$noticePrefix 第${place}个参数必须为一个已经注册过的玩家名字")
                    return true
                }

                CommandException.Reason.ONLINEPLAYER -> {
                    sendReportToSender(
                        sender!!,
                        "$noticePrefix 第${place}个参数必须为一个已经注册过的玩家名字 且此玩家必须在线"
                    )
                    return true
                }

                CommandException.Reason.MULTIPAMAMETERNOTFOUNDEND -> {
                    sendReportToSender(sender!!, "$noticePrefix 复合参数缺少结束标记")
                    return true
                }

                CommandException.Reason.MULTIPAMAMETERNOTFOUNDSTART -> {
                    sendReportToSender(sender!!, "$noticePrefix 复合参数缺少开始标记")
                    return true
                }

                CommandException.Reason.WRONGAMOUNT -> {
                    sendReportToSender(sender!!, "$noticePrefix 参数数量过少")
                    return true
                }

                CommandException.Reason.BYTE -> {
                    sendReportToSender(sender!!, "$noticePrefix 第${place}个参数必须为一个数字")
                    return true
                }

                CommandException.Reason.SHORT -> {
                    sendReportToSender(sender!!, "$noticePrefix 第${place}个参数必须为一个数字")
                    return true
                }

                CommandException.Reason.LONG -> {
                    sendReportToSender(sender!!, "$noticePrefix 第${place}个参数必须为一个数字")
                    return true
                }

                CommandException.Reason.WRONGSENDER -> {
                    val list = exception.extraInformation as MutableList<CommandSenderType>
                    var senders = ""
                    if (list.contains(CommandSenderType.COMMANDBLOCK)) senders += " 命令方块 "
                    if (list.contains(CommandSenderType.PLAYER)) senders += " 玩家 "
                    if (list.contains(CommandSenderType.CONSOLE)) senders += " 控制台 "
                    sendReportToSender(sender!!, "$noticePrefix 此命令必须由$senders 执行")
                }

                else -> {

                }
            }
        }
        return false
    }
}