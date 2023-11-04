package cxplugins.cxfundamental.minecraft.command

import cxplugins.cxfundamental.minecraft.server.CXReplace
import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.entity.Player

typealias ScriptExecuteLambda = (Player, String, String) -> Unit

/**
 * 脚本(包含多条命令的列表)
 */
class CXScript : ConfigurationSerializable {
    /**
     * 命令列表
     */
    var commands: MutableList<String> = ArrayList()

    /**
     * 身份列表
     */
    var roles: MutableList<String> = ArrayList()

    /**
     *添加一个命令
     * @param command 需要添加的命令
     * @param role 以什么身份执行此命令
     */
    fun add(command: String, role: String) {
        commands.add(command)
        this.roles.add(role)
    }

    /**
     *删除所有匹配的命令
     * @param command 删除的命令
     * @param role 身份
     */
    fun removeAll(command: String, role: String) {
        for (i in commands.indices) {
            if (commands[i] == command && this.roles[i] == role) {
                commands.removeAt(i)
                this.roles.removeAt(i)
            }
        }
    }

    /**
     * 删除指定行数的命令
     *
     * @param line 命令的行数
     */
    fun remove(line: Int) {
        commands.removeAt(line)
        this.roles.removeAt(line)
    }

    /**
     * 执行此脚本
     *
     * @param sender 执行的玩家
     */
    fun perform(sender: Player, executors: List<ScriptExecuteLambda> = ArrayList<ScriptExecuteLambda>()) {
        for (i in commands.indices) {
            for (executor in executors) {
                executor(sender, commands[i], this.roles[i])
            }
            if (this.roles[i] == "Player")
                sender.performCommand(commands[i])
            else if (this.roles[i] == "Op") CXCommand.runWithoutPermission(sender, commands[i])
            else if (this.roles[i] == "Console") CXCommand.sendCommandToConsole(commands[i])
        }
    }

    fun perform(sender: Player) {
        for (i in commands.indices) {
            if (this.roles[i] == "Player")
                sender.performCommand(commands[i])
            else if (this.roles[i] == "Op")
                CXCommand.runWithoutPermission(sender, commands[i])
            else if (this.roles[i] == "Console") CXCommand.sendCommandToConsole(commands[i])
        }
    }

    /**
     * 以控制台身份执行此脚本
     *
     */
    fun performAsConsole() {
        for (i in commands.indices) {
            if (this.roles[i] == "Player")
                continue
            else if (this.roles[i] == "Op")
                continue
            else if (this.roles[i] == "Console") CXCommand.sendCommandToConsole(CXReplace.replace(commands[i]))
        }
    }

    /**
     * 删除所有命令
     *
     * @param command 需要删除的命令
     */
    fun removeAll(command: String) {
        for (i in commands.indices) {
            if (commands[i] == command) {
                commands.removeAt(i)
                this.roles.removeAt(i)
            }
        }
    }

    override fun serialize(): Map<String, Any> {
        val result = HashMap<String, Any>()
        result["Commands"] = commands
        result["Roles"] = this.roles
        return result
    }

    constructor(arg0: Map<String, Any>) {
        commands = (arg0["Commands"] as List<String>).toMutableList()
        this.roles = (arg0["Roles"] as List<String>).toMutableList()
    }

    constructor() {
        commands = ArrayList()
        this.roles = ArrayList()
        // TODO 自动生成的构造函数存根
    }
}
