package cxplugins.cxfundamental.minecraft.ui

import cxplugins.cxfundamental.minecraft.kotlindsl.toColor
import cxplugins.cxfundamental.minecraft.server.CXInventory
import cxplugins.cxfundamental.minecraft.server.CXItemStack
import org.bukkit.Bukkit
import org.bukkit.inventory.Inventory
import java.lang.IllegalArgumentException

/**
 * ��ʾһ������ һ���������ᵥ������ ����󶨵�һ��������
 *
 * @property height �������ĸ߶�
 * @constructor
 *
 * @param title �������ı���
 */
class CXPanel(internal var height: Int,title:String) {
    internal var elements: Array<Array<CXUIElement?>>
    var inventory: Inventory? = null

    /**
     * ͨ��Kotlin DSLΪ���������Ӱ�ť�ķ���
     *
     * @param x X����
     * @param y Y����
     * @param lambda DSL��Lambda����ʽ
     * @receiver
     */
    fun button(x:Int,y:Int,lambda: CXButton.() -> Unit){
        var button=CXButton(CXItemStack(1,1,"",""))
        button.apply(lambda)
        this.set(x,y,button)
    }
    init {
        this.inventory=Bukkit.createInventory(null,height*9,title.toColor())
        this.elements =Array(9,{ arrayOfNulls<CXUIElement?>(height)})
    }
    /**
     * ��ĳ��λ���������
     *
     * @param location λ��
     * @param element �����
     * @receiver
     */
    @Throws(CXPrepositionException::class)
    fun set(location:Int, element: CXUIElement) {
        this.set(CXInventory.integerToPos(location).blockX,CXInventory.integerToPos(location).blockY,element)

    }
    /**
     * ��ĳ��λ�����ð�ť
     *
     * @param location ��ť
     * @param element �����
     * @receiver
     */
    @Throws(CXPrepositionException::class)
    fun set(location:Int, element: CXButton) {
        this.set(CXInventory.integerToPos(location).blockX,CXInventory.integerToPos(location).blockY,element)

    }
    /**
     * ��ĳ��λ���������
     *
     * @param x X����
     * @param y Y����
     * @param element �����
     * @receiver
     */
    @Throws(CXPrepositionException::class)
    fun set(x: Int, y: Int, element: CXUIElement) {
        if (y <0 || y >= this.height) throw IllegalArgumentException("����Y������0~�߶�֮��")
        if (x !in 0..8) throw IllegalArgumentException("����X������0~8֮��")
        this.elements[x][y] = element

    }
    /**
     * ��ĳ��λ�����ð�ť
     *
     * @param x X����
     * @param y Y����
     * @param element �˰�ť
     * @receiver
     */
    @Throws(CXPrepositionException::class)
    fun set(x: Int, y: Int, element: CXButton) {
        if (y <0 || y >= this.height) throw IllegalArgumentException("����Y������0~�߶�֮��")
        if (x !in 0..8) throw IllegalArgumentException("����X������0~8֮��")
        this.elements[x][y] = element
        this.inventory!!.setItem(CXInventory.posToInteger(x,y),element.item)
    }
    /**
     * ɾ��ĳ��λ�õ����
     *
     * @param x X����
     * @param y Y����
     * @receiver
     */
    @Throws(CXPrepositionException::class)
    fun remove(x: Int, y: Int) {
        if (y < 0 || y >= this.height) throw IllegalArgumentException("����Y������0~�߶�֮��")
        if (x !in 0..8) throw IllegalArgumentException("����X������0~8֮��")
        this.inventory!!.setItem(CXInventory.posToInteger(x, y), null)

        this.elements[x][y] = null
    }
    /**
     * ɾ��ĳ��λ�õ����
     *
     * @param location λ��
     * @receiver
     */
    @Throws(CXPrepositionException::class)
    fun remove(location: Int){
        val x=CXInventory.Companion.integerToPos(location).blockX
        val y=CXInventory.Companion.integerToPos(location).blockY
        remove(x,y)
    }
    /**
     * ��ȡĳ��λ�õ����
     *
     * @param x X����
     * @param y Y����
     * @receiver
     */
    @Throws(CXPrepositionException::class)
    fun  getElement(x:Int,y:Int):CXUIElement?{
        if (y <0 || y >= this.height) throw IllegalArgumentException("����Y������0~�߶�֮��")
        if (x !in 0..8) throw IllegalArgumentException("����X������0~8֮��")
        return elements[x][y]
    }

}