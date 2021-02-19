package cxplugins.cxfundamental.minecraft.server

import cxplugins.cxfundamental.minecraft.command.CPMLCommandExecutor
import cxplugins.cxfundamental.minecraft.kotlindsl.*
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
        var itemStack=CXItemStack(Material.BOOKSHELF,1,"&3&lCXFundamental(CX前置插件)"," ")
        var meta=itemStack.itemMeta
        meta.addEnchant(Enchantment.DURABILITY,5,true)
        itemStack.itemMeta=meta
        this.itemInMenu=itemStack
        this.description="&3&l此插件是所有CX插件的前置插件 及其重要".toColor()

    }
    var onOpenMenu=object : cxplugins.cxfundamental.minecraft.command.Action() {
        override fun action():Boolean {
            var frame=CXFrame(6)
            var multiPagePanel=CXMultipagePanel(6)
            var plugins= Bukkit.getPluginManager().plugins
            var amount=0
            for(plugin in plugins){
                if(plugin is CXPluginMain){
                    var item=if(plugin.itemInMenu!=null){
                        plugin.itemInMenu!!.clone()
                    }
                    else{
                        var itemStack=CXItemStack(plugin.itemTypeInMenu,1,plugin.name,"")

                        itemStack.clone()
                    }
                    item.appendLore("&3&l介绍:${plugin.description}".toColor())
                    item.appendLore("&3&l左键点我关闭此插件")
                    item.appendLore("&3&l右键点我启动此插件")
                    item.appendLore("&3&l左键点我+下蹲重载此插件")

                    var button=object:CXButton(item) {
                        var plugin = plugin

                        override fun onLeftClick(event: InventoryClickEvent,frame:CXFrame) {
                            event.isCancelled = true


                            if (Bukkit.getPluginManager().isPluginEnabled(plugin)) {
                                Bukkit.getPluginManager().disablePlugin(plugin)
                                (sender as Player).sendMessageWithColor("&2&l[CXFundamental]操作成功 此插件已经被关闭")
                            } else {
                                (sender as Player).sendMessageWithColor("&4&l[CXFundamental]你无法关闭一个已经被关闭的插件")
                            }
                            return
                        }

                        override fun onRightClick(event: InventoryClickEvent,frame:CXFrame) {
                            event.isCancelled = true
                            if (!Bukkit.getPluginManager().isPluginEnabled(plugin)) {
                                Bukkit.getPluginManager().enablePlugin(plugin)
                                (sender as Player).sendMessageWithColor("&2&l[CXFundamental]操作成功 此插件已经开启")
                            } else {
                                (sender as Player).sendMessageWithColor("&4&l[CXFundamental]你无法开启一个正在运行的插件")
                            }
                            return
                        }

                        override fun onLeftShiftClick(event: InventoryClickEvent,frame:CXFrame) {
                            event.isCancelled = true
                            try {
                                (plugin).reload()
                            } catch (exception: NoSuchMethodException) {
                                (sender as Player).sendMessageWithColor("&4&l[CXFundamental]此插件不包含重载功能")
                                return
                            }
                            (sender as Player).sendMessageWithColor("&2&l[CXFundamental]操作成功 此插件已经重载")

                        }
                    }
                    //button.clickSound= Sound.BLOCK_NOTE_BELL
                    var vector=CXInventory.integerToPos(amount%45)
                    multiPagePanel.setWithCreateNewPage(amount/45,vector.blockX,vector.blockY,button,"&3&lCX附属插件管理界面".toColor())

                    amount++
                }
            }
            frame.setPanel(multiPagePanel)
            (sender as Player).openFrame(frame)
            return true
        }
    }
    override fun onEnable() {
        pluginMain=this
        CXUIActionListener().register()
        Bukkit.getPluginManager().registerEvents(QuestionListener(),this)
        cxplugins.cxfundamental.minecraft.command.CPMLCommandExecutor.register("cxp","",onOpenMenu)
        CPMLCommandExecutor.register("cxp debug"){
            action{
                debug=!debug
                sender.sendMessageWithColor("&2&l[CXFundamental]Debug模式已经更改为$debug")
                true
            }
        }
        updateBlockData()
        return
    }
    override fun onDisable() {
        for(player in Bukkit.getOnlinePlayers()){
            player.closeFrame()
        }
        return
    }
}
