package cxplugins.cxfundamental.minecraft.ui

import cxplugins.cxfundamental.minecraft.kotlindsl.create
import cxplugins.cxfundamental.minecraft.server.CXItemStack
import org.bukkit.Sound
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

open class CXButton : CXUIElement {
    var item: ItemStack? = null
        get() = field
        set(value) {
            field = value
        }

    var clickSound: Sound? = null;
    fun itemStack(lambda: CXItemStack.() -> Unit) {
        var itemStack = CXItemStack.create(lambda)
        this.item = itemStack
    }

    private var leftClickLambda: (InventoryClickEvent, CXFrame) -> Unit = { inventoryClickEvent: InventoryClickEvent, cxFrame: CXFrame ->

    }

    fun leftClick(lambda: (InventoryClickEvent, CXFrame) -> Unit) {
        leftClickLambda = lambda
    }

    open fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
        event.isCancelled = true
        leftClickLambda(event, frame)
    }

    private var rightClickLambda: (InventoryClickEvent, CXFrame) -> Unit = { inventoryClickEvent: InventoryClickEvent, cxFrame: CXFrame ->

    }

    fun rightClick(lambda: (InventoryClickEvent, CXFrame) -> Unit) {
        rightClickLambda = lambda
    }

    //fun a(){}
    open fun onRightClick(event: InventoryClickEvent, frame: CXFrame) {
        event.isCancelled = true
        rightClickLambda(event, frame)
    }

    private var rightShiftClickLambda: (InventoryClickEvent, CXFrame) -> Unit = { inventoryClickEvent: InventoryClickEvent, cxFrame: CXFrame ->
    }

    fun rightShiftClick(lambda: (InventoryClickEvent, CXFrame) -> Unit) {
        rightShiftClickLambda = lambda
    }

    open fun onRightShiftClick(event: InventoryClickEvent, frame: CXFrame) {
        event.isCancelled = true
        rightShiftClickLambda(event, frame)
    }

    private var leftShiftClickLambda: (InventoryClickEvent, CXFrame) -> Unit = { inventoryClickEvent: InventoryClickEvent, cxFrame: CXFrame ->
    }

    fun leftShiftClick(lambda: (InventoryClickEvent, CXFrame) -> Unit) {
        leftShiftClickLambda = lambda
    }

    open fun onLeftShiftClick(event: InventoryClickEvent, frame: CXFrame) {
        event.isCancelled = true
        leftShiftClickLambda(event, frame)
    }

    private var doubleClickLambda: (InventoryClickEvent, CXFrame) -> Unit = { inventoryClickEvent: InventoryClickEvent, cxFrame: CXFrame ->
    }

    fun doubleClick(lambda: (InventoryClickEvent, CXFrame) -> Unit) {
        doubleClickLambda = lambda
    }

    open fun onDoubleClick(event: InventoryClickEvent, frame: CXFrame) {
        event.isCancelled = true
        doubleClickLambda(event, frame)
    }

    private var middleClickLambda: (InventoryClickEvent, CXFrame) -> Unit = { inventoryClickEvent: InventoryClickEvent, cxFrame: CXFrame ->
    }

    fun middleClick(lambda: (InventoryClickEvent, CXFrame) -> Unit) {
        middleClickLambda = lambda
    }

    open fun onMiddleClick(event: InventoryClickEvent, frame: CXFrame) {
        event.isCancelled = true
        middleClickLambda(event, frame)
    }

    constructor(arg0: ItemStack) {
        item = arg0

        // TODO 自动生成的构造函数存根
    }

    constructor() {
        item = null
    }


}
