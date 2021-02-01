package cxplugins.cxfundamental.minecraft.ui

import cxplugins.cxfundamental.minecraft.kotlindsl.openFrame
import cxplugins.cxfundamental.minecraft.server.CXInventory
import cxplugins.cxfundamental.minecraft.server.CXItemStack
import org.bukkit.event.inventory.InventoryClickEvent

open class CXCheckBox(text:String, lore:Array<String>?=null) : CXButton() {
    override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
        event.isCancelled=true
        onChanged()
        if(this@CXCheckBox.item==selected){
            this@CXCheckBox.item=unselected
            if(frame.mainPanel is CXPanel){
                var mainPanel=frame.mainPanel as CXPanel
                var x=CXInventory.integerToPos(event.rawSlot).blockX
                var y=CXInventory.integerToPos(event.rawSlot).blockY
                mainPanel.set(x,y,this@CXCheckBox)
                frame.mainPanel=mainPanel
            }
            else if(frame.mainPanel is CXMultipagePanel){
                var mainPanel=frame.mainPanel as CXMultipagePanel
                var x=CXInventory.integerToPos(event.rawSlot).blockX
                var y=CXInventory.integerToPos(event.rawSlot).blockY
                mainPanel.set(mainPanel.index,x,y,this@CXCheckBox)
                frame.mainPanel=mainPanel
            }
            event.whoClicked.openFrame(frame)
            onUnselect()
        }
        else{
            this@CXCheckBox.item=selected
            if(frame.mainPanel is CXPanel){
                var mainPanel=frame.mainPanel as CXPanel
                var x=CXInventory.integerToPos(event.rawSlot).blockX
                var y=CXInventory.integerToPos(event.rawSlot).blockY
                mainPanel.set(x,y,this@CXCheckBox)
                frame.mainPanel=mainPanel
            }
            else if(frame.mainPanel is CXMultipagePanel){
                var mainPanel=frame.mainPanel as CXMultipagePanel
                var x=CXInventory.integerToPos(event.rawSlot).blockX
                var y=CXInventory.integerToPos(event.rawSlot).blockY
                mainPanel.set(mainPanel.index,x,y,this@CXCheckBox)
                frame.mainPanel=mainPanel
            }
            event.whoClicked.openFrame(frame)
            onSelect()
        }

    }
    var changed:CXCheckBox.()->Unit={

    }
    fun onChanged(lambda:CXCheckBox.()->Unit){
        changed=lambda
    }
    open fun onChanged(){
        this.apply(changed)
    }

    var select:CXCheckBox.()->Unit={

    }
    fun onSelect(lambda:CXCheckBox.()->Unit){
        select=lambda
    }
    open fun onSelect(){
        this.apply(select)
    }

    var unselect:CXCheckBox.()->Unit={

    }
    fun onUnselect(lambda:CXCheckBox.()->Unit){
        unselect=lambda
    }
    open fun onUnselect(){
        this.apply(unselect)
    }
    var selected=if(lore!=null){
        CXItemStack(160,1,text,lore,5)
    }
    else{
        CXItemStack(160,1,text,"",5)
    }
    var unselected=if(lore!=null){
        CXItemStack(160,1,text,lore,0)
    }
    else{
        CXItemStack(160,1,text,"",0)
    }
    var isSelected=false
    init{
        this.item=unselected
    }
}