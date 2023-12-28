package cxplugins.cxfundamental.minecraft.ui

import cxplugins.cxfundamental.minecraft.kotlindsl.getOpeningFrame
import cxplugins.cxfundamental.minecraft.kotlindsl.setOpeningFrame
import cxplugins.cxfundamental.minecraft.plugin.CXFundamentalMain
import cxplugins.cxfundamental.minecraft.server.CXInventory
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryAction
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent


class CXUIActionListener : Listener {
    init {

        // TODO 自动生成的构造函数存根
    }

    companion object {
        //var mapCXButtonAction=HashMap<CXButton,CXButtonAction>()

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onCloseFrame(event: InventoryCloseEvent) {
        if (event.player.getOpeningFrame() != null) event.player.setOpeningFrame(null)
    }

    private fun checkAction(event: InventoryClickEvent): Boolean {
        val acceptedAction = listOf(
            InventoryAction.PICKUP_ALL,
            InventoryAction.PICKUP_ONE,
            InventoryAction.PICKUP_SOME,
            InventoryAction.PICKUP_HALF,
            InventoryAction.MOVE_TO_OTHER_INVENTORY
        )
        return event.action in acceptedAction
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onButtonPress(event: InventoryClickEvent) {

        if (event.whoClicked.getOpeningFrame() != null) {

            if (!checkAction(event)) {
                event.isCancelled = true
                return
            }
            var frame = event.whoClicked.getOpeningFrame() as CXFrame
            if (frame.mainPanel is CXMultipagePanel) {
                var mainPanel = frame.mainPanel as CXMultipagePanel
                var panel = mainPanel.pages[mainPanel.index]
                var clickXPos = CXInventory.integerToPos(event.rawSlot).blockX
                var clickYPos = CXInventory.integerToPos(event.rawSlot).blockY
                if (clickXPos >= 9 || clickXPos < 0) return
                if (clickYPos >= panel.height || clickYPos < 0) return
                var clickedButton = panel.elements[clickXPos][clickYPos]
                if (clickedButton is CXButton) {
                    //println("1")
                    var button = clickedButton
                    if (button.clickSound != null) {
                        (event.whoClicked as Player).playSound(event.whoClicked.location, button.clickSound, 0.0f, 0.0f)
                    }
                    when (event.click) {
                        ClickType.SHIFT_LEFT -> {
                            button.onLeftShiftClick(event, frame)
                        }

                        ClickType.LEFT -> {
                            button.onLeftClick(event, frame)
                        }

                        ClickType.RIGHT -> {
                            button.onRightClick(event, frame)
                        }

                        ClickType.SHIFT_RIGHT -> {
                            button.onRightShiftClick(event, frame)
                        }

                        ClickType.MIDDLE -> {
                            button.onMiddleClick(event, frame)
                        }

                        ClickType.DOUBLE_CLICK -> {
                            button.onDoubleClick(event, frame)
                        }

                        else -> {

                        }
                    }
                } else return
            }
            if (frame.mainPanel is CXPanel) {
                //println(1)
                var panel = frame.mainPanel as CXPanel
                var clickXPos = CXInventory.integerToPos(event.rawSlot).blockX
                var clickYPos = CXInventory.integerToPos(event.rawSlot).blockY
                //println(clickXPos)
                //println(clickYPos)
                if (clickXPos >= 9 || clickXPos < 0) return
                if (clickYPos >= panel.height || clickYPos < 0) return
                var clickedButton = panel.elements[clickXPos][clickYPos]
                if (clickedButton is CXButton) {
                    var button = clickedButton
                    if (button.clickSound != null) {
                        (event.whoClicked as Player).playSound(event.whoClicked.location, button.clickSound, 1f, 1f)
                    }
                    //println(1)
                    when (event.click) {
                        ClickType.SHIFT_LEFT -> {
                            button.onLeftShiftClick(event, frame)
                        }

                        ClickType.LEFT -> {
                            button.onLeftClick(event, frame)
                        }

                        ClickType.RIGHT -> {
                            button.onRightClick(event, frame)
                        }

                        ClickType.SHIFT_RIGHT -> {
                            button.onRightShiftClick(event, frame)
                        }

                        ClickType.MIDDLE -> {
                            button.onMiddleClick(event, frame)
                        }

                        ClickType.DOUBLE_CLICK -> {
                            button.onDoubleClick(event, frame)
                        }

                        else -> {

                        }
                    }
                } else return
            }
        }
    }

    fun register() {
        Bukkit.getServer().pluginManager.registerEvents(this, CXFundamentalMain.pluginMain)
        //println("1")
    }
}
