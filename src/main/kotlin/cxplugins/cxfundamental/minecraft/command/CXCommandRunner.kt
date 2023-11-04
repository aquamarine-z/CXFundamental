package cxplugins.cxfundamental.minecraft.command

import cxplugins.cxfundamental.minecraft.math.CXMath.Companion.isInteger
import cxplugins.cxfundamental.minecraft.math.CXMath.Companion.isNumeric
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*

/**
 * �򻯶���������
 */
object CXCommandRunner {
    var onlinePlayerNotFound = "Player Not Found"
    var playerNotFound = "Player Not Found"
    var worldNotFound = "Player Not Found"
    var wrongAmountOfArgs = "Args Error"
    var actionMap: MutableMap<String?, CXCommandAction> = HashMap()
    var argsMap: MutableMap<String?, String> = HashMap()

    /**
     * ���(ע��)һ��������//
     * @param name ������ (����"/")
     * @param args ������Ĳ��� ��"[]"������ �ж���������ÿո�ָ� ���������������ҵĻ�ʹ��"[OnlinePlayer]" ���ʹ��"[Player]" ����ʹ��"[World]" ����������ʹ��"[Other]"
     * @param action ����������֮����Ҫִ�еĶ���
     */
    fun addCommand(name: String?, args: String, action: CXCommandAction) {
        actionMap[name] = action
        argsMap[name] = args
    }

    /**
     * ��JavaPlugin.OnCommand��������д���
     * @param sender OnCommand��sender����
     * @param cmd OnCommand��cmd����
     * @param label OnCommand��label����
     * @param args OnCommand��args����
     */
    fun runCommand(sender: CommandSender?, cmd: Command, label: String?, args: Array<String>): Boolean {
        var commandLine = cmd.name
        //CXMessage.sendToConsole(commandLine);
        for (s in args) {
            commandLine += " "
            commandLine += s
        }
        val commandList: MutableList<String?> = ArrayList<String?>(argsMap.keys)
        val argsList: MutableList<String?> = ArrayList()
        val actionList: MutableList<CXCommandAction?> = ArrayList()
        for (s in commandList) {
            argsList.add(argsMap[s])
            actionList.add(actionMap[s])
        }
        for (i in 0 until commandList.size - 1) {
            for (j in i until commandList.size) {
                if (commandList[i]!!.split(" ".toRegex()).toTypedArray().size < commandList[j]!!.split(" ".toRegex())
                        .toTypedArray().size
                ) {
                    var sTemp: String? = ""
                    sTemp = commandList[i]
                    commandList[i] = commandList[j]
                    commandList[j] = sTemp
                    sTemp = argsList[i]
                    argsList[i] = argsList[j]
                    argsList[j] = sTemp
                    var ACTemp: CXCommandAction?
                    ACTemp = actionList[i]
                    actionList[i] = actionList[j]
                    actionList[j] = ACTemp
                }
            }
        }
        for (regCommand in commandList) {
            val commands = regCommand!!.split(" ".toRegex()).toTypedArray()
            val inputCommands = commandLine.split(" ".toRegex()).toTypedArray()
            var result = true
            if (commands.size > inputCommands.size) {
                continue
            }
            for (i in commands.indices) {
                if (commands[i] != inputCommands[i]) result = false
            }
            if (result == true) {
                val inputArgs: MutableList<String> = ArrayList()
                for (i in commands.size until inputCommands.size) {
                    try {
                        inputArgs.add(inputCommands[i])
                    } catch (exc: ArrayIndexOutOfBoundsException) {
                        //CXMessage.sendToConsole(Integer.toString(i)+" "+Integer.toString(j));
                    }
                }
                val arg = argsMap[regCommand]!!.split(" ".toRegex()).toTypedArray()
                val finalargs: MutableList<String> = ArrayList()
                val infor = CXCommandInformation(sender!!, cmd, label!!, null)
                if (arg[0] != "") {
                    try {
                        for (i in arg.indices) {
                            when (arg[i].lowercase(Locale.getDefault())) {
                                "[onlineplayer]" -> if (Bukkit.getPlayer(inputArgs[i]) == null) {
                                    //sender.sendMessage(onlinePlayerNotFound);
                                    throw CommandException(i + 1, CommandException.Reason.ONLINEPLAYER)
                                } else if (!Bukkit.getPlayer(inputArgs[i]).isOnline) {
                                    //sender.sendMessage(onlinePlayerNotFound);
                                    throw CommandException(i + 1, CommandException.Reason.ONLINEPLAYER)
                                } else {
                                    infor.onlinePlayerArgs.add(Bukkit.getPlayer(inputArgs[i]))
                                }

                                "[player]" -> if (Bukkit.getPlayer(inputArgs[i]) == null) {
                                    //sender.sendMessage(playerNotFound);
                                    throw CommandException(i + 1, CommandException.Reason.PLAYER)
                                } else {
                                    //CXMessage.sendToConsole("1");
                                    infor.playerArgs.add(Bukkit.getPlayer(inputArgs[i]))
                                }

                                "[world]" -> if (Bukkit.getWorld(inputArgs[i]) == null) {
                                    //sender.sendMessage(playerNotFound);
                                    throw CommandException(i + 1, CommandException.Reason.WORLD)
                                } else {
                                    infor.worldArgs.add(Bukkit.getWorld(inputArgs[i]))
                                }

                                "[integer]" -> if (!isInteger(inputArgs[i])) {
                                    throw CommandException(i + 1, CommandException.Reason.INTEGER)
                                } else {
                                    infor.integerArgs.add(inputArgs[i].toInt())
                                }

                                "[number]" -> if (!isNumeric(inputArgs[i])) {
                                    throw CommandException(i, CommandException.Reason.NUMBER)
                                } else {
                                    infor.numberArgs.add(inputArgs[i].toDouble())
                                }

                                "[boolean]" -> if (!(inputArgs[i].equals(
                                        "true",
                                        ignoreCase = true
                                    ) || inputArgs[i].equals("false", ignoreCase = true))
                                ) {
                                    throw CommandException(i + 1, CommandException.Reason.NUMBER)
                                } else {
                                    infor.booleanArgs.add(java.lang.Boolean.parseBoolean(inputArgs[i].lowercase(Locale.getDefault())))
                                }
                            }
                            finalargs.add(inputArgs[i])
                        }
                        if (inputArgs.size > arg.size) {
                            for (i in arg.size until inputArgs.size) {
                                finalargs.add(inputArgs[i])
                            }
                        }
                    } catch (exc: ArrayIndexOutOfBoundsException) {
                        throw CommandException(0, CommandException.Reason.WRONGAMOUNT)
                    }
                }
                infor.args = finalargs.toTypedArray()
                if (sender is Player) {
                    infor.senderPlayer = sender
                }
                return actionMap[regCommand]!!.action(infor)
            }
        }
        return false
    }
}