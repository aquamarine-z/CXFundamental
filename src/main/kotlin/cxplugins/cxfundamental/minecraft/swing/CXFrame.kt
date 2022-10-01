package cxplugins.cxfundamental.minecraft.swing

import cxplugins.cxfundamental.minecraft.server.CXInventory
import org.bukkit.Bukkit
import org.bukkit.inventory.Inventory
typealias SwingFrame=CXFrame
open class CXFrame(var frameHeight:Int=6, title:String="CXFrame") : CXWindow,CXPanel(9,frameHeight) {
    override var title:String="CXFrame"
    get() {
        return field
    }
    set(value) {
        field = if(value.length>26){
            value.subSequence(0,26).toString()+"..."
        } else{
            value
        }
    }
    init{
        this.title=title
        if(frameHeight !in 1..6){
            throw IllegalArgumentException("面板的高度参数必须在1~6之间")
        }
        elementArray=Array(9) { Array(frameHeight) { null } }
    }
    override fun asInventory(): Inventory {
        val inventory= Bukkit.createInventory(null,frameHeight*9,title)
        val itemArray=this.repaint(0,0)
        for(x in itemArray.indices){
            for(y in itemArray[x].indices){
                inventory.setItem(CXInventory.posToInteger(x,y),itemArray[x][y])
            }
        }
        return inventory
    }
}