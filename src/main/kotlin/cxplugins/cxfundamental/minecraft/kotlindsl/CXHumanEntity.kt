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
fun HumanEntity.askQuestion(question:String,action:QuestionAction){
    this.setMetadata("question.action",FixedMetadataValue(CXFundamentalMain.pluginMain,action))
    this.sendMessageWithColor(question)
}
fun HumanEntity.askQuestion(question:String,lambda:(String)->Unit){
     var action=object:QuestionAction{
         override fun action(answer: String) {
             lambda(answer)
         }
     }
    askQuestion(question,action)
}
/**
 * 使一个HumanEntity同步打开一个容器Inventory
 * 可以在异步线程中调用
 * @param inventory 要打开的容器
 * @param plugin 执行打开容器操作的插件
 */
fun HumanEntity.openInventorySynchronously(inventory:Inventory,plugin: JavaPlugin){
    var thread=object:BukkitRunnable(){
        override fun run() {
            this@openInventorySynchronously.openInventory(inventory)
        }
    }
    thread.runTask(plugin)
}
fun HumanEntity.closeFrame(){
    this.closeInventory()
    this.setOpeningFrame(null)
}
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
 * 此人类实体正在打开的窗口
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
fun HumanEntity.setOpeningFrame(value:CXFrame?){
    if(value==null){
        this.removeMetadata("openingFrame",CXFundamentalMain.pluginMain)
    }
    else{
        this.setMetadata("openingFrame",FixedMetadataValue(CXFundamentalMain.pluginMain,value))
    }
}


fun HumanEntity.openFrameSynchronously(frame:CXFrame,plugin:JavaPlugin){
    var thread=object:BukkitRunnable(){
        override fun run() {
            this@openFrameSynchronously.openFrame(frame)
        }
    }
    thread.runTask(plugin)
}