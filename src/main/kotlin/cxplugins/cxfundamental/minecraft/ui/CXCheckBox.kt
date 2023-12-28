package cxplugins.cxfundamental.minecraft.ui

import cxplugins.cxfundamental.minecraft.kotlindsl.openFrame
import cxplugins.cxfundamental.minecraft.server.CXInventory
import cxplugins.cxfundamental.minecraft.server.CXItemStack
import org.bukkit.event.inventory.InventoryClickEvent

/**
 * 类似Java Swing的复选框
 */
open class CXCheckBox(text: String, lore: Array<String>? = null) : CXButton() {

    override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
        event.isCancelled = true
        onChanged()
        if (this@CXCheckBox.item == selected) {
            this@CXCheckBox.item = unselected
            if (frame.mainPanel is CXPanel) {
                var mainPanel = frame.mainPanel as CXPanel
                var x = CXInventory.integerToPos(event.rawSlot).blockX
                var y = CXInventory.integerToPos(event.rawSlot).blockY
                mainPanel.set(x, y, this@CXCheckBox)
                frame.mainPanel = mainPanel
            } else if (frame.mainPanel is CXMultipagePanel) {
                var mainPanel = frame.mainPanel as CXMultipagePanel
                var x = CXInventory.integerToPos(event.rawSlot).blockX
                var y = CXInventory.integerToPos(event.rawSlot).blockY
                mainPanel.set(mainPanel.index, x, y, this@CXCheckBox)
                frame.mainPanel = mainPanel
            }
            event.whoClicked.openFrame(frame)
            onUnselect()
        } else {
            this@CXCheckBox.item = selected
            if (frame.mainPanel is CXPanel) {
                var mainPanel = frame.mainPanel as CXPanel
                var x = CXInventory.integerToPos(event.rawSlot).blockX
                var y = CXInventory.integerToPos(event.rawSlot).blockY
                mainPanel.set(x, y, this@CXCheckBox)
                frame.mainPanel = mainPanel
            } else if (frame.mainPanel is CXMultipagePanel) {
                var mainPanel = frame.mainPanel as CXMultipagePanel
                var x = CXInventory.integerToPos(event.rawSlot).blockX
                var y = CXInventory.integerToPos(event.rawSlot).blockY
                mainPanel.set(mainPanel.index, x, y, this@CXCheckBox)
                frame.mainPanel = mainPanel
            }
            event.whoClicked.openFrame(frame)
            onSelect()
        }

    }

    private var changed: CXCheckBox.() -> Unit = {

    }

    /**
     * 设置当勾选更换时操作的Lambda表达式的方法
     *
     */
    fun onChanged(lambda: CXCheckBox.() -> Unit) {
        changed = lambda
    }

    /**
     * 当勾选更换时的响应
     *
     */
    open fun onChanged() {
        this.apply(changed)
    }

    private var select: CXCheckBox.() -> Unit = {

    }

    /**
     * 设置当选中时操作的Lambda表达式的方法
     *
     */
    fun onSelect(lambda: CXCheckBox.() -> Unit) {
        select = lambda
    }

    /**
     * 当选中时的响应
     *
     */
    open fun onSelect() {
        this.apply(select)
    }

    private var unselect: CXCheckBox.() -> Unit = {

    }

    /**
     * 设置当取消选中时操作的Lambda表达式的方法
     *
     */
    fun onUnselect(lambda: CXCheckBox.() -> Unit) {
        unselect = lambda
    }

    /**
     * 当取消选中时的响应
     *
     */
    open fun onUnselect() {
        this.apply(unselect)
    }

    var selected = if (lore != null) {
        CXItemStack(160, 1, text, lore, 5)
    } else {
        CXItemStack(160, 1, text, "", 5)
    }
    var unselected = if (lore != null) {
        CXItemStack(160, 1, text, lore, 0)
    } else {
        CXItemStack(160, 1, text, "", 0)
    }
    var isSelected = false

    init {
        this.item = unselected
    }
}