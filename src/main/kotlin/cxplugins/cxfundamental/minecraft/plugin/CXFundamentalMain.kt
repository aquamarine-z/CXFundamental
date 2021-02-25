package cxplugins.cxfundamental.minecraft.plugin

import cxplugins.cxfundamental.minecraft.command.CPMLCommandExecutor
import cxplugins.cxfundamental.minecraft.kotlindsl.*
import cxplugins.cxfundamental.minecraft.server.CXInventory
import cxplugins.cxfundamental.minecraft.server.CXItemStack
import cxplugins.cxfundamental.minecraft.server.CXPluginMain
import cxplugins.cxfundamental.minecraft.ui.*
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
var debug=false
class CXFundamentalMain : CXPluginMain(null) {
    override fun reload() {
        updateBlockData()
    }
    companion object
    {
        lateinit var pluginMain:CXFundamentalMain
    }
    init{
        var itemStack= CXItemStack(Material.BOOKSHELF,1,"&3&lCXFundamental(CX前置插件)"," ")
        var meta=itemStack.itemMeta
        meta.addEnchant(Enchantment.DURABILITY,5,true)
        itemStack.itemMeta=meta
        this.itemInMenu=itemStack
        this.description="&3&l此插件是所有CX插件的前置插件 及其重要".toColor()
    }
    override fun onEnable() {

        pluginMain=this
        CXUIActionListener().register()
        Bukkit.getPluginManager().registerEvents(QuestionListener(),this)
        updateBlockData()
        registerCommands()
        return
    }
    override fun onDisable() {
        for(player in Bukkit.getOnlinePlayers()){
            player.closeFrame()
        }
        return
    }
}
