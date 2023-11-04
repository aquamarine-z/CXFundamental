package cxplugins.cxfundamental.minecraft.swing

import org.bukkit.entity.Player

typealias SwingMultipagePanelEvent = CXMultipagePanelEvent

data class CXMultipagePanelEvent(val frame: CXFrame, val player: Player, val oldPage: Int, var cancel: Boolean) {
}