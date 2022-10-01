package cxplugins.cxfundamental.minecraft.swing

import org.bukkit.inventory.ItemStack
typealias SwingComponent=CXComponent
interface CXComponent {
    fun repaint(x:Int,y:Int):Array<Array<ItemStack?>>
}