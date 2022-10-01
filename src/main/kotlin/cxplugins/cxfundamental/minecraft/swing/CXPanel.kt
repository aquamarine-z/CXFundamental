package cxplugins.cxfundamental.minecraft.swing

import cxplugins.cxfundamental.minecraft.server.CXInventory
import org.bukkit.inventory.ItemStack
typealias SwingPanel=CXPanel

open class CXPanel(val panelWidth:Int, val panelHeight:Int) : CXContainer {
    fun button(x:Int,y:Int,lambda:SwingButton.()->Unit){
        val button=CXButton()
        button.apply(lambda)
        this.setComponent(x,y,button)
    }
    fun button(location:Int,lambda:SwingButton.()->Unit){
        val button=CXButton()
        button.apply(lambda)
        val position=CXInventory.integerToPos(location)
        this.setComponent(position.blockX,position.blockY,button)
    }
    fun panel(x:Int,y:Int,width:Int,height:Int,lambda:SwingPanel.()->Unit){
        val panel=SwingPanel(width,height)
        panel.apply(lambda)
        this.setComponent(x,y,panel)
    }
    fun panel(location:Int,width:Int,height:Int,lambda:SwingPanel.()->Unit){
        val panel=SwingPanel(width,height)
        panel.apply(lambda)
        val position=CXInventory.integerToPos(location)
        this.setComponent(position.blockX,position.blockY,panel)
    }
    fun multipagePanel(x: Int,y: Int,width: Int,height: Int,lambda:SwingMultipagePanel.()->Unit){
        val multipagePanel=CXMultipagePanel(width,height)
        multipagePanel.apply(lambda)
        this.setComponent(x,y,multipagePanel)
    }
    fun multipagePanel(location:Int,width: Int,height: Int,lambda:SwingMultipagePanel.()->Unit){
        val multipagePanel=CXMultipagePanel(width,height)
        multipagePanel.apply(lambda)
        val position=CXInventory.integerToPos(location)
        this.setComponent(position.blockX,position.blockY,multipagePanel)
    }
    private fun getOccupyMap():Array<Array<Boolean>>{
        var result=Array(panelWidth) { Array(panelHeight) {false} }
        for(x in elementArray.indices){
            for(y in elementArray[x].indices){
                val element=elementArray[x][y]
                if(element is CXContainer){
                    for(i in x until x+element.getWidth()){
                        for(j in y until y+element.getHeight()){
                            result[i][j]=true
                        }
                    }
                }
                else if (element != null){
                    result[x][y]=true
                }
            }
        }
        /*for(i in result){
            var string=""
            for(j in i){
                string+="$j "

            }
            println(string)
        }*/
        return result
    }
    private fun checkOccupy(x: Int, y: Int, component:CXComponent):Boolean{
        val map=getOccupyMap()
        if(component is CXContainer){
            for(i in x until x+component.getWidth()){
                for(j in y until y+component.getHeight()){
                    if(map[i][j]) return true
                }
            }
            return false
        }
        else{
            return map[x][y]
        }
    }
    var elementArray:Array<Array<CXComponent?>>
    var layout=CXLayout.NullLayout
    init{
        if(panelHeight !in 1..6){
            throw IllegalArgumentException("面板的高度参数必须在1~6之间")
        }
        if(panelWidth !in 1..9){
            throw IllegalArgumentException("面板的宽度参数必须在1~9之间")
        }
        elementArray=Array(panelWidth) { Array(panelHeight) { null } }

    }
    private fun getNearByContainer(x:Int,y:Int) : CXContainer?{
        for(i in x downTo 0){
            for(j in y downTo 0){
                if(elementArray[i][j] is CXContainer) return elementArray[i][j] as CXContainer
            }
        }
        return null
    }
    private fun getNearByContainerLocation(x:Int,y:Int) : Pair<Int,Int>?{
        for(i in x downTo 0){
            for(j in y downTo 0){
                if(elementArray[i][j] is CXContainer) return Pair(i,j)
            }
        }
        return null
    }
    override fun getComponent(x: Int, y: Int):CXComponent? {
        if(elementArray[x][y] == null){
            val nearByContainer=this.getNearByContainer(x,y)
            val nearByContainerLocation=this.getNearByContainerLocation(x,y)
            if(nearByContainer==null) return null
            if(x !in nearByContainerLocation!!.first until nearByContainerLocation!!.first+nearByContainer.getWidth()){
                return null
            }
            return if(y !in nearByContainerLocation!!.second until nearByContainerLocation!!.second+nearByContainer.getHeight()){
                null
            } else{
                nearByContainer.getComponent(x-nearByContainerLocation.first,y-nearByContainerLocation.second)
            }
        }
        else{
            return elementArray[x][y]
        }
    }
    override fun getComponent(location: Int):CXComponent? {
        return null
    }
    override fun setComponent(location: Int, element: CXComponent?) {
    }
    override fun setComponent(x: Int, y: Int, element: CXComponent?) {
        if(element == null){
            elementArray[x][y]=null
            return
        }
        if(element is CXContainer){
            if(element.getWidth()>panelWidth){
                throw CXSwingException("该容器的宽度大于父容器的宽度")
            }
            if(element.getHeight()>panelWidth){
                throw CXSwingException("该容器的高度大于父容器的高度")
            }
        }
        val pair=layout.calculateLocation(x,y)
        if(checkOccupy(pair.first,pair.second,element)) {
            throw CXSwingException("该位置已经被占据")
        }

        elementArray[pair.first][pair.second]=element
    }

    override fun getWidth(): Int {
        return panelWidth
    }

    override fun getHeight(): Int {
        return panelHeight
    }

    override fun repaint(x:Int,y:Int) : Array<Array<ItemStack?>>{
        val items:Array<Array<ItemStack?>> = Array(panelWidth) {Array(panelHeight) {null} }
        for(i in elementArray.indices){
            for(j in elementArray[i].indices){
                if(elementArray[i][j] != null) {
                    val array = elementArray[i][j]?.repaint( x + i, y + j)
                    if (array != null) {
                        for (kx in array.indices) {
                            for (ky in array.indices) {
                                if(array?.get(kx)?.get(ky) !=null) {
                                    items[i + kx][j + ky] = array?.get(kx)?.get(ky)
                                }
                            }
                        }
                    }
                }
            }
        }
        return items
    }
}