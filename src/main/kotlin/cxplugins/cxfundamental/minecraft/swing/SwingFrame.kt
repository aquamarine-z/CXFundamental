@file:Suppress("DIFFERENT_NAMES_FOR_THE_SAME_PARAMETER_IN_SUPERTYPES")

package cxplugins.cxfundamental.minecraft.swing

import cxplugins.cxfundamental.minecraft.language.LanguageManager
import cxplugins.cxfundamental.minecraft.math.geometry.Vector2I
import cxplugins.cxfundamental.minecraft.plugin.CXFundamentalMain
import cxplugins.cxfundamental.minecraft.server.CXInventory
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.metadata.FixedMetadataValue

internal fun cutTitle(from: String): String {
    return if (from.length < 31) from
    else from.substring(0, 25) + "..."
}

open class SwingFrame(var title: String, override var height: Int) : SwingWindow,
    SwingPanel(Vector2I(0, 0), 9, height) {
    override var width: Int = 9

    init {
        title = cutTitle(title)
        if (height !in 1..6) {
            val languagePack = LanguageManager.getLanguageManager(CXFundamentalMain.pluginMain)!!.getSelectedLanguage()
            throw SwingException(
                languagePack["cxplugins.cxfundamental.minecraft.swing.SwingFrame.init.HeightOutOfBounds"] ?: ""
            )
        }
    }

    override fun generateInventory(): Inventory {
        val itemArray = this.repaint()
        val inventory = Bukkit.createInventory(null, 9 * height, title)
        for (i in 0 until 9) {
            for (j in 0 until height) {
                val place = CXInventory.posToInteger(i, j)
                //print("i=$i j=$j place=$place")
                inventory.setItem(place, itemArray[i][j])
            }
        }
        return inventory
    }

    override fun resize(width: Int, height: Int) {
        if (width != 9) {
            val languagePack = LanguageManager.getLanguageManager(CXFundamentalMain.pluginMain)!!.getSelectedLanguage()
            throw SwingException(languagePack["cxplugins.cxfundamental.minecraft.swing.SwingFrame.WidthError"] ?: "")
        }
        if (height !in 1..6) {
            val languagePack = LanguageManager.getLanguageManager(CXFundamentalMain.pluginMain)!!.getSelectedLanguage()
            throw SwingException(
                languagePack["cxplugins.cxfundamental.minecraft.swing.SwingFrame.HeightOutOfBounds"] ?: ""
            )
        } else {
            this.height = height
        }
    }

    override fun setBounds(x: Int, y: Int, w: Int, h: Int) {
        this.resize(w, h)
    }

    override fun relocate(point: Vector2I) {
        val languagePack = LanguageManager.getLanguageManager(CXFundamentalMain.pluginMain)!!.getSelectedLanguage()
        throw SwingException(languagePack["cxplugins.cxfundamental.minecraft.swing.SwingFrame.Unrelocatable"] ?: "")
    }

    override fun relocate(x: Int, y: Int) {
        val languagePack = LanguageManager.getLanguageManager(CXFundamentalMain.pluginMain)!!.getSelectedLanguage()
        throw SwingException(languagePack["cxplugins.cxfundamental.minecraft.swing.SwingFrame.Unrelocatable"] ?: "")
    }

    companion object {
        fun openFrame(player: Player, frame: SwingFrame) {
            val inventory = frame.generateInventory()
            if (!player.isOnline) {
                return
            } else {
                player.setMetadata("openingSwingFrame", FixedMetadataValue(CXFundamentalMain.pluginMain, frame))
                player.openInventory(inventory)
            }
        }

        fun closeFrame(player: Player) {
            if (player.hasMetadata("openingSwingFrame")) {
                player.removeMetadata("openingSwingFrame", CXFundamentalMain.pluginMain)
                player.closeInventory()
            }
        }

        fun isOpeningFrame(player: Player): Boolean {
            return player.hasMetadata("openingSwingFrame")
        }

        fun getOpeningFrame(player: Player): SwingFrame? {
            return if (isOpeningFrame(player)) {
                player.getMetadata("openingSwingFrame")[0].value() as SwingFrame
            } else {
                null
            }
        }
    }
}