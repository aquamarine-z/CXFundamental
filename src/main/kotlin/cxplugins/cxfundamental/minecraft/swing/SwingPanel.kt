package cxplugins.cxfundamental.minecraft.swing

import cxplugins.cxfundamental.minecraft.language.LanguageManager
import cxplugins.cxfundamental.minecraft.math.geometry.Rectangle2I
import cxplugins.cxfundamental.minecraft.math.geometry.Vector2I
import cxplugins.cxfundamental.minecraft.plugin.CXFundamentalMain
import org.bukkit.inventory.ItemStack

open class SwingPanel(
    override var position: Vector2I, override var width: Int, override var height: Int,
    override var id: String? = null
) :
    SwingContainer {
    val componentList = mutableListOf<SwingComponent>()
    override fun setComponent(component: SwingComponent) {
        /*val panelRect=Rectangle2I(0,0,width-1,height-1)
        if(!panelRect.isPointInside(component.position)){
            println("${component.position.x} ${component.position.y}")
            val languagePack= LanguageManager.getLanguageManager(CXFundamentalMain.pluginMain)!!.getSelectedLanguage()
            throw SwingException(languagePack["cxplugins.cxfundamental.minecraft.swing.SwingPanel.PositionOutOfBounds"]?:"")
        }*/
        componentList.add(component)
    }

    override fun getTopComponent(pos: Vector2I): SwingComponent? {
        /*val panelRect=Rectangle2I(0,0,width-1,height-1)
        if(!panelRect.isPointInside(pos)){
            val languagePack= LanguageManager.getLanguageManager(CXFundamentalMain.pluginMain)!!.getSelectedLanguage()
            throw SwingException(languagePack["cxplugins.cxfundamental.minecraft.swing.SwingPanel.PositionOutOfBounds"]?:"")
        }*/
        val x = pos.x
        val y = pos.y
        for (i in componentList.size - 1 downTo 0) {
            val component = componentList[i]
            if (component is SwingContainer) {
                val rect = Rectangle2I(
                    component.position.x,
                    component.position.y,
                    component.position.x + component.width - 1,
                    component.position.y + component.height - 1
                )
                if (rect.isPointInside(Vector2I(x, y))) {
                    //println("1")
                    return component
                }

            } else {
                if ((component.position.x == x) && (component.position.y == y)) {
                    //println(component.javaClass.toString()+"${component.position.x} ${component.position.y}")
                    //println("${position.x} ${position.y}")
                    return component
                }

            }
        }

        return null
    }

    override fun getComponents(position: Vector2I): Array<SwingComponent> {
        /*val panelRect=Rectangle2I(this.position.x,this.position.y,this.position.x+width-1,this.position.y+height-1)
        if(!panelRect.isPointInside(position)){
            val languagePack= LanguageManager.getLanguageManager(CXFundamentalMain.pluginMain)!!.getSelectedLanguage()
            throw SwingException(languagePack["cxplugins.cxfundamental.minecraft.swing.SwingPanel.PositionOutOfBounds"]?:"")
        }*/
        val x = position.x
        val y = position.y
        val result = mutableListOf<SwingComponent>()
        for (i in componentList.size - 1 downTo 0) {
            val component = componentList[i]
            if (component is SwingContainer) {
                val rect = Rectangle2I(
                    component.position.x,
                    component.position.y,
                    component.position.x + width,
                    component.position.y + height
                )
                if (rect.isPointInside(Vector2I(x, y))) {
                    result.add(component)
                } else {
                    if (component.position.x == x && component.position.y == y) {
                        result.add(component)
                    }
                }
            }
        }
        return result.toTypedArray()
    }

    override fun resize(width: Int, height: Int) {
        if (width !in 1..9) {
            val languagePack = LanguageManager.getLanguageManager(CXFundamentalMain.pluginMain)!!.getSelectedLanguage()
            throw SwingException(languagePack["cxplugins.cxfundamental.minecraft.swing.SwingPanel.WidthError"] ?: "")
        }
        if (height !in 1..6) {
            val languagePack = LanguageManager.getLanguageManager(CXFundamentalMain.pluginMain)!!.getSelectedLanguage()
            throw SwingException(languagePack["cxplugins.cxfundamental.minecraft.swing.SwingPanel.HeightError"] ?: "")
        }
        this.width = width
        this.height = height
    }

    override fun containsElementWithId(id: String): Boolean {
        for (component in componentList) {
            if (component.id == id) return true
        }
        return false
    }

    override fun getElementById(id: String): SwingComponent? {
        for (element in this.componentList) {
            if (element.id == id) return element
        }
        return null
    }

    override fun relocate(x: Int, y: Int) {
        if (x !in 1..9) {
            val languagePack = LanguageManager.getLanguageManager(CXFundamentalMain.pluginMain)!!.getSelectedLanguage()
            throw SwingException(languagePack["cxplugins.cxfundamental.minecraft.swing.SwingPanel.PositionError"] ?: "")
        }
        if (y !in 1..6) {
            val languagePack = LanguageManager.getLanguageManager(CXFundamentalMain.pluginMain)!!.getSelectedLanguage()
            throw SwingException(languagePack["cxplugins.cxfundamental.minecraft.swing.SwingPanel.PositionError"] ?: "")
        }
        this.position = Vector2I(x, y)
    }

    override fun relocate(point: Vector2I) {
        if (point.x !in 1..9) {
            val languagePack = LanguageManager.getLanguageManager(CXFundamentalMain.pluginMain)!!.getSelectedLanguage()
            throw SwingException(languagePack["cxplugins.cxfundamental.minecraft.swing.SwingPanel.PositionError"] ?: "")
        }
        if (point.y !in 1..6) {
            val languagePack = LanguageManager.getLanguageManager(CXFundamentalMain.pluginMain)!!.getSelectedLanguage()
            throw SwingException(languagePack["cxplugins.cxfundamental.minecraft.swing.SwingPanel.PositionError"] ?: "")
        }
        this.position = point
    }

    override fun setBounds(x: Int, y: Int, w: Int, h: Int) {
        relocate(x, y)
        resize(w, h)
    }

    override fun repaint(): Array<Array<ItemStack?>> {
        var result = createMatrixOfNulls<ItemStack?>(width, height)

        for (i in componentList.indices) {
            val component = componentList[i]
            val repaintArray = component.repaint()
            val componentX = component.position.x
            val componentY = component.position.y
            for (j in 0 until component.width) {
                for (k in 0 until component.height) {
                    val itemX = componentX + j
                    val itemY = componentY + k
                    if ((itemX in 0 until width) && (itemY in 0 until height)) {
                        result[itemX][itemY] = repaintArray[j][k]
                    }
                }
            }
        }
        return result
    }


}