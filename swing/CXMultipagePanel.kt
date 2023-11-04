package cxplugins.cxfundamental.minecraft.swing

import cxplugins.cxfundamental.minecraft.kotlindsl.openFrame
import cxplugins.cxfundamental.minecraft.server.CXInventory
import cxplugins.cxfundamental.minecraft.server.CXItemStack
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

typealias SwingMultipagePanel = CXMultipagePanel

open class CXMultipagePanel(val width: Int, val height: Int) : CXContainer {

    private var pageIndex = 0
    public val pages = ArrayList<SwingPanel>()
    public fun setPage(index: int, page: SwingPanel) {
    }

    override fun repaint() {
    }
}