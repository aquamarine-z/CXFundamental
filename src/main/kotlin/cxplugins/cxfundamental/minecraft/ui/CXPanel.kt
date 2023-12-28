package cxplugins.cxfundamental.minecraft.ui

import cxplugins.cxfundamental.minecraft.kotlindsl.toColor
import cxplugins.cxfundamental.minecraft.server.CXInventory
import cxplugins.cxfundamental.minecraft.server.CXItemStack
import org.bukkit.Bukkit
import org.bukkit.inventory.Inventory

internal fun cutTitle(from: String): String {
    return if (from.length < 31) from
    else from.substring(0, 25) + "..."
}

/**
 * 表示一个容器 一个容器不会单独存在 他会绑定到一个窗口中
 *
 * @property height 此容器的高度
 * @constructor
 *
 * @param title 此容器的标题
 */
class CXPanel(internal var height: Int, title: String) {
    internal var elements: Array<Array<CXUIElement?>>
    var inventory: Inventory? = null

    /**
     * 通过Kotlin DSL为此容器添加按钮的方法
     *
     * @param x X坐标
     * @param y Y坐标
     * @param lambda DSL的Lambda表达式
     * @receiver
     */
    fun button(x: Int, y: Int, lambda: CXButton.() -> Unit) {
        var button = CXButton(CXItemStack(1, 1, "", ""))
        button.apply(lambda)
        try {
            button.item!!.itemMeta.displayName = button.item!!.itemMeta.displayName.toColor()
            button.item!!.itemMeta.lore = button.item!!.itemMeta.lore.toColor()
        } catch (exception: NullPointerException) {

        } finally {
            this.set(x, y, button)
        }

    }

    init {
        this.inventory = Bukkit.createInventory(null, height * 9, cutTitle(title.toColor()))
        this.elements = Array(9) { arrayOfNulls<CXUIElement?>(height) }
    }

    /**
     * 在某个位置设置组件
     *
     * @param location 位置
     * @param element 此组件
     * @receiver
     */
    @Throws(CXUIException::class)
    fun set(location: Int, element: CXUIElement) {
        this.set(CXInventory.integerToPos(location).blockX, CXInventory.integerToPos(location).blockY, element)

    }

    /**
     * 在某个位置设置按钮
     *
     * @param location 按钮
     * @param element 此组件
     * @receiver
     */
    @Throws(CXUIException::class)
    fun set(location: Int, element: CXButton) {
        this.set(CXInventory.integerToPos(location).blockX, CXInventory.integerToPos(location).blockY, element)

    }

    /**
     * 在某个位置设置组件
     *
     * @param x X坐标
     * @param y Y坐标
     * @param element 此组件
     * @receiver
     */
    @Throws(CXUIException::class)
    fun set(x: Int, y: Int, element: CXUIElement) {
        if (y < 0 || y >= this.height) throw IllegalArgumentException("参数Y必须在0~高度之间")
        if (x !in 0..8) throw IllegalArgumentException("参数X必须在0~8之间")
        this.elements[x][y] = element

    }

    /**
     * 在某个位置设置按钮
     *
     * @param x X坐标
     * @param y Y坐标
     * @param element 此按钮
     * @receiver
     */
    @Throws(CXUIException::class)
    fun set(x: Int, y: Int, element: CXButton) {
        if (y < 0 || y >= this.height) throw IllegalArgumentException("参数Y必须在0~高度之间")
        if (x !in 0..8) throw IllegalArgumentException("参数X必须在0~8之间")
        this.elements[x][y] = element
        this.inventory!!.setItem(CXInventory.posToInteger(x, y), element.item)
    }

    /**
     * 删除某个位置的组件
     *
     * @param x X坐标
     * @param y Y坐标
     * @receiver
     */
    @Throws(CXUIException::class)
    fun remove(x: Int, y: Int) {
        if (y < 0 || y >= this.height) throw IllegalArgumentException("参数Y必须在0~高度之间")
        if (x !in 0..8) throw IllegalArgumentException("参数X必须在0~8之间")
        this.inventory!!.setItem(CXInventory.posToInteger(x, y), null)

        this.elements[x][y] = null
    }

    /**
     * 删除某个位置的组件
     *
     * @param location 位置
     * @receiver
     */
    @Throws(CXUIException::class)
    fun remove(location: Int) {
        val x = CXInventory.integerToPos(location).blockX
        val y = CXInventory.integerToPos(location).blockY
        remove(x, y)
    }

    /**
     * 获取某个位置的组件
     *
     * @param x X坐标
     * @param y Y坐标
     * @receiver
     */
    @Throws(CXUIException::class)
    fun getElement(x: Int, y: Int): CXUIElement? {
        if (y < 0 || y >= this.height) throw IllegalArgumentException("参数Y必须在0~高度之间")
        if (x !in 0..8) throw IllegalArgumentException("参数X必须在0~8之间")
        return elements[x][y]
    }

}
