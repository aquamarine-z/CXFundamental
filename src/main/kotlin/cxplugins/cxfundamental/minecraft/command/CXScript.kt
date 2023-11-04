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
     * ������б�
     */
    var commands: MutableList<String>
        /**
         * ��ȡ������б�
         */
        get() = Commands
        /**
         * ����������б�
         */
        set(commands) {
            Commands = commands
        }

    /**
     * ��ݵ��б�
     */
    var roles: MutableList<String>
        /**
         * ��ȡ��ݵ��б�
         */
        get() = Roles
        /**
         * ������ݵ��б�
         */
        set(roles) {
            Roles = roles
        }

    /**
     * �����б�
     */
    private var Commands: MutableList<String> = ArrayList()

    /**
     * ����б�
     */
    private var Roles: MutableList<String> = ArrayList()

    /**
     *���һ������
     * @param Command ��Ҫ��ӵ�����
     * @param Role ��ʲô���ִ�д�����
     */
    fun add(Command: String, Role: String) {
        Commands.add(Command)
        Roles.add(Role)
    }

    /**
     *ɾ������ƥ�������
     * @param Command ɾ��������
     * @param Role ���
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
     * ɾ��ָ������������
     *
     * @param Line ���������
     */
    fun remove(Line: Int) {
        Commands.removeAt(Line)
        Roles.removeAt(Line)
    }

    /**
     * ִ�д˽ű�
     *
     * @param Sender ִ�е����
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
     * �Կ���̨���ִ�д˽ű�
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
     * ɾ����������
     *
     * @param Command ��Ҫɾ��������
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
        // TODO �Զ����ɵĹ��캯�����
    }
}
