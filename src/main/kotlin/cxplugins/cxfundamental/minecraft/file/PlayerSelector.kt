package cxplugins.cxfundamental.minecraft.file

import org.bukkit.entity.Player

/**
 * δ�깤���� ��ʾҪ�޸���������ļ�ʱ��ҵ�ѡ����
 */
interface PlayerSelector {

    fun isSelected(player: Player): Boolean
}
