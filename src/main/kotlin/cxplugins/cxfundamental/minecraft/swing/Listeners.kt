package cxplugins.cxfundamental.minecraft.swing

import cxplugins.cxfundamental.minecraft.kotlindsl.openingWindow
import cxplugins.cxfundamental.minecraft.server.CXInventory
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent

class CXButtonListener:Listener{
    @EventHandler
    fun onInventoryClick(event:InventoryClickEvent){
        if(event.whoClicked.openingWindow!=null){
            val window=event.whoClicked.openingWindow
            if(event.rawSlot !in 0 until event.inventory.size) {
                return
            }
            val x=CXInventory.integerToPos(event.rawSlot).blockX
            val y=CXInventory.integerToPos(event.rawSlot).blockY
            var component=window!!.getComponent(x,y)
            while(component is CXContainer) {
                component=component.getComponent(0,0)
            }
            if(component == null) return
            else if(component is CXButton){
                val buttonEvent=CXButtonEvent(event,window,event.whoClicked as Player,component)
                when(event.click){
                    ClickType.LEFT->{
                        component.leftClickLambda(buttonEvent)
                    }
                    ClickType.DOUBLE_CLICK->{
                        component.doubleClickLambda(buttonEvent)
                    }
                    ClickType.MIDDLE->{
                        component.middleClickLambda(buttonEvent)
                    }
                    ClickType.NUMBER_KEY->{
                        component.numberKeyLambda(buttonEvent)
                    }
                    ClickType.SHIFT_LEFT->{
                        component.leftShiftClickLambda(buttonEvent)
                    }
                    ClickType.DROP->{
                        component.dropLambda(buttonEvent)
                    }
                    ClickType.CONTROL_DROP->{
                        component.controlDropLambda(buttonEvent)
                    }
                    ClickType.SHIFT_RIGHT->{
                        component.rightShiftClickLambda(buttonEvent)
                    }
                    ClickType.CREATIVE->{
                        component.creativeLambda(buttonEvent)
                    }
                    else -> {

                    }
                }
            }
        }
    }
}