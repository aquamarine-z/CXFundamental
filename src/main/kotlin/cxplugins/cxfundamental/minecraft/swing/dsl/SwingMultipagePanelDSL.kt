package cxplugins.cxfundamental.minecraft.swing.dsl

import cxplugins.cxfundamental.minecraft.swing.SwingMultipagePanel
import cxplugins.cxfundamental.minecraft.swing.SwingPanel

fun SwingMultipagePanel.newPage(pageContent: SwingPanel.() -> Unit) {
    this.addEmptyPage()
    this.getPage(this.base.panelList.size - 1).apply(pageContent)
}

fun SwingMultipagePanel.pageAt(index: Int, pageContent: SwingPanel.() -> Unit) {
    this.getPage(index).apply(pageContent)
}

fun SwingMultipagePanel.buttonArea(panelContent: SwingPanel.() -> Unit) {
    this.buttonArea.run(panelContent)
}