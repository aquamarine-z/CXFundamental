package cxplugins.cxfundamental.minecraft.command

import cxplugins.cxfundamental.minecraft.server.CXReplace
import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.entity.Player

typealias ScriptExecuteLambda = (Player, String, String) -> Unit

/**
 * �ű�(��������������б�)
 */
class CXScript : ConfigurationSerializable {
    /**
     * �����б�
     */
    var commands: MutableList<String> = ArrayList()

    /**
     * ����б�
     */
    var roles: MutableList<String> = ArrayList()

    /**
     *���һ������
     * @param command ��Ҫ��ӵ�����
     * @param role ��ʲô���ִ�д�����
     */
    fun add(command: String, role: String) {
        commands.add(command)
        this.roles.add(role)
    }

    /**
     *ɾ������ƥ�������
     * @param command ɾ��������
     * @param role ���
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
     * ɾ��ָ������������
     *
     * @param line ���������
     */
    fun remove(line: Int) {
        commands.removeAt(line)
        this.roles.removeAt(line)
    }

    /**
     * ִ�д˽ű�
     *
     * @param sender ִ�е����
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
     * �Կ���̨���ִ�д˽ű�
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
     * ɾ����������
     *
     * @param command ��Ҫɾ��������
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
        // TODO �Զ����ɵĹ��캯�����
    }
}
