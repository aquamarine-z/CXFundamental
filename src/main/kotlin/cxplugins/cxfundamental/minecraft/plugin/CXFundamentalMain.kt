package cxplugins.cxfundamental.minecraft.plugin

import cxplugins.cxfundamental.minecraft.kotlindsl.QuestionListener
import cxplugins.cxfundamental.minecraft.kotlindsl.closeFrame

import cxplugins.cxfundamental.minecraft.kotlindsl.toColor
import cxplugins.cxfundamental.minecraft.server.CXItemStack
import cxplugins.cxfundamental.minecraft.server.CXPluginMain
import cxplugins.cxfundamental.minecraft.swing.SwingUIListener
import cxplugins.cxfundamental.minecraft.ui.CXUIActionListener
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment

var debug = false

class CXFundamentalMain : CXPluginMain(null) {
    override fun reload() {

    }

    companion object {
        lateinit var pluginMain: CXFundamentalMain
    }

    override fun onLoad() {
        //RuntimeLoader.checkRuntime()
    }

    override fun onEnable() {
        pluginMain = this
        CXUIActionListener().register()
        SwingUIListener.registerListener()
        Bukkit.getPluginManager().registerEvents(QuestionListener(), this)
        PluginLanguage.initializeEnglishLanguagePack()
        //Bukkit.getPluginManager().registerEvents(CXButtonListener(),this)
        registerCommands()
        var itemStack = CXItemStack(Material.BOOKSHELF, 1, "&3&lCXFundamental", " ")
        var meta = itemStack.itemMeta
        meta.addEnchant(Enchantment.DURABILITY, 5, true)
        itemStack.itemMeta = meta
        this.itemInMenu = itemStack
        this.description = "&3&lThis plugin is the fundamental preposition for cxplugins".toColor()
        return
    }

    override fun onDisable() {
        for (player in Bukkit.getOnlinePlayers()) {
            player.closeFrame()
        }
        return
    }
}
