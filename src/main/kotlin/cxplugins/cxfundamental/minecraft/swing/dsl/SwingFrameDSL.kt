package cxplugins.cxfundamental.minecraft.swing.dsl

import cxplugins.cxfundamental.minecraft.swing.SwingFrame
import cxplugins.cxfundamental.minecraft.swing.SwingMultipagePanel
import cxplugins.cxfundamental.minecraft.swing.SwingPanel

fun SwingFrame.multipagePanel(content: SwingMultipagePanel.() -> Unit) {
    val panelToAdd = SwingMultipagePanel(position, width, height)
    panelToAdd.apply(content)
    this.setComponent(panelToAdd)
}

fun SwingFrame.panel(content: SwingPanel.() -> Unit) {
    val panelToAdd = SwingPanel(position, width, height)
    panelToAdd.apply(content)
    this.setComponent(panelToAdd)
}

fun SwingFrame.setContent(lambda: SwingFrame.() -> Unit) {
    this.apply(lambda)
}