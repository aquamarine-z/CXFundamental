/**
 * ����CommandSender����չ����
 */
package cxplugins.cxfundamental.minecraft.kotlindsl

import cxplugins.cxfundamental.minecraft.server.CXColor
import org.bukkit.command.CommandSender

/**
 * ���;���CXColor.toColor()���������Ϣ ����'&'ת��Ϊ��ɫ����'��'
 *
 * @param message ���͵���Ϣ
 */
fun CommandSender.sendMessageWithColor(message: String) {
    this.sendMessage(CXColor.toColor(message))
}