package cxplugins.cxfundamental.minecraft.ui

import cxplugins.cxfundamental.minecraft.kotlindsl.create
import cxplugins.cxfundamental.minecraft.server.CXItemStack
import org.bukkit.Sound
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

/**
 * GUI����İ�ť��
 *
 * @constructor ����һ����ť�����
 */
open class CXButton : CXUIElement {
    /**
     * �ð�ť��GUI��������ʾ�Ĳ���(��Ʒ)
     */
    var item: ItemStack? = null

    /**
     * �ð�ť���µ�ʱ�򷢳�������
     */
    var clickSound: Sound? = null

    /**
     * ����GUI���ʵ�DSL Lambda���ʽ
     */
    fun itemStack(lambda: CXItemStack.() -> Unit) {
        var itemStack = CXItemStack.create(lambda)
        this.item = itemStack
    }

    /**
     * ������²�����DSL Lambda���ʽ
     */
    private var leftClickLambda: (InventoryClickEvent, CXFrame) -> Unit =
        { inventoryClickEvent: InventoryClickEvent, cxFrame: CXFrame ->

        }

    /**
     * ����������²�����DSL Lambda���ʽ�ķ���
     */
    fun leftClick(lambda: (InventoryClickEvent, CXFrame) -> Unit) {
        leftClickLambda = lambda
    }

    /**
     * ������²�����Ӧ�ķ���
     *
     * @param event ���µ��¼�
     * @param frame ���ڵĴ���
     */
    open fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
        event.isCancelled = true
        leftClickLambda(event, frame)
    }

    /**
     * �Ҽ����²�����DSL Lambda���ʽ
     */
    private var rightClickLambda: (InventoryClickEvent, CXFrame) -> Unit =
        { inventoryClickEvent: InventoryClickEvent, cxFrame: CXFrame ->

        }

    /**
     * �����Ҽ����²�����DSL Lambda���ʽ�ķ���
     */
    fun rightClick(lambda: (InventoryClickEvent, CXFrame) -> Unit) {
        rightClickLambda = lambda
    }

    /**
     * �Ҽ����²�����Ӧ�ķ���
     *
     * @param event ���µ��¼�
     * @param frame ���ڵĴ���
     */
    //fun a(){}
    open fun onRightClick(event: InventoryClickEvent, frame: CXFrame) {
        event.isCancelled = true
        rightClickLambda(event, frame)
    }

    /**
     * �Ҽ����°��²�����DSL Lambda���ʽ
     */
    private var rightShiftClickLambda: (InventoryClickEvent, CXFrame) -> Unit =
        { inventoryClickEvent: InventoryClickEvent, cxFrame: CXFrame ->


        }

    /**
     * �����Ҽ����°��²�����DSL Lambda���ʽ�ķ���
     */
    fun rightShiftClick(lambda: (InventoryClickEvent, CXFrame) -> Unit) {
        rightShiftClickLambda = lambda
    }

    /**
     * �Ҽ����°��²�����Ӧ�ķ���
     *
     * @param event ���µ��¼�
     * @param frame ���ڵĴ���
     */
    open fun onRightShiftClick(event: InventoryClickEvent, frame: CXFrame) {
        event.isCancelled = true
        rightShiftClickLambda(event, frame)
    }

    /**
     * ������°��²�����DSL Lambda���ʽ
     */
    private var leftShiftClickLambda: (InventoryClickEvent, CXFrame) -> Unit =
        { inventoryClickEvent: InventoryClickEvent, cxFrame: CXFrame ->
        }

    /**
     * ����������°��²�����DSL Lambda���ʽ�ķ���
     */
    fun leftShiftClick(lambda: (InventoryClickEvent, CXFrame) -> Unit) {
        leftShiftClickLambda = lambda
    }

    /**
     * ������°��²�����Ӧ�ķ���
     *
     * @param event ���µ��¼�
     * @param frame ���ڵĴ���
     */
    open fun onLeftShiftClick(event: InventoryClickEvent, frame: CXFrame) {
        event.isCancelled = true
        leftShiftClickLambda(event, frame)
    }

    /**
     * ˫��������DSL Lambda���ʽ
     */
    private var doubleClickLambda: (InventoryClickEvent, CXFrame) -> Unit =
        { inventoryClickEvent: InventoryClickEvent, cxFrame: CXFrame ->
        }

    /**
     * ����˫��������DSL Lambda���ʽ�ķ���
     */
    fun doubleClick(lambda: (InventoryClickEvent, CXFrame) -> Unit) {
        doubleClickLambda = lambda
    }

    /**
     * ˫��������Ӧ�ķ���
     *
     * @param event ���µ��¼�
     * @param frame ���ڵĴ���
     */
    open fun onDoubleClick(event: InventoryClickEvent, frame: CXFrame) {
        event.isCancelled = true
        doubleClickLambda(event, frame)
    }

    /**
     * �м����²�����DSL Lambda���ʽ
     */
    private var middleClickLambda: (InventoryClickEvent, CXFrame) -> Unit =
        { inventoryClickEvent: InventoryClickEvent, cxFrame: CXFrame ->
        }

    /**
     * �����м����²�����DSL Lambda���ʽ�ķ���
     */
    fun middleClick(lambda: (InventoryClickEvent, CXFrame) -> Unit) {
        middleClickLambda = lambda
    }

    /**
     * �м����²�����Ӧ�ķ���
     *
     * @param event ���µ��¼�
     * @param frame ���ڵĴ���
     */
    open fun onMiddleClick(event: InventoryClickEvent, frame: CXFrame) {
        event.isCancelled = true
        middleClickLambda(event, frame)
    }

    constructor(arg0: ItemStack) {
        item = arg0

        // TODO �Զ����ɵĹ��캯�����
    }

    constructor() {
        item = null
    }


}
