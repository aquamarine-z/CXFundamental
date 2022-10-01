package cxplugins.cxfundamental.minecraft.swing

import cxplugins.cxfundamental.minecraft.kotlindsl.setDisplayName
import cxplugins.cxfundamental.minecraft.kotlindsl.toColor
import cxplugins.cxfundamental.minecraft.server.CXItemStack
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
typealias SwingButton=CXButton
var SwingButton.title:String
set(value) {
    this.texture.setDisplayName(value.toColor())
}
get() = this.texture.itemMeta.displayName
var SwingButton.material:Material
set(value) {
    this.texture.type=value
}
get() = this.texture.type
var SwingButton.materialId:Int
set(value) {
    this.texture.typeId=value
}
get() = this.texture.typeId
var SwingButton.description:String
set(value) {
    this.texture.itemMeta.lore=value.split("|").toColor()
}
get(){
    val loreList=this.texture.itemMeta.lore
    var value=""
    for(lore in loreList){
        value+=lore
        value+="|"
    }
    return value
}
var SwingButton.amount:Int
set(value) {
    this.texture.amount=value
}
get()=this.texture.amount


open class CXButton : CXComponent {
    var texture:ItemStack= CXItemStack(Material.SLIME_BALL,1,"Button","Button")
    open fun onDoubleClick(event:CXButtonEvent){
        event.event.isCancelled=true

    }
    var doubleClickLambda=fun(event:CXButtonEvent){
        onDoubleClick(event)
        return
    }
    fun doubleClick(lambda:(CXButtonEvent)->Unit){
        val function = fun(event:CXButtonEvent){
            lambda(event)
            return
        }
        doubleClickLambda= function
    }
    open fun onLeftClick(event:CXButtonEvent){
        event.event.isCancelled=true

    }
    var leftClickLambda=fun(event:CXButtonEvent){
        onLeftClick(event)
        return
    }
    fun leftClick(lambda:(CXButtonEvent)->Unit){
        val function = fun(event:CXButtonEvent){
            lambda(event)
            return
        }
        leftClickLambda= function
    }
    open fun onLeftShiftClick(event:CXButtonEvent){
        event.event.isCancelled=true

    }
    var leftShiftClickLambda=fun(event:CXButtonEvent){
        onLeftShiftClick(event)
        return
    }
    fun leftShiftClick(lambda:(CXButtonEvent)->Unit){
        val function = fun(event:CXButtonEvent){
            lambda(event)
            return
        }
        leftShiftClickLambda= function
    }
    open fun onRightClick(event:CXButtonEvent){
        event.event.isCancelled=true

    }
    var rightClickLambda=fun(event:CXButtonEvent){
        onRightClick(event)
        return
    }
    fun rightClick(lambda:(CXButtonEvent)->Unit){
        val function = fun(event:CXButtonEvent){
            lambda(event)
            return
        }
        rightClickLambda= function
    }
    open fun onRightShiftClick(event:CXButtonEvent){
        event.event.isCancelled=true

    }
    var rightShiftClickLambda=fun(event:CXButtonEvent){
        onRightShiftClick(event)
        return
    }
    fun rightShiftClick(lambda:(CXButtonEvent)->Unit){
        val function = fun(event:CXButtonEvent){
            lambda(event)
            return
        }
        rightShiftClickLambda= function
    }
    open fun onMiddleClick(event:CXButtonEvent){
        event.event.isCancelled=true

    }
    var middleClickLambda=fun(event:CXButtonEvent){
        onMiddleClick(event)
        return
    }
    fun middleClick(lambda:(CXButtonEvent)->Unit){
        val function = fun(event:CXButtonEvent){
            lambda(event)
            return
        }
        middleClickLambda= function
    }
    open fun onDrop(event:CXButtonEvent){
        event.event.isCancelled=true

    }
    var dropLambda=fun(event:CXButtonEvent){
        onDrop(event)
        return
    }
    fun drop(lambda:(CXButtonEvent)->Unit){
        val function = fun(event:CXButtonEvent){
            lambda(event)
            return
        }
        dropLambda= function
    }
    open fun onControlDrop(event:CXButtonEvent){
        event.event.isCancelled=true

    }
    var controlDropLambda=fun(event:CXButtonEvent){
        onControlDrop(event)
        return
    }
    fun controlDrop(lambda:(CXButtonEvent)->Unit){
        val function = fun(event:CXButtonEvent){
            lambda(event)
            return
        }
        controlDropLambda= function
    }
    open fun onNumberKey(event:CXButtonEvent){
        event.event.isCancelled=true

    }
    var numberKeyLambda=fun(event:CXButtonEvent){
        onNumberKey(event)
        return
    }
    fun numberKey(lambda:(CXButtonEvent)->Unit){
        val function = fun(event:CXButtonEvent){
            lambda(event)
            return
        }
        numberKeyLambda= function
    }
    open fun onCreative(event:CXButtonEvent){
        event.event.isCancelled=true

    }
    var creativeLambda=fun(event:CXButtonEvent){
        onCreative(event)
        return
    }
    fun creative(lambda:(CXButtonEvent)->Unit){
        val function = fun(event:CXButtonEvent){
            lambda(event)
            return
        }
        creativeLambda= function
    }
    override fun repaint(x: Int, y: Int): Array<Array<ItemStack?>> {
        return Array(1){Array(1){texture} }
    }
}