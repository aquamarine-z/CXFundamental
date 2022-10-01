package cxplugins.cxfundamental.minecraft.ui


import cxplugins.cxfundamental.minecraft.kotlindsl.openFrame
import cxplugins.cxfundamental.minecraft.server.CXInventory
import cxplugins.cxfundamental.minecraft.server.CXItemStack
import org.bukkit.Material
import org.bukkit.event.inventory.InventoryClickEvent
import java.util.*

/**
 * 一个多页容器 其组成为 0..height-2为摆放容器的区域
 * height-1叫做ButtonBar 包含一个往前翻页键和一个往后翻页键 也可以添加按钮进去此区域
 * @property height 多页容器的高度
 */
class CXMultipagePanel(internal var height: Int) {
    internal var pages: MutableList<CXPanel> = ArrayList()
    val buttonBar= mutableListOf<CXButton?>(null,null,null,null,null,null,null,null,null)
    /**
     * 获取某个位置的元素

     @param page 页数
     @param x X坐标 0..8
     @param y Y坐标 0..height-1
     */
    fun getElement(page:Int,x:Int,y:Int):CXUIElement?{
        if(page !in pages.indices){
            throw IllegalArgumentException("输入的page参数非法!")
        }
        if(x !in 0..8){
            throw IllegalArgumentException("输入的x参数非法! 必须在0~8内")
        }
        if(x !in 0..(height-1)){
            throw IllegalArgumentException("输入的y参数非法! 必须在0~高度内")
        }
        return pages[page].getElement(x,y)
    }
    /**
     * 获取某一页

    @param page 页数
     */
    fun getPage(page:Int):CXPanel{
        if(page !in pages.indices){
            throw IllegalArgumentException("输入的page参数非法!")
        }
        return pages[page]
    }
    /**
     * 为此multipagepanel添加新的一页的DSL方法

     @param title 新的一页的标题
     */
    fun panel(title:String,lambda:CXPanel.()->Unit){
        var panel=CXPanel(height,title)
        panel.apply(lambda)
        this.pages.add(panel)
    }
    /**
     * 在按钮区设置一个按钮
     * @param index 0..8 表示位置
     * @param button 要设置的按钮
     */
    fun setButtonOnButtonBar(index:Int,button:CXButton?){
        buttonBar[index]=button
        updateButtonBar()
    }
    /**
     * 在按钮区通过DSL设置一个按钮
     * @param index 0..8 表示位置
     * @param buttonLambda 要设置的按钮的DSL
     */
    fun setButtonOnButtonBar(index: Int,buttonLambda:CXButton.()->Unit){
        var button=CXButton()
        button.apply (buttonLambda)
        buttonBar[index]=button
        updateButtonBar()
    }
    /**
     * 切换到下一页的按钮
     */
    var nextPageButton=CXItemStack(Material.SLIME_BALL,1,"&3&l下一页","&3&l点我前往下一页")
        set(value){
            field=value
        }
        get()=field
    /**
     * 切换到上一页的按钮
     */
    var previousPageButton=CXItemStack(Material.SLIME_BALL,1,"&3&l上一页","&3&l点我返回上一页")
        set(value){
            field=value
        }
        get()=field
    init{
        var nextPage=object : CXButton(nextPageButton) {
            override fun onLeftClick(event: InventoryClickEvent,frame:CXFrame) {
                event.isCancelled=true
                if(index<pages.size-1){
                    index++
                    event.whoClicked.openFrame(frame)
                }
                else return
            }
        }
        var previousPage=object : CXButton(previousPageButton) {
            override fun onLeftClick(event: InventoryClickEvent, frame:CXFrame) {
                event.isCancelled=true
                if(index>0){
                    index--
                    event.whoClicked.openFrame(frame)
                }
                else return
            }

        }
        buttonBar[0]=previousPage
        buttonBar[8]=nextPage
    }
    var index=0
    /**
     * 在指定位置设置一个组件
     * @param 页数
     * @param x X坐标 0..8
     * @param y Y坐标 0..height-2
     * @param button 设置的按钮
     */
    fun set(page:Int,x:Int,y:Int,button:CXButton){
        if(page<0||page>=pages.size) throw ArrayIndexOutOfBoundsException("指定页面不存在")
        if(y<0||y>=height-1)throw ArrayIndexOutOfBoundsException("指定的位置不存在")
        pages[page].set(x,y,button)
    }
    /**
     * 在指定位置设置一个组件
     * @param 页数
     * @param location 位置 0..(9*(height-2)-1)
     * @param button 设置的按钮
     */
    fun set(page:Int,location:Int,button:CXButton){
        var x=CXInventory.integerToPos(location%(height-1)).blockX
        var y=CXInventory.integerToPos(location%(height-1)).blockY
        this.set(page,x,y,button)
    }
    /**
     * 在指定位置设置一个组件
     * @param location 位置 0..(9*(height-2)-1)*(pageAmount)
     * @param button 设置的按钮
     */
    fun set(location:Int,button:CXButton){
        var page=location/(height-1)
        var x=CXInventory.integerToPos(location%(height-1)).blockX
        var y=CXInventory.integerToPos(location%(height-1)).blockY
        this.set(page,x,y,button)
    }
    /**
     * 添加一个空页进入此multipagepanel
     * @param title 此页的标题
     */
    fun addEmptyPage(title:String=""){
        this.add(CXPanel(height,title))
    }
    /**
     * 刷新按钮区域的内容
     */
    fun updateButtonBar(){
        for(i in pages.indices){
            for(j in buttonBar.indices){
                if(buttonBar[j]!=null)
                pages[i].set(j,height-1,buttonBar[j]!!)
            }
        }
    }
    private fun isButtonBarEmpty(panel: CXPanel):Boolean{
        for(i in 0..8){
            if(panel.elements[i][panel.height-1]!=null) return false
        }
        return true
    }

    /**
     * 设置一个按钮到指定位置 如果此位置不存在 则自动新建前面的页
     *
     * @param page 页数
     * @param x X坐标
     * @param y Y坐标
     * @param button 按钮
     * @param title 如果要新建页面 新建页面的标题
     */
    fun setWithCreateNewPage(page:Int,x:Int,y:Int,button:CXButton,title:String=""){
        if(page<0) throw ArrayIndexOutOfBoundsException("页面不能小于0")
        var addPage=false
        if(page>=pages.size){
            addPage=true
            while(pages.size<=page){
                var p=CXPanel(height,title)
                this.add(p)
            }
        }
        if(y<0||y>=height-1)throw ArrayIndexOutOfBoundsException("指定的位置不存在")
        //index=pages.size
        pages[page].set(x,y,button)
        updateButtonBar()
    }
    /**
     * 设置一个按钮到指定位置 如果此位置不存在 则自动新建前面的页
     *
     * @param location 位置
     * @param button 按钮
     * @param title 如果要新建页面 新建页面的标题
     */

    fun setWithCreateNewPage(location:Int,button:CXButton,title:String=""){
        var page=location/((height-1)*9)
        var x=CXInventory.integerToPos(location%((height-1)*9)).blockX
        var y=CXInventory.integerToPos(location%((height-1)*9)).blockY
        setWithCreateNewPage(page,x,y,button,title)
    }
    /**
    * 设置一个按钮到指定位置 如果此位置不存在 则自动新建前面的页
    *
    * @param page 页数
    * @param location 位置
    * @param button 按钮
    * @param title 如果要新建页面 新建页面的标题
    */
    fun setWithCreateNewPage(page:Int,location:Int,button:CXButton,title:String=""){
        var x=CXInventory.integerToPos(location).blockX
        var y=CXInventory.integerToPos(location).blockY
        setWithCreateNewPage(page,x,y,button,title)
    }
    /**
     * 添加一个panel到此multipagepanel
     *

     * @param panel 要添加的容器
     */
    fun add(panel: CXPanel) {
        if(!isButtonBarEmpty(panel)) throw CXPrepositionException("此Panel的最底下的九个格子有物品 无法作为多页容器的其中一页")
        if (panel.height > this.height)
            throw IllegalArgumentException("此Panel的高度大于多页容器的高度")
        else {
            var nextPage=object : CXButton(nextPageButton) {
                override fun onLeftClick(event: InventoryClickEvent,frame:CXFrame) {
                    event.isCancelled=true
                    if(index<pages.size-1){
                        index++
                        event.whoClicked.openFrame(frame)
                    }
                    else return
                }
            }
            var previousPage=object : CXButton(previousPageButton) {
                override fun onLeftClick(event: InventoryClickEvent, frame:CXFrame) {
                    event.isCancelled=true
                    if(index>0){
                        index--
                        event.whoClicked.openFrame(frame)
                    }
                    else return
                }

            }

            pages.add(panel)
            updateButtonBar()
        }
        //index=pages.size

    }
}
