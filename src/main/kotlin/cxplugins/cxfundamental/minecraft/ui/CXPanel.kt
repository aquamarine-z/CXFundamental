package cxplugins.cxfundamental.minecraft.ui

import cxplugins.cxfundamental.minecraft.kotlindsl.toColor
import cxplugins.cxfundamental.minecraft.server.CXInventory
import cxplugins.cxfundamental.minecraft.server.CXItemStack
import org.bukkit.Bukkit
import org.bukkit.inventory.Inventory
import java.lang.IllegalArgumentException

class CXPanel(internal var height: Int,title:String) {
    internal var elements: Array<Array<CXUIElement?>>
    var inventory: Inventory? = null
    fun button(x:Int,y:Int,lambda: CXButton.() -> Unit){
        var button=CXButton(CXItemStack(1,1,"",""))
        button.apply(lambda)
        this.set(x,y,button)
    }
    init {
        this.inventory=Bukkit.createInventory(null,height*9,title.toColor())
        this.elements =Array(9,{ arrayOfNulls<CXUIElement?>(height)})
    }
    @Throws(CXPrepositionException::class)
    fun set(location:Int, element: CXUIElement) {
        this.set(CXInventory.integerToPos(location).blockX,CXInventory.integerToPos(location).blockY,element)

    }
    @Throws(CXPrepositionException::class)
    fun set(location:Int, element: CXButton) {
        this.set(CXInventory.integerToPos(location).blockX,CXInventory.integerToPos(location).blockY,element)

    }
    @Throws(CXPrepositionException::class)
    fun set(x: Int, y: Int, element: CXUIElement) {
        if (y <0 || y >= this.height) throw IllegalArgumentException("参数Y必须在0~高度之间")
        if (x !in 0..8) throw IllegalArgumentException("参数X必须在0~8之间")
        this.elements[x][y] = element

    }
    @Throws(CXPrepositionException::class)
    fun set(x: Int, y: Int, element: CXButton) {
        if (y <0 || y >= this.height) throw IllegalArgumentException("参数Y必须在0~高度之间")
        if (x !in 0..8) throw IllegalArgumentException("参数X必须在0~8之间")
        this.elements[x][y] = element
        this.inventory!!.setItem(CXInventory.posToInteger(x,y),element.item)
    }
    @Throws(CXPrepositionException::class)
    fun remove(x: Int, y: Int) {
        if (y < 0 || y >= this.height) throw IllegalArgumentException("参数Y必须在0~高度之间")
        if (x !in 0..8) throw IllegalArgumentException("参数X必须在0~8之间")
        this.inventory!!.setItem(CXInventory.posToInteger(x, y), null)

        this.elements[x][y] = null
    }
    @Throws(CXPrepositionException::class)
    fun remove(location: Int){
        val x=CXInventory.Companion.integerToPos(location).blockX
        val y=CXInventory.Companion.integerToPos(location).blockY
        remove(x,y)
    }
    @Throws(CXPrepositionException::class)
    fun  getElement(x:Int,y:Int):CXUIElement?{
        if (y <0 || y >= this.height) throw IllegalArgumentException("参数Y必须在0~高度之间")
        if (x !in 0..8) throw IllegalArgumentException("参数X必须在0~8之间")
        return elements[x][y]
    }

}
