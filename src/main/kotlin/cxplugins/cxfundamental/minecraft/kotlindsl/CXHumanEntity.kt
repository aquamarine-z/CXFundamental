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
 * 当玩家回答此问题时的操作
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
 * 询问一个玩家问题
 * @param question 问题的内容
 * @param action 玩家回答后的操作
 */
fun HumanEntity.askQuestion(question:String,action:QuestionAction){
    this.setMetadata("question.action",FixedMetadataValue(CXFundamentalMain.pluginMain,action))
    this.sendMessageWithColor(question)
}
/**
 * 询问一个玩家问题
 * @param question 问题的内容
 * @param lambda 玩家回答后的操作
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
/**
 * 使一个人类实体关闭打开的窗口
 */
fun HumanEntity.closeFrame(){
    this.closeInventory()
    this.setOpeningFrame(null)
}
/**
 * 使一个人类实体打开一个的窗口
 * @param frame 要打开的窗口
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
 * 获取此人类实体正在打开的窗口
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
 * 设置此人类实体正在打开的窗口
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
 * 可以在异步线程调用 使一个人类实体打开窗口
 */
fun HumanEntity.openFrameSynchronously(frame:CXFrame,plugin:JavaPlugin){
    var thread=object:BukkitRunnable(){
        override fun run() {
            this@openFrameSynchronously.openFrame(frame)
        }
    }
    thread.runTask(plugin)
}