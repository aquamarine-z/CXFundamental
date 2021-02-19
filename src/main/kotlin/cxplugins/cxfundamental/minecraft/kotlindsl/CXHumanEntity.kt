package cxplugins.cxfundamental.minecraft.kotlindsl

import cxplugins.cxfundamental.minecraft.server.CXFundamentalMain
import cxplugins.cxfundamental.minecraft.ui.CXFrame
import cxplugins.cxfundamental.minecraft.ui.CXMultipagePanel
import cxplugins.cxfundamental.minecraft.ui.CXPanel
import org.bukkit.entity.HumanEntity
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerChatEvent
import org.bukkit.inventory.Inventory
import org.bukkit.metadata.FixedMetadataValue
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import java.util.*

/**
 * ����һش������ʱ�Ĳ���
 *
 */
interface QuestionAction{
    fun action(answer:String)
}
class QuestionListener: Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    fun onPlayerChat(event:PlayerChatEvent){
        if(event.player.hasMetadata("question.action")){
            event.isCancelled=true
            var action=event.player.getMetadata("question.action")[0].value() as QuestionAction
            event.player.removeMetadata("question.action",CXFundamentalMain.pluginMain)
            action.action(event.message)
        }
    }
}
/**
 * ѯ��һ���������
 * @param question ���������
 * @param action ��һش��Ĳ���
 */
fun HumanEntity.askQuestion(question:String,action:QuestionAction){
    this.setMetadata("question.action",FixedMetadataValue(CXFundamentalMain.pluginMain,action))
    this.sendMessageWithColor(question)
}
/**
 * ѯ��һ���������
 * @param question ���������
 * @param lambda ��һش��Ĳ���
 */
fun HumanEntity.askQuestion(question:String,lambda:(String)->Unit){
     var action=object:QuestionAction{
         override fun action(answer: String) {
             lambda(answer)
         }
     }
    askQuestion(question,action)
}
/**
 * ʹһ��HumanEntityͬ����һ������Inventory
 * �������첽�߳��е���
 * @param inventory Ҫ�򿪵�����
 * @param plugin ִ�д����������Ĳ��
 */
fun HumanEntity.openInventorySynchronously(inventory:Inventory,plugin: JavaPlugin){
    var thread=object:BukkitRunnable(){
        override fun run() {
            this@openInventorySynchronously.openInventory(inventory)
        }
    }
    thread.runTask(plugin)
}
/**
 * ʹһ������ʵ��رմ򿪵Ĵ���
 */
fun HumanEntity.closeFrame(){
    this.closeInventory()
    this.setOpeningFrame(null)
}
/**
 * ʹһ������ʵ���һ���Ĵ���
 * @param frame Ҫ�򿪵Ĵ���
 */
fun HumanEntity.openFrame(frame:CXFrame){
    if(frame.mainPanel is CXMultipagePanel){
        var mainPanel=frame.mainPanel as CXMultipagePanel
        var inventoryToOpen=mainPanel.pages[mainPanel.index].inventory
        this.openInventory(inventoryToOpen)
    }
    if(frame.mainPanel is CXPanel){
        var mainPanel=frame.mainPanel as CXPanel
        var inventoryToOpen=mainPanel.inventory
        this.openInventory(inventoryToOpen)
    }
    this.setOpeningFrame(frame)
}

/**
 * ��ȡ������ʵ�����ڴ򿪵Ĵ���
 */
fun HumanEntity.getOpeningFrame():CXFrame?{
    return if(this.hasMetadata("openingFrame")){
        var value=this.getMetadata("openingFrame")[0].value()
        if(value is CXFrame) value
        else
            null
    }
    else{
        null
    }
}
/**
 * ���ô�����ʵ�����ڴ򿪵Ĵ���
 */
fun HumanEntity.setOpeningFrame(value:CXFrame?){
    if(value==null){
        this.removeMetadata("openingFrame",CXFundamentalMain.pluginMain)
    }
    else{
        this.setMetadata("openingFrame",FixedMetadataValue(CXFundamentalMain.pluginMain,value))
    }
}

/**
 * �������첽�̵߳��� ʹһ������ʵ��򿪴���
 */
fun HumanEntity.openFrameSynchronously(frame:CXFrame,plugin:JavaPlugin){
    var thread=object:BukkitRunnable(){
        override fun run() {
            this@openFrameSynchronously.openFrame(frame)
        }
    }
    thread.runTask(plugin)
}