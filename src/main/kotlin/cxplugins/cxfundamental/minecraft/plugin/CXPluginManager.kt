package cxplugins.cxfundamental.minecraft.plugin

import cxplugins.cxfundamental.minecraft.file.CXYamlConfiguration
import cxplugins.cxfundamental.minecraft.kotlindsl.*
import cxplugins.cxfundamental.minecraft.server.CXInventory
import cxplugins.cxfundamental.minecraft.server.CXItemStack
import cxplugins.cxfundamental.minecraft.server.CXPluginMain
import cxplugins.cxfundamental.minecraft.ui.*
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import java.io.*
import java.net.URL
import java.net.URLDecoder
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.thread

class DownloadedPluginControlFrame(val owner:Player): CXFrame(6){
    init{
        var multiPagePanel= CXMultipagePanel(6)
        var plugins= Bukkit.getPluginManager().plugins
        var amount=0
        for(plugin in plugins){
            if(plugin is CXPluginMain){
                var item=if(plugin.itemInMenu!=null){
                    plugin.itemInMenu!!.clone()
                }
                else{
                    var itemStack= CXItemStack(plugin.itemTypeInMenu,1,plugin.name,"")

                    itemStack.clone()
                }
                item.appendLore("&3&l����:${plugin.description}".toColor())
                item!!.appendLore("&3&l������ҹرմ˲��")
                item!!.appendLore("&3&l�Ҽ����������˲��")
                item!!.appendLore("&3&l�������+�¶����ش˲��")
                item!!.appendLore("&4&l�Ҽ�����+�¶�ɾ���˲��")
                var button=object: CXButton(item!!) {
                    var plugin = plugin

                    override fun onLeftClick(event: InventoryClickEvent, frame:CXFrame) {
                        event.isCancelled = true


                        if (Bukkit.getPluginManager().isPluginEnabled(plugin)) {
                            Bukkit.getPluginManager().disablePlugin(plugin)
                            owner.sendMessageWithColor("&2&l[CXFundamental]�����ɹ� �˲���Ѿ����ر�")
                        } else {
                            owner.sendMessageWithColor("&4&l[CXFundamental]���޷��ر�һ���Ѿ����رյĲ��")
                        }
                        return
                    }

                    override fun onRightClick(event: InventoryClickEvent, frame:CXFrame) {
                        event.isCancelled = true
                        if (!Bukkit.getPluginManager().isPluginEnabled(plugin)) {
                            Bukkit.getPluginManager().enablePlugin(plugin)
                            owner.sendMessageWithColor("&2&l[CXFundamental]�����ɹ� �˲���Ѿ�����")
                        } else {
                            owner.sendMessageWithColor("&4&l[CXFundamental]���޷�����һ���������еĲ��")
                        }
                        return
                    }

                    override fun onLeftShiftClick(event: InventoryClickEvent, frame:CXFrame) {
                        event.isCancelled = true
                        try {
                            (plugin).reload()
                        } catch (exception: NoSuchMethodException) {
                            owner.sendMessageWithColor("&4&l[CXFundamental]�˲�����������ع���")
                            return
                        }
                        owner.sendMessageWithColor("&2&l[CXFundamental]�����ɹ� �˲���Ѿ�����")

                    }

                    override fun onRightShiftClick(event: InventoryClickEvent, frame: CXFrame) {
                        super.onRightShiftClick(event, frame)
                        val optionPane=object:CXOptionPane(1,"&4��ȷ��Ҫɾ���˲����?",CXOptionPaneButtonType.CONFIRM,CXOptionPaneButtonType.CANCEL){
                            override fun onClickConfirm(event: InventoryClickEvent, frame: CXFrame) {
                                super.onClickConfirm(event, frame)
                                Bukkit.getPluginManager().disablePlugin(plugin)
                                if(File(".\\plugins\\${plugin.name}.jar").exists()){
                                    File(".\\plugins\\${plugin.name}.jar").delete()
                                    owner.sendMessageWithColor("&2&l[CXFundamental]�����ɹ� �˲���Ѿ���ɾ��")
                                }
                                else{
                                    owner.sendMessageWithColor("&4&l[����]�޷��ҵ��˲�� ��ȷ���˲�����ļ����Ƿ�Ϊ <�����>.jar")
                                }
                                owner.openFrame(DownloadedPluginControlFrame(owner))
                            }
                            override fun onClickCancel(event: InventoryClickEvent, frame: CXFrame) {
                                super.onClickCancel(event, frame)
                                owner.openFrame(DownloadedPluginControlFrame(owner))
                            }
                        }
                        owner.openFrame(optionPane)
                    }
                }
                //button.clickSound= Sound.BLOCK_NOTE_BELL
                var vector= CXInventory.integerToPos(amount%45)
                multiPagePanel.setWithCreateNewPage(amount/45,vector.blockX,vector.blockY,button,"&3&lCX��������������".toColor())

                amount++
            }
        }
        multiPagePanel.setButtonOnButtonBar(4){
            item=CXItemStack(Material.COMMAND,1,"&3&l��������CXPlugins","&3&l���һ�ȡCXPlugins�������ز˵�")
            leftClick{ inventoryClickEvent: InventoryClickEvent, cxFrame: CXFrame ->
                thread(true,true){

                    inventoryClickEvent.whoClicked.sendMessageWithColor("&3&l���ڴ�Gitee����ȡ����б��ļ�...........")
                    try{
                        getAndSave("https://gitee.com/yuncaiyuye/cxplugins/raw/master/plugins.yml",File(".\\plugins\\CXPlugins"),"plugins.yml")
                    } catch(exception:Exception){
                        inventoryClickEvent.whoClicked.sendMessageWithColor("&2&l[����]��ȡʧ�� ������������!")

                    } finally{
                        inventoryClickEvent.whoClicked.sendMessageWithColor("&3&l��ȡ�ɹ�!")
                    }
                    inventoryClickEvent.whoClicked.openFrameSynchronously(OnlinePluginControlFrame(),CXFundamentalMain.pluginMain)
                }

            }
        }
        this.setPanel(multiPagePanel)
    }
}
class OnlinePluginVersionViewFrame(val pluginName:String):CXFrame(6){
    private fun readUrlFile(fileUrl: String?): ByteArray?  {
        var r: ByteArray? = null
        var out: ByteArrayOutputStream? = null
        try {
            val url = URL(fileUrl)
            val `in` = url.openStream()
            out = ByteArrayOutputStream()
            val b = ByteArray(8 * 1024)
            var read:Int=0
            while (`in`.read(b).also { read = it } != -1) out.write(b, 0, read)
            out.flush()
            r = out.toByteArray()
            out.close()
            `in`.close()
        } catch (e: java.lang.Exception) {
        }
        return r
    }

    private fun downloadPlugin(pluginName:String,address:String):Boolean{
        val file= File(".\\plugins\\$pluginName.jar")
        if(!file.exists()){
            file.createNewFile()
        }
        val writer=BufferedOutputStream(FileOutputStream(file))
        //val onlineStream= BufferedInputStream(URL(URLDecoder.decode(address,"utf-8")).openStream())
        var buf=readUrlFile(address)
        writer.write(buf)
        writer.close()
        return true
    }
    init{
        val configuration=CXYamlConfiguration("CXPlugins","plugins.yml")
        val section=configuration.getConfigurationSection("$pluginName")
        val versions=section.getKeys(false)
        var i=0
        val multipagePanel=CXMultipagePanel(6)
        multipagePanel.addEmptyPage("&3����:$pluginName")
        for(version in versions) {
            val button=object :CXButton(){
                init{
                    item=CXItemStack(Material.CHEST,1,"&c&l$pluginName:$version","")
                    item!!.appendLore("&c&l����:${section["$version.description"] ?: "��"}")
                    item!!.appendLore("&c&l���õ�Minecraft�汾:${section["$version.version"] ?: "����"}")
                    item!!.appendLore("&c&l��������:${section["$version.download"] ?: "��������"}")
                }

                override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                    super.onLeftClick(event, frame)
                    val pluginNames=ArrayList<String>()
                    for(plugin in Bukkit.getPluginManager().plugins){
                        pluginNames.add(plugin.name)
                    }
                    if(pluginName in pluginNames){
                        val optionPane=object:CXOptionPane(1,"&4&l�˲���Ѿ����� ��ȷ��Ҫ������������?",CXOptionPaneButtonType.CONFIRM,CXOptionPaneButtonType.CANCEL){
                            override fun onClickConfirm(event: InventoryClickEvent, frame: CXFrame) {
                                super.onClickConfirm(event, frame)
                                val thread=object :Thread(){
                                    override fun run() {
                                        try {
                                            event.whoClicked.sendMessageWithColor("&3&l��������$pluginName �˹��̿�����Ҫһ��ʱ��...")
                                            downloadPlugin(pluginName, "${section["$version.download"]}")
                                        }
                                        catch (exception:Exception){
                                            exception.printStackTrace()
                                            event.whoClicked.sendMessageWithColor("&4[����]����ʧ�� ������������:${exception.message}")
                                        }
                                        finally {
                                            event.whoClicked.sendMessageWithColor("&2���� $pluginName �ɹ�! ���ֶ����������� ���´˲�� ����:/reload")

                                        }
                                    }
                                }
                                thread.start()
                                event.whoClicked.closeFrame()
                            }

                            override fun onClickCancel(event: InventoryClickEvent, frame: CXFrame) {
                                super.onClickCancel(event, frame)
                                event.whoClicked.openFrame(OnlinePluginVersionViewFrame(pluginName))
                            }
                        }
                        event.whoClicked.openFrame(optionPane)
                        return
                    }
                    val thread=object :Thread(){
                        override fun run() {
                            try {
                                downloadPlugin(pluginName, "${section["$version.download"]}")
                            }
                            catch (exception:Exception){
                                exception.printStackTrace()
                                event.whoClicked.sendMessageWithColor("&4[����]����ʧ�� ������������:${exception.message}")
                            }
                            finally {
                                event.whoClicked.sendMessageWithColor("&2���� $pluginName �ɹ�! ���ֶ����������� ���´˲�� ����:/reload")
                            }
                        }
                    }
                    thread.start()
                }
            }
            multipagePanel.setWithCreateNewPage(i,button,"&3����:$pluginName")
        }
        this.setPanel(multipagePanel)
    }
}
class OnlinePluginControlFrame():CXFrame(6){


    init{
        val configuration=CXYamlConfiguration("CXPlugins","plugins.yml")
        val multipagePanel=CXMultipagePanel(6)
        multipagePanel.addEmptyPage("&3&lCXPlugins:")
        val pluginNames=ArrayList<String>()
        for(plugin in Bukkit.getPluginManager().plugins){
            pluginNames.add(plugin.name)
        }
        var index=0
        for(pluginName in configuration.getKeys(false)) {

            val description = configuration.getString("$pluginName.description", "No description")
            val button = object : CXButton() {
                init {
                    this.item = CXItemStack(Material.CHEST, 1, "&9$pluginName", "&9$description|&9���ҽ���鿴��ϸ")
                }

                override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                    event.whoClicked.openFrame(OnlinePluginVersionViewFrame(pluginName))
                }
            }

            multipagePanel.setWithCreateNewPage(index, button, "&3&lCXPlugins:")
            index++

        }
        multipagePanel.setButtonOnButtonBar(4){
            item=CXItemStack(Material.IRON_DOOR,1,"&3&l����","&3���ҷ��ز�������˵�")
            leftClick { event, cxFrame ->
                event.whoClicked.openFrame(DownloadedPluginControlFrame(event.whoClicked as Player))
            }
        }
        this.setPanel(multipagePanel)
    }

}
