package cxplugins.cxfundamental.minecraft.ui

import cxplugins.cxfundamental.minecraft.kotlindsl.create
import cxplugins.cxfundamental.minecraft.server.CXItemStack
import org.bukkit.Sound
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

/**
 * GUI界面的按钮类
 *
 * @constructor 生成一个按钮类对象
 */
open class CXButton : CXUIElement {
    /**
     * 该按钮在GUI界面上显示的材质(物品)
     */
    var item: ItemStack? = null

    /**
     * 该按钮按下的时候发出的声音
     */
    var clickSound: Sound? = null

    /**
     * 设置GUI材质的DSL Lambda表达式
     */
    fun itemStack(lambda: CXItemStack.() -> Unit) {
        var itemStack = CXItemStack.create(lambda)
        this.item = itemStack
    }

    /**
     * 左键按下操作的DSL Lambda表达式
     */
    private var leftClickLambda: (InventoryClickEvent, CXFrame) -> Unit =
        { inventoryClickEvent: InventoryClickEvent, cxFrame: CXFrame ->

        }

    /**
     * 设置左键按下操作的DSL Lambda表达式的方法
     */
    fun leftClick(lambda: (InventoryClickEvent, CXFrame) -> Unit) {
        leftClickLambda = lambda
    }

    /**
     * 左键按下操作响应的方法
     *
     * @param event 按下的事件
     * @param frame 属于的窗口
     */
    open fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
        event.isCancelled = true
        leftClickLambda(event, frame)
    }

    /**
     * 右键按下操作的DSL Lambda表达式
     */
    private var rightClickLambda: (InventoryClickEvent, CXFrame) -> Unit =
        { inventoryClickEvent: InventoryClickEvent, cxFrame: CXFrame ->

        }

    /**
     * 设置右键按下操作的DSL Lambda表达式的方法
     */
    fun rightClick(lambda: (InventoryClickEvent, CXFrame) -> Unit) {
        rightClickLambda = lambda
    }

    /**
     * 右键按下操作响应的方法
     *
     * @param event 按下的事件
     * @param frame 属于的窗口
     */
    //fun a(){}
    open fun onRightClick(event: InventoryClickEvent, frame: CXFrame) {
        event.isCancelled = true
        rightClickLambda(event, frame)
    }

    /**
     * 右键蹲下按下操作的DSL Lambda表达式
     */
    private var rightShiftClickLambda: (InventoryClickEvent, CXFrame) -> Unit =
        { inventoryClickEvent: InventoryClickEvent, cxFrame: CXFrame ->


        }

    /**
     * 设置右键蹲下按下操作的DSL Lambda表达式的方法
     */
    fun rightShiftClick(lambda: (InventoryClickEvent, CXFrame) -> Unit) {
        rightShiftClickLambda = lambda
    }

    /**
     * 右键蹲下按下操作响应的方法
     *
     * @param event 按下的事件
     * @param frame 属于的窗口
     */
    open fun onRightShiftClick(event: InventoryClickEvent, frame: CXFrame) {
        event.isCancelled = true
        rightShiftClickLambda(event, frame)
    }

    /**
     * 左键蹲下按下操作的DSL Lambda表达式
     */
    private var leftShiftClickLambda: (InventoryClickEvent, CXFrame) -> Unit =
        { inventoryClickEvent: InventoryClickEvent, cxFrame: CXFrame ->
        }

    /**
     * 设置左键蹲下按下操作的DSL Lambda表达式的方法
     */
    fun leftShiftClick(lambda: (InventoryClickEvent, CXFrame) -> Unit) {
        leftShiftClickLambda = lambda
    }

    /**
     * 左键蹲下按下操作响应的方法
     *
     * @param event 按下的事件
     * @param frame 属于的窗口
     */
    open fun onLeftShiftClick(event: InventoryClickEvent, frame: CXFrame) {
        event.isCancelled = true
        leftShiftClickLambda(event, frame)
    }

    /**
     * 双击操作的DSL Lambda表达式
     */
    private var doubleClickLambda: (InventoryClickEvent, CXFrame) -> Unit =
        { inventoryClickEvent: InventoryClickEvent, cxFrame: CXFrame ->
        }

    /**
     * 设置双击操作的DSL Lambda表达式的方法
     */
    fun doubleClick(lambda: (InventoryClickEvent, CXFrame) -> Unit) {
        doubleClickLambda = lambda
    }

    /**
     * 双击操作响应的方法
     *
     * @param event 按下的事件
     * @param frame 属于的窗口
     */
    open fun onDoubleClick(event: InventoryClickEvent, frame: CXFrame) {
        event.isCancelled = true
        doubleClickLambda(event, frame)
    }

    /**
     * 中键按下操作的DSL Lambda表达式
     */
    private var middleClickLambda: (InventoryClickEvent, CXFrame) -> Unit =
        { inventoryClickEvent: InventoryClickEvent, cxFrame: CXFrame ->
        }

    /**
     * 设置中键按下操作的DSL Lambda表达式的方法
     */
    fun middleClick(lambda: (InventoryClickEvent, CXFrame) -> Unit) {
        middleClickLambda = lambda
    }

    /**
     * 中键按下操作响应的方法
     *
     * @param event 按下的事件
     * @param frame 属于的窗口
     */
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
