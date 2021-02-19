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
        var itemStack=CXItemStack(Material.BOOKSHELF,1,"&3&lCXFundamental(CXǰ�ò��)"," ")
        var meta=itemStack.itemMeta
        meta.addEnchant(Enchantment.DURABILITY,5,true)
        itemStack.itemMeta=meta
        this.itemInMenu=itemStack
        this.description="&3&l�˲��������CX�����ǰ�ò�� ������Ҫ".toColor()

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
                    item.appendLore("&3&l����:${plugin.description}".toColor())
                    item.appendLore("&3&l������ҹرմ˲��")
                    item.appendLore("&3&l�Ҽ����������˲��")
                    item.appendLore("&3&l�������+�¶����ش˲��")

                    var button=object:CXButton(item) {
                        var plugin = plugin

                        override fun onLeftClick(event: InventoryClickEvent,frame:CXFrame) {
                            event.isCancelled = true


                            if (Bukkit.getPluginManager().isPluginEnabled(plugin)) {
                                Bukkit.getPluginManager().disablePlugin(plugin)
                                (sender as Player).sendMessageWithColor("&2&l[CXFundamental]�����ɹ� �˲���Ѿ����ر�")
                            } else {
                                (sender as Player).sendMessageWithColor("&4&l[CXFundamental]���޷��ر�һ���Ѿ����رյĲ��")
                            }
                            return
                        }

                        override fun onRightClick(event: InventoryClickEvent,frame:CXFrame) {
                            event.isCancelled = true
                            if (!Bukkit.getPluginManager().isPluginEnabled(plugin)) {
                                Bukkit.getPluginManager().enablePlugin(plugin)
                                (sender as Player).sendMessageWithColor("&2&l[CXFundamental]�����ɹ� �˲���Ѿ�����")
                            } else {
                                (sender as Player).sendMessageWithColor("&4&l[CXFundamental]���޷�����һ���������еĲ��")
                            }
                            return
                        }

                        override fun onLeftShiftClick(event: InventoryClickEvent,frame:CXFrame) {
                            event.isCancelled = true
                            try {
                                (plugin).reload()
                            } catch (exception: NoSuchMethodException) {
                                (sender as Player).sendMessageWithColor("&4&l[CXFundamental]�˲�����������ع���")
                                return
                            }
                            (sender as Player).sendMessageWithColor("&2&l[CXFundamental]�����ɹ� �˲���Ѿ�����")

                        }
                    }
                    //button.clickSound= Sound.BLOCK_NOTE_BELL
                    var vector=CXInventory.integerToPos(amount%45)
                    multiPagePanel.setWithCreateNewPage(amount/45,vector.blockX,vector.blockY,button,"&3&lCX��������������".toColor())

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
                sender.sendMessageWithColor("&2&l[CXFundamental]Debugģʽ�Ѿ�����Ϊ$debug")
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
