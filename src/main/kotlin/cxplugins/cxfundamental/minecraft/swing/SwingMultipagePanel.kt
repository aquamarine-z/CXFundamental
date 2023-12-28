package cxplugins.cxfundamental.minecraft.swing

import cxplugins.cxfundamental.minecraft.language.LanguageManager
import cxplugins.cxfundamental.minecraft.math.geometry.Vector2I
import cxplugins.cxfundamental.minecraft.plugin.CXFundamentalMain
import cxplugins.cxfundamental.minecraft.server.CXItemStack
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent

class SwingMultipagePanel(
    override var position: Vector2I, override var width: Int, override var height: Int,
    override var id: String? = null
) :
    SwingPanel(position, width, height) {
    val base = SwingBaseMultipagePanel(Vector2I(0, 0), width, height - 1)
    val buttonArea = SwingPanel(Vector2I(0, height - 1), width, 1)
    init {
        this.setComponent(base)
        this.setComponent(buttonArea)
        val previousButton =
            object : SwingButton(Vector2I(0, 0), CXItemStack(Material.SLIME_BALL, 1, "Back", "BackButton")) {
                override fun onLeftClick(event: InventoryClickEvent) {
                    super.onLeftClick(event)
                    base.previousPage()
                    val frame = SwingFrame.getOpeningFrame(event.whoClicked as Player)!!
                    SwingFrame.closeFrame(event.whoClicked as Player)
                    SwingFrame.openFrame(event.whoClicked as Player, frame)
                }
            }
        val nextButton =
            object : SwingButton(Vector2I(width - 1, 0), CXItemStack(Material.SLIME_BALL, 1, "Next", "NextButton")) {
                override fun onLeftClick(event: InventoryClickEvent) {
                    super.onLeftClick(event)
                    base.nextPage()
                    val frame = SwingFrame.getOpeningFrame(event.whoClicked as Player)!!
                    SwingFrame.closeFrame(event.whoClicked as Player)
                    SwingFrame.openFrame(event.whoClicked as Player, frame)
                }
            }
        this.buttonArea.setComponent(previousButton)
        this.buttonArea.setComponent(nextButton)
    }

    fun setComponent(page: Int, component: SwingComponent) {
        if (page !in base.panelList.indices) {
            val languagePack = LanguageManager.getLanguageManager(CXFundamentalMain.pluginMain)!!.getSelectedLanguage()
            throw SwingException(
                languagePack["cxplugins.cxfundamental.minecraft.swing.SwingBaseMultipagePanel.PageOutOfBounds"] ?: ""
            )
        }
        base.panelList[page].setComponent(component)
    }

    fun getTopComponent(page: Int, position: Vector2I) {
        if (page !in base.panelList.indices) {
            val languagePack = LanguageManager.getLanguageManager(CXFundamentalMain.pluginMain)!!.getSelectedLanguage()
            throw SwingException(
                languagePack["cxplugins.cxfundamental.minecraft.swing.SwingBaseMultipagePanel.PageOutOfBounds"] ?: ""
            )
        }
        base.panelList[page].getTopComponent(position)
    }

    fun getComponents(page: Int, position: Vector2I) {
        if (page !in base.panelList.indices) {
            val languagePack = LanguageManager.getLanguageManager(CXFundamentalMain.pluginMain)!!.getSelectedLanguage()
            throw SwingException(
                languagePack["cxplugins.cxfundamental.minecraft.swing.SwingBaseMultipagePanel.PageOutOfBounds"] ?: ""
            )
        }
        base.panelList[page].getComponents(position)
    }

    fun previousPage() {
        if (base.pagePointer <= 0) base.pagePointer = base.panelList.size - 1
        else base.pagePointer--
    }

    fun addEmptyPage() {
        base.panelList.add(SwingPanel(Vector2I(0, 0), width, height))
    }

    fun deletePage(page: Int) {
        if (page !in base.panelList.indices) {
            val languagePack = LanguageManager.getLanguageManager(CXFundamentalMain.pluginMain)!!.getSelectedLanguage()
            throw SwingException(
                languagePack["cxplugins.cxfundamental.minecraft.swing.SwingBaseMultipagePanel.PageOutOfBounds"] ?: ""
            )
        }
        base.panelList.removeAt(page)
    }

    fun getPage(page: Int): SwingPanel {
        if (page !in base.panelList.indices) {
            val languagePack = LanguageManager.getLanguageManager(CXFundamentalMain.pluginMain)!!.getSelectedLanguage()
            throw SwingException(
                languagePack["cxplugins.cxfundamental.minecraft.swing.SwingBaseMultipagePanel.PageOutOfBounds"] ?: ""
            )
        }
        return base.panelList[page]
    }

    fun checkIdAvailable(id: String): Boolean {
        for (panel in base.panelList) {
            for (component in panel.componentList) {
                if (component.id == id) return false
            }
        }
        for (component in buttonArea.componentList) {
            if (component.id == id) return false
        }
        return true
    }

    override fun getElementById(id: String): SwingComponent? {
        for (panel in base.panelList) {
            for (component in panel.componentList) {
                if (component.id == id) return component
            }
        }
        for (component in buttonArea.componentList) {
            if (component.id == id) return component
        }
        return null
    }

    override fun containsElementWithId(id: String): Boolean {
        return getElementById(id) != null
    }
}