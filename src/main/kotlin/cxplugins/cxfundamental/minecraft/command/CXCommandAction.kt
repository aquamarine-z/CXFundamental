package cxplugins.cxfundamental.minecraft.command

/**
 * 添加CXCommandRunner对命令的处理的接口
 */
interface CXCommandAction {
    fun action(arg0: CXCommandInformation?): Boolean
}
