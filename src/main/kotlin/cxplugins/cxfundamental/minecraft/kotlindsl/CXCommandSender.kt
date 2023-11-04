/**
 * 对于CommandSender的扩展方法
 */
package cxplugins.cxfundamental.minecraft.kotlindsl

import cxplugins.cxfundamental.minecraft.server.CXColor
import org.bukkit.command.CommandSender

/**
 * 发送经过CXColor.toColor()处理过的信息 即把'&'转换为颜色代码'§'
 *
 * @param message 发送的信息
 */
fun CommandSender.sendMessageWithColor(message: String) {
    this.sendMessage(CXColor.toColor(message))
}