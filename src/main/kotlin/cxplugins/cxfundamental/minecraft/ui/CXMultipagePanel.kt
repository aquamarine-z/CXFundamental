package cxplugins.cxfundamental.minecraft.ui


import cxplugins.cxfundamental.minecraft.kotlindsl.openFrame
import cxplugins.cxfundamental.minecraft.server.CXInventory
import cxplugins.cxfundamental.minecraft.server.CXItemStack
import java.util.ArrayList

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import java.lang.IllegalArgumentException

class CXMultipagePanel(internal var height: Int) {
    internal var pages: MutableList<CXPanel> = ArrayList()
    val buttonBar= mutableListOf<CXButton?>(null,null,null,null,null,null,null,null,null)
    fun getElement(page:Int,x:Int,y:Int):CXUIElement?{
        if(page !in pages.indices){
            throw IllegalArgumentException("�����page�����Ƿ�!")
        }
        if(x !in 0..8){
            throw IllegalArgumentException("�����x�����Ƿ�! ������0~8��")
        }
        if(x !in 0..(height-1)){
            throw IllegalArgumentException("�����y�����Ƿ�! ������0~�߶���")
        }
        return pages[page].getElement(x,y)
    }
    fun getPage(page:Int):CXPanel{
        if(page !in pages.indices){
            throw IllegalArgumentException("�����page�����Ƿ�!")
        }
        return pages[page]
    }
    fun panel(title:String,lambda:CXPanel.()->Unit){
        var panel=CXPanel(height,title)
        panel.apply(lambda)
        this.pages.add(panel)
    }
    fun setButtonOnButtonBar(index:Int,button:CXButton?){
        buttonBar[index]=button
        updateButtonBar()
    }
    fun setButtonOnButtonBar(index: Int,buttonLambda:CXButton.()->Unit){
        var button=CXButton()
        button.apply (buttonLambda)
        buttonBar[index]=button
        updateButtonBar()
    }
    var nextPageButton=CXItemStack(Material.SLIME_BALL,1,"&3&l��һҳ","&3&l����ǰ����һҳ")
        set(value){
            field=value
        }
        get()=field
    var previousPageButton=CXItemStack(Material.SLIME_BALL,1,"&3&l��һҳ","&3&l���ҷ�����һҳ")
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
    fun set(page:Int,x:Int,y:Int,button:CXButton){
        if(page<0||page>=pages.size) throw ArrayIndexOutOfBoundsException("ָ��ҳ�治����")
        if(y<0||y>=height-1)throw ArrayIndexOutOfBoundsException("ָ����λ�ò�����")
        pages[page].set(x,y,button)
    }
    fun set(page:Int,location:Int,button:CXButton){
        var x=CXInventory.integerToPos(location%(height-1)).blockX
        var y=CXInventory.integerToPos(location%(height-1)).blockY
        this.set(page,x,y,button)
    }
    fun set(location:Int,button:CXButton){
        var page=location/(height-1)
        var x=CXInventory.integerToPos(location%(height-1)).blockX
        var y=CXInventory.integerToPos(location%(height-1)).blockY
        this.set(page,x,y,button)
    }
    fun addEmptyPage(title:String=""){
        this.add(CXPanel(height,title))
    }
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
    fun setWithCreateNewPage(page:Int,x:Int,y:Int,button:CXButton,title:String=""){
        if(page<0) throw ArrayIndexOutOfBoundsException("ҳ�治��С��0")
        var addPage=false
        if(page>=pages.size){
            addPage=true
            while(pages.size<=page){
                var p=CXPanel(height,title)
                this.add(p)
            }
        }
        if(y<0||y>=height-1)throw ArrayIndexOutOfBoundsException("ָ����λ�ò�����")
        //index=pages.size
        pages[page].set(x,y,button)
        updateButtonBar()
    }
    fun setWithCreateNewPage(location:Int,button:CXButton,title:String=""){
        var page=location/((height-1)*9)
        var x=CXInventory.integerToPos(location%((height-1)*9)).blockX
        var y=CXInventory.integerToPos(location%((height-1)*9)).blockY
        setWithCreateNewPage(page,x,y,button,title)
    }
    fun setWithCreateNewPage(page:Int,location:Int,button:CXButton,title:String=""){
        var x=CXInventory.integerToPos(location).blockX
        var y=CXInventory.integerToPos(location).blockY
        setWithCreateNewPage(page,x,y,button,title)
    }
    fun add(panel: CXPanel) {
        if(!isButtonBarEmpty(panel)) throw CXPrepositionException("��Panel������µľŸ���������Ʒ �޷���Ϊ��ҳ����������һҳ")
        if (panel.height > this.height)
            throw IllegalArgumentException("��Panel�ĸ߶ȴ��ڶ�ҳ�����ĸ߶�")
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
