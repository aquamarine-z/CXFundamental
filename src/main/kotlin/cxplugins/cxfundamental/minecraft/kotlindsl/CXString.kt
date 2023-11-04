package cxplugins.cxfundamental.minecraft.kotlindsl

import cxplugins.cxfundamental.minecraft.server.CXColor

/**
 * 将字符串中的'&'转换为颜色代码'§'
 *
 * @return 转换后的字符串
 */
fun String.toColor(): String {
    return CXColor.toColor(this)
}

/**
 * 将List中的'&'转换为颜色代码'§'
 *
 * @return 转换后的字符串
 */
fun List<String>.toColor(): List<String> {
    var list = this.toMutableList()
    for (i in list.indices) {
        list[i] = list[i].toColor()
    }
    return list
}