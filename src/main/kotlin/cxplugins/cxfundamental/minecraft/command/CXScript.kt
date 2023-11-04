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
     * 命令的列表
     */
    var commands: MutableList<String>
        /**
         * 获取命令的列表
         */
        get() = Commands
        /**
         * 设置命令的列表
         */
        set(commands) {
            Commands = commands
        }

    /**
     * 身份的列表
     */
    var roles: MutableList<String>
        /**
         * 获取身份的列表
         */
        get() = Roles
        /**
         * 设置身份的列表
         */
        set(roles) {
            Roles = roles
        }

    /**
     * 命令列表
     */
    private var Commands: MutableList<String> = ArrayList()

    /**
     * 身份列表
     */
    private var Roles: MutableList<String> = ArrayList()

    /**
     *添加一个命令
     * @param Command 需要添加的命令
     * @param Role 以什么身份执行此命令
     */
    fun add(Command: String, Role: String) {
        Commands.add(Command)
        Roles.add(Role)
    }

    /**
     *删除所有匹配的命令
     * @param Command 删除的命令
     * @param Role 身份
     */
    fun removeAll(Command: String, Role: String) {
        for (i in Commands.indices) {
            if (Commands[i] == Command && Roles[i] == Role) {
                Commands.removeAt(i)
                Roles.removeAt(i)
            }
        }
    }

    /**
     * 删除指定行数的命令
     *
     * @param Line 命令的行数
     */
    fun remove(Line: Int) {
        Commands.removeAt(Line)
        Roles.removeAt(Line)
    }

    /**
     * 执行此脚本
     *
     * @param Sender 执行的玩家
     */
    fun perform(Sender: Player, executors: List<ScriptExecuteLambda> = ArrayList<ScriptExecuteLambda>()) {
        for (i in Commands.indices) {
            for (executor in executors) {
                executor(Sender, Commands[i], Roles[i])
            }
            if (Roles[i] == "Player")
                Sender.performCommand(Commands[i])
            else if (Roles[i] == "Op") CXCommand.runWithoutPermission(Sender, Commands[i])
            else if (Roles[i] == "Console") CXCommand.sendCommandToConsole(Commands[i])
        }
    }

    fun perform(Sender: Player) {
        for (i in Commands.indices) {
            if (Roles[i] == "Player")
                Sender.performCommand(Commands[i])
            else if (Roles[i] == "Op")
                CXCommand.runWithoutPermission(Sender, Commands[i])
            else if (Roles[i] == "Console") CXCommand.sendCommandToConsole(Commands[i])
        }
    }

    /**
     * 以控制台身份执行此脚本
     *
     */
    fun performAsConsole() {
        for (i in Commands.indices) {
            if (Roles[i] == "Player")
                continue
            else if (Roles[i] == "Op")
                continue
            else if (Roles[i] == "Console") CXCommand.sendCommandToConsole(CXReplace.replace(Commands[i]))
        }
    }

    /**
     * 删除所有命令
     *
     * @param Command 需要删除的命令
     */
    fun removeAll(Command: String) {
        for (i in Commands.indices) {
            if (Commands[i] == Command) {
                Commands.removeAt(i)
                Roles.removeAt(i)
            }
        }
    }

    override fun serialize(): Map<String, Any> {
        val Result = HashMap<String, Any>()
        Result["Commands"] = Commands
        Result["Roles"] = Roles
        return Result
    }

    constructor(arg0: Map<String, Any>) {
        Commands = (arg0["Commands"] as List<String>).toMutableList()
        Roles = (arg0["Roles"] as List<String>).toMutableList()
    }

    constructor() {
        Commands = ArrayList()
        Roles = ArrayList()
        // TODO 自动生成的构造函数存根
    }
}
