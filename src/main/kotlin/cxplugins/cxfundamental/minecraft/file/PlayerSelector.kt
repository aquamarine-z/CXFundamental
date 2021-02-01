package cxplugins.cxfundamental.minecraft.file

import org.bukkit.entity.Player

/**
 * 未完工的类 表示要修改所有玩家文件时玩家的选择器
 */
interface PlayerSelector {

    fun isSelected(player: Player): Boolean
}
