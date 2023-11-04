package cxplugins.cxfundamental.minecraft.swing

import cxplugins.cxfundamental.minecraft.plugin.CXFundamentalMain
import cxplugins.cxfundamental.minecraft.server.CXInventory
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryAction
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryType

class SwingUIListener : Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onCloseFrame(event: InventoryCloseEvent) {
        if (SwingFrame.isOpeningFrame(event.player as Player)) {
            event.player.removeMetadata("openingSwingFrame", CXFundamentalMain.pluginMain)
        }
    }

    private fun checkAction(event: InventoryClickEvent): Boolean {

        return event.action in acceptedAction
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onClickInventory(event: InventoryClickEvent) {
        if (!SwingFrame.isOpeningFrame(event.whoClicked as Player)) {
            return
        }
        val frame = SwingFrame.getOpeningFrame(event.whoClicked as Player)!!
        val position = CXInventory.integerToVector2I(event.rawSlot)
        var baseComponent: SwingComponent?
        run {
            var positionOffset = position
            var componentInPosition = frame as SwingComponent?
            while ((componentInPosition is SwingComponent) && (componentInPosition is SwingContainer)) {
                //println("x=${positionOffset.x} y=${positionOffset.y}")
                val containerPosition = componentInPosition.position
                positionOffset = positionOffset - containerPosition
                //println("${componentInPosition.javaClass}positionOffset=${positionOffset.x} , ${positionOffset.y}")
                val next = componentInPosition.getTopComponent(positionOffset)
                componentInPosition = next
            }
            baseComponent = componentInPosition
        }
        if (baseComponent is Clickable) {
            (baseComponent as Clickable).onClick(event)
        }
        /*if(baseComponent is ItemContainable){
            (event.whoClicked as Player).setMetadata("openingSwingFrame",
                FixedMetadataValue(CXFundamentalMain.pluginMain,frame)
            )
        }*/
        if (event.clickedInventory == null) return
        if (event.clickedInventory.type == InventoryType.CHEST) {
            if (baseComponent == null) {
                //println(1)
                event.isCancelled = true
            }
        }
    }

    companion object {
        private val acceptedAction = listOf(
            InventoryAction.PICKUP_ALL,
            InventoryAction.PICKUP_ONE,
            InventoryAction.PICKUP_SOME,
            InventoryAction.PICKUP_HALF,
            InventoryAction.MOVE_TO_OTHER_INVENTORY
        )

        fun registerListener() {
            Bukkit.getServer().pluginManager.registerEvents(SwingUIListener(), CXFundamentalMain.pluginMain)
            //println("1")
        }
    }
}