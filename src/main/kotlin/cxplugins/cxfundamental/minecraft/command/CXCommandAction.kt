package cxplugins.cxfundamental.minecraft.command

/**
 * ���CXCommandRunner������Ĵ���Ľӿ�
 */
interface CXCommandAction {
    fun action(arg0: CXCommandInformation?): Boolean
}
