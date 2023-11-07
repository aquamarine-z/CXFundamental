package cxplugins.cxfundamental.minecraft.swing

import cxplugins.cxfundamental.minecraft.math.geometry.Vector2I
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryAction
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

open class SwingItemContainer(override val position: Vector2I, override var itemInside: ItemStack? = null) :
    ItemContainable, SwingComponent {


    override fun onItemChange(event: InventoryClickEvent) {
        if (event.click != ClickType.LEFT) {
            event.isCancelled = true
            return
        }
        if (event.action != InventoryAction.PICKUP_ALL && event.action != InventoryAction.PLACE_ALL) {
            event.isCancelled = true
            return
        }
        itemInside = event.cursor.clone()
        if (itemChangeLambda != null) itemChangeLambda!!(event)

    }

    override fun onClick(event: InventoryClickEvent) {
        if (event.click != ClickType.LEFT) {
            event.isCancelled = true
            return
        }
        if (event.cursor != itemInside) {
            onItemChange(event)
        }
        //itemInside=event.cursor
    }

    override fun repaint(): Array<Array<ItemStack?>> {
        /*if(itemInside==null){
            println("NULL")
        }
        else{
            println(itemInside!!.type.toString())
        }*/
        return arrayOf(arrayOf(itemInside))
    }

    override fun relocate(x: Int, y: Int) {
        this.position.x = x
        this.position.y = y
    }

    override fun relocate(point: Vector2I) {
        this.position.x = point.x
        this.position.y = point.y
    }


    override var height: Int = 1
    override var width: Int = 1
    var itemChangeLambda: ((event: InventoryClickEvent) -> Unit)? = null
}