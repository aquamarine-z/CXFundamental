package cxplugins.cxfundamental.minecraft.ui

import cxplugins.cxfundamental.minecraft.server.CXItemStack
import org.bukkit.event.inventory.InventoryClickEvent

open class CXOptionPane(height:Int=3, var title:String, vararg buttonTypes:CXOptionPaneButtonType) : CXFrame(height){
    val OK=CXOptionPaneButtonType.OK
    val CONFIRM=CXOptionPaneButtonType.CONFIRM
    val NO=CXOptionPaneButtonType.NO
    val CLOSE=CXOptionPaneButtonType.CLOSE
    val CANCEL=CXOptionPaneButtonType.CANCEL
    init{
        title=title ?: ""
        val panel=CXPanel(height,title)
        for(buttonType in buttonTypes)
            when(buttonType) {
                OK -> {
                    panel.set(0, height - 1, object : CXButton(CXItemStack(160, 1, "&3&l是", " ", 5)) {
                        override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                            event.isCancelled = true
                            onClickOk(event, frame)
                        }
                    })
                }
                NO -> {
                    panel.set(8, height - 1, object : CXButton(CXItemStack(160, 1, "&4&l否", " ", 14)) {
                        override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                            event.isCancelled = true
                            onClickNo(event, frame)
                        }
                    })
                }
                CLOSE -> {
                    panel.set(1, height - 1, object : CXButton(CXItemStack(160, 1, "&3&l关闭", " ", 14)) {
                        override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                            event.isCancelled = true
                            onClickClose(event, frame)
                        }
                    })
                }
                CANCEL -> {
                    panel.set(7, height - 1, object : CXButton(CXItemStack(160, 1, "&4&l取消", " ", 14)) {
                        override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                            event.isCancelled = true
                            onClickCancel(event, frame)
                        }
                    })
                }
                CONFIRM-> {
                    panel.set(5, height - 1, object : CXButton(CXItemStack(160, 1, "&3&l确定", " ", 5)) {
                        override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                            event.isCancelled = true
                            onClickConfirm(event, frame)
                        }
                    })
                }

            }

        this.setPanel(panel)
    }
    open fun onClickOk(event: InventoryClickEvent, frame: CXFrame){

    }
    open fun onClickNo(event: InventoryClickEvent, frame: CXFrame){

    }
    open fun onClickClose(event: InventoryClickEvent, frame: CXFrame){

    }
    open fun onClickCancel(event: InventoryClickEvent, frame: CXFrame){

    }
    open fun onClickConfirm(event: InventoryClickEvent, frame: CXFrame){

    }
}
enum class CXOptionPaneButtonType{
    OK,CONFIRM,NO,CANCEL,CLOSE
}