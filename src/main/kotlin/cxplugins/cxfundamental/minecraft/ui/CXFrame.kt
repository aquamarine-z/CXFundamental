package cxplugins.cxfundamental.minecraft.ui

import java.lang.IllegalArgumentException

open class CXFrame(internal var height: Int) {
    companion object{
        @JvmStatic
        fun create(height:Int,lambda:CXFrame.()->Unit):CXFrame{
            var frame=CXFrame(height)
            frame.apply(lambda)
            return frame
        }
    }
    var mainPanel:Any?=null
    fun panel(title:String,lambda:CXPanel.()->Unit){
        var panel=CXPanel(height,title)
        panel.apply(lambda)
        this.mainPanel=panel
    }
    fun multipagePanel(lambda:CXMultipagePanel.()->Unit){
        var multiPagePanel=CXMultipagePanel(height)
        multiPagePanel.apply(lambda)
        this.mainPanel=multiPagePanel
    }
    init{
        mainPanel=CXPanel(height,"")
    }
    fun setPanel(panel:CXPanel){
        if(panel.height>height) throw IllegalArgumentException()
        mainPanel=panel
    }
    fun setPanel(panel:CXMultipagePanel){
        if(panel.height>height) throw IllegalArgumentException()
        mainPanel=panel
    }
}
