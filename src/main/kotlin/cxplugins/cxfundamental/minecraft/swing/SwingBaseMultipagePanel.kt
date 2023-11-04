package cxplugins.cxfundamental.minecraft.swing

import cxplugins.cxfundamental.minecraft.language.LanguageManager
import cxplugins.cxfundamental.minecraft.math.geometry.Vector2I
import cxplugins.cxfundamental.minecraft.plugin.CXFundamentalMain
import org.bukkit.inventory.ItemStack

open class SwingBaseMultipagePanel(
    override var position: Vector2I,
    override var width: Int,
    override var height: Int
) : SwingContainer {
    var pagePointer = 0
        set(value) {
            field = if (value <= 0) value
            else if (value >= panelList.size) panelList.size - 1
            else value
        }
    var panelList = mutableListOf<SwingPanel>()

    override fun setComponent(component: SwingComponent) {
        if (panelList.size == 0) {
            val languagePack = LanguageManager.getLanguageManager(CXFundamentalMain.pluginMain)!!.getSelectedLanguage()
            throw SwingException(
                languagePack["cxplugins.cxfundamental.minecraft.swing.SwingBeseMultipagePanel.PageOutOfBounds"] ?: ""
            )
        }
        panelList[pagePointer].setComponent(component)
    }

    override fun getTopComponent(position: Vector2I): SwingComponent? {
        //println("page=$pagePointer")
        if (panelList.size == 0) {
            val languagePack = LanguageManager.getLanguageManager(CXFundamentalMain.pluginMain)!!.getSelectedLanguage()
            throw SwingException(
                languagePack["cxplugins.cxfundamental.minecraft.swing.SwingBeseMultipagePanel.PageOutOfBounds"] ?: ""
            )
        }
        return panelList[pagePointer].getTopComponent(position)
    }

    override fun getComponents(position: Vector2I): Array<SwingComponent> {
        if (panelList.size == 0) {
            val languagePack = LanguageManager.getLanguageManager(CXFundamentalMain.pluginMain)!!.getSelectedLanguage()
            throw SwingException(
                languagePack["cxplugins.cxfundamental.minecraft.swing.SwingBeseMultipagePanel.PageOutOfBounds"] ?: ""
            )
        }
        return panelList[pagePointer].getComponents(position)
    }

    fun setComponent(page: Int, component: SwingComponent) {
        if (page !in panelList.indices) {
            val languagePack = LanguageManager.getLanguageManager(CXFundamentalMain.pluginMain)!!.getSelectedLanguage()
            throw SwingException(
                languagePack["cxplugins.cxfundamental.minecraft.swing.SwingBeseMultipagePanel.PageOutOfBounds"] ?: ""
            )
        }
        panelList[page].setComponent(component)
    }

    fun getTopComponent(page: Int, position: Vector2I) {
        if (page !in panelList.indices) {
            val languagePack = LanguageManager.getLanguageManager(CXFundamentalMain.pluginMain)!!.getSelectedLanguage()
            throw SwingException(
                languagePack["cxplugins.cxfundamental.minecraft.swing.SwingBeseMultipagePanel.PageOutOfBounds"] ?: ""
            )
        }
        panelList[page].getTopComponent(position)
    }

    fun getComponents(page: Int, position: Vector2I) {
        if (page !in panelList.indices) {
            val languagePack = LanguageManager.getLanguageManager(CXFundamentalMain.pluginMain)!!.getSelectedLanguage()
            throw SwingException(
                languagePack["cxplugins.cxfundamental.minecraft.swing.SwingBeseMultipagePanel.PageOutOfBounds"] ?: ""
            )
        }
        panelList[page].getComponents(position)
    }

    override fun resize(width: Int, height: Int) {
        for (panel in panelList) {
            panel.height = height
            panel.width = width
        }
        this.height = height
        this.width = width

    }

    override fun setBounds(x: Int, y: Int, w: Int, h: Int) {
        for (panel in panelList) {
            panel.resize(w, h)
        }

        this.relocate(x, y)
        this.resize(w, h)

    }

    override fun repaint(): Array<Array<ItemStack?>> {
        return panelList[pagePointer].repaint()
    }


    override fun relocate(x: Int, y: Int) {
        if (x !in 1..9) {
            val languagePack = LanguageManager.getLanguageManager(CXFundamentalMain.pluginMain)!!.getSelectedLanguage()
            throw SwingException(
                languagePack["cxplugins.cxfundamental.minecraft.swing.SwingBeseMultipagePanel.PositionError"] ?: ""
            )
        }
        if (y !in 1..6) {
            val languagePack = LanguageManager.getLanguageManager(CXFundamentalMain.pluginMain)!!.getSelectedLanguage()
            throw SwingException(
                languagePack["cxplugins.cxfundamental.minecraft.swing.SwingBeseMultipagePanel.PositionError"] ?: ""
            )
        }
        this.position = Vector2I(x, y)
    }

    override fun relocate(point: Vector2I) {
        if (point.x !in 1..9) {
            val languagePack = LanguageManager.getLanguageManager(CXFundamentalMain.pluginMain)!!.getSelectedLanguage()
            throw SwingException(
                languagePack["cxplugins.cxfundamental.minecraft.swing.SwingBeseMultipagePanel.PositionError"] ?: ""
            )
        }
        if (point.y !in 1..6) {
            val languagePack = LanguageManager.getLanguageManager(CXFundamentalMain.pluginMain)!!.getSelectedLanguage()
            throw SwingException(
                languagePack["cxplugins.cxfundamental.minecraft.swing.SwingBeseMultipagePanel.PositionError"] ?: ""
            )
        }
        this.position = point
    }

    fun nextPage() {
        if (pagePointer >= panelList.size - 1) {
            pagePointer = 0
        } else pagePointer++
    }

    fun previousPage() {
        if (pagePointer <= 0) pagePointer = panelList.size - 1
        else pagePointer--
    }

    fun addEmptyPage() {
        panelList.add(SwingPanel(Vector2I(0, 0), width, height))
    }

    fun deletePage(page: Int) {
        if (page !in panelList.indices) {
            val languagePack = LanguageManager.getLanguageManager(CXFundamentalMain.pluginMain)!!.getSelectedLanguage()
            throw SwingException(
                languagePack["cxplugins.cxfundamental.minecraft.swing.SwingBeseMultipagePanel.PageOutOfBounds"] ?: ""
            )
        }
        panelList.removeAt(page)
    }

    fun getPage(page: Int): SwingPanel {
        if (page !in panelList.indices) {
            val languagePack = LanguageManager.getLanguageManager(CXFundamentalMain.pluginMain)!!.getSelectedLanguage()
            throw SwingException(
                languagePack["cxplugins.cxfundamental.minecraft.swing.SwingBeseMultipagePanel.PageOutOfBounds"] ?: ""
            )
        }
        return panelList[page]
    }
}