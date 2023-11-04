package cxplugins.cxfundamental.minecraft.kotlindsl

import cxplugins.cxfundamental.minecraft.server.CXColor

/**
 * ���ַ����е�'&'ת��Ϊ��ɫ����'��'
 *
 * @return ת������ַ���
 */
fun String.toColor(): String {
    return CXColor.toColor(this)
}

/**
 * ��List�е�'&'ת��Ϊ��ɫ����'��'
 *
 * @return ת������ַ���
 */
fun List<String>.toColor(): List<String> {
    var list = this.toMutableList()
    for (i in list.indices) {
        list[i] = list[i].toColor()
    }
    return list
}