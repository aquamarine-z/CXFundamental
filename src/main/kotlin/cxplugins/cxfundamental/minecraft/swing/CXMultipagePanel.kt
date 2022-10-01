package cxplugins.cxfundamental.minecraft.swing

import cxplugins.cxfundamental.minecraft.kotlindsl.openFrame
import cxplugins.cxfundamental.minecraft.server.CXInventory
import cxplugins.cxfundamental.minecraft.server.CXItemStack
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
typealias SwingMultipagePanel=CXMultipagePanel
open class CXMultipagePanel(val panelWidth:Int, val panelHeight:Int) : CXContainer {
    fun button(page:Int,x:Int,y:Int,lambda:SwingButton.()->Unit){
        val button=CXButton()
        button.apply(lambda)
        this.setComponent(page,x,y,button)
    }
    fun button(page:Int,location:Int,lambda:SwingButton.()->Unit){
        val button=CXButton()
        button.apply(lambda)
        val position= CXInventory.integerToPos(location)
        this.setComponent(page,position.blockX,position.blockY,button)
    }
    fun panel(page:Int,x:Int,y:Int,width:Int,height:Int,lambda:SwingPanel.()->Unit){
        val panel=SwingPanel(width,height)
        panel.apply(lambda)
        this.setComponent(page,x,y,panel)
    }
    fun panel(page:Int,location:Int,width:Int,height:Int,lambda:SwingPanel.()->Unit){
        val panel=SwingPanel(width,height)
        panel.apply(lambda)
        val position= CXInventory.integerToPos(location)
        this.setComponent(page,position.blockX,position.blockY,panel)
    }
    fun multipagePanel(page:Int,x: Int,y: Int,width: Int,height: Int,lambda:SwingMultipagePanel.()->Unit){
        val multipagePanel=CXMultipagePanel(width,height)
        multipagePanel.apply(lambda)
        this.setComponent(page,x,y,multipagePanel)
    }
    fun multipagePanel(page:Int,location:Int,width: Int,height: Int,lambda:SwingMultipagePanel.()->Unit){
        val multipagePanel=CXMultipagePanel(width,height)
        multipagePanel.apply(lambda)
        val position= CXInventory.integerToPos(location)
        this.setComponent(page,position.blockX,position.blockY,multipagePanel)
    }
    fun buttonBar(location: Int,lambda: SwingButton.() -> Unit){
        if(location !in 0..8) throw CXSwingException("位置必须在0~8之间")
        else{
            val button=SwingButton()
            button.apply(lambda)
            this.buttonbar.setComponent(location,0,button)
        }
    }
    val buttonbar=SwingPanel(9,1);
    val pages=ArrayList<CXPanel>()
    var pageIndex:Int=0
    get() = field
    set(value) {
        if(value>pages.size-1) field=pages.size-1
        if(value<0) field=0
        else field=value
    }

    var cycle=false
    var nextButton=object :CXButton(){
        init{
            this.texture=CXItemStack(Material.SLIME_BALL,1,"&3&l下一页","&3&l点我前往下一页")
        }
        override fun onLeftClick(event: CXButtonEvent) {
            super.onLeftClick(event)
            val multipagePanelEvent=CXMultipagePanelEvent(event.frame,event.player,pageIndex,false)
            nextPageLambda(multipagePanelEvent)
            if(multipagePanelEvent.cancel) return
            if(pageIndex>=pages.size-1) {
                if(cycle) {
                    pageIndex = 0
                    event.player.openFrame(event.frame)
                }
            }
            else{
                pageIndex++
                event.player.openFrame(event.frame)
            }

        }
    }
    var previousButton=object:CXButton(){
        init{
            this.texture=CXItemStack(Material.SLIME_BALL,1,"&3&l上一页","&3&l点我前往上一页")
        }
        override fun onLeftClick(event: CXButtonEvent) {
            super.onLeftClick(event)
            val multipagePanelEvent=CXMultipagePanelEvent(event.frame,event.player,pageIndex,false)
            previousPageLambda(multipagePanelEvent)
            if(multipagePanelEvent.cancel) return
            if(pageIndex<=0) {
                if(cycle) {
                    pageIndex = pages.size-1
                    event.player.openFrame(event.frame)
                }
            }
            else{
                pageIndex--
                event.player.openFrame(event.frame)
            }
        }

    }
    init{
        buttonbar.setComponent(0,0,previousButton)
        buttonbar.setComponent(8,0,nextButton)
        val page=CXPanel(panelWidth,panelHeight)
        page.setComponent(0,panelHeight-1,previousButton)
        page.setComponent(panelWidth-1,panelHeight-1,nextButton)
        pages.add(page)
    }
    open fun onNextPage(event:CXMultipagePanelEvent){
    }
    var nextPageLambda=fun(event:CXMultipagePanelEvent){
        onNextPage(event)
        return
    }
    fun nextPage(lambda:(CXMultipagePanelEvent)->Unit){
        val function = fun(event:CXMultipagePanelEvent){
            lambda(event)
            return
        }
        nextPageLambda= function
    }
    open fun onPreviousPage(event:CXMultipagePanelEvent){
    }
    var previousPageLambda=fun(event:CXMultipagePanelEvent){
        onPreviousPage(event)
        return
    }
    fun previousPage(lambda:(CXMultipagePanelEvent)->Unit){
        val function = fun(event:CXMultipagePanelEvent){
            lambda(event)
            return
        }
        previousPageLambda= function
    }
    open fun onLeftClick(event:CXButtonEvent){
        event.event.isCancelled=true

    }
    override fun getComponent(x: Int, y: Int): CXComponent? {

        return  if(y<panelHeight-1) pages[pageIndex].getComponent(x,y) else this.buttonbar.getComponent(x,0);
    }

    override fun getComponent(location: Int): CXComponent? {
        return null
    }

    override fun setComponent(location: Int, element: CXComponent?) {
    }

    override fun setComponent(x: Int, y: Int, element: CXComponent?) {
        val page=pages[pageIndex]
        page.setComponent(x,y,element)
        pages[pageIndex]=page
    }
    fun setComponent(pageIndex:Int,x:Int,y:Int,element: CXComponent?){
        val page=pages[pageIndex]
        page.setComponent(x,y,element)
        pages[pageIndex]=page
    }
    fun getCompoent(pageIndex: Int,x:Int,y:Int):CXComponent?{
        return pages[pageIndex].getComponent(x,y)
    }
    fun addNewPage(){
        val page=CXPanel(panelWidth,panelHeight-1)
        pages.add(page)
    }
    override fun getWidth(): Int {
        return panelWidth
    }

    override fun getHeight(): Int {
        return panelHeight
    }

    override fun repaint(x: Int, y: Int): Array<Array<ItemStack?>> {
        return if(y<panelHeight-1) pages[pageIndex].repaint(x,y) else this.buttonbar.repaint(x,0);
    }
}