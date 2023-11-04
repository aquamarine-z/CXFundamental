package cxplugins.cxfundamental.minecraft.ui

/**
 * 一个窗口 类似于Inventory的界面 一个窗口里面可以包含一个Panel或者一个MultipagePanel
 *
 * @property height 窗口的高度 1..6
 */
open class CXFrame(internal var height: Int) {
    companion object {
        /**
         * 采用Kotlin DSL来生成一个窗口的方法
         *
         * @param height 高度
         * @param lambda DSL的Lambda表达式
         * @receiver
         * @return 生成的窗口
         */
        @JvmStatic
        fun create(height: Int, lambda: CXFrame.() -> Unit): CXFrame {
            var frame = CXFrame(height)
            frame.apply(lambda)
            return frame
        }
    }

    /**
     * 此窗口的mainPanel 主容器 可以为multipagepanel或panel
     */
    var mainPanel: Any? = null

    /**
     * 采用Kotlin DSL来生成一个窗口的构造器
     *
     * @param height 高度
     * @param lambda DSL的Lambda表达式
     * @receiver
     */
    constructor(height: Int, lambda: CXFrame.() -> Unit) : this(height) {
        this.apply(lambda)
    }

    /**
     * 使用DSL来设定此窗口的panel的方法
     *
     * @param title panel的标题
     * @param lambda DSL的Lambda表达式
     * @receiver
     */
    fun panel(title: String, lambda: CXPanel.() -> Unit) {
        var panel = CXPanel(height, title)
        panel.apply(lambda)
        this.mainPanel = panel
    }

    /**
     * 使用DSL来设定此窗口的multipagepanel的方法 注意 multipagepanel中还需嵌套panel
     * @param lambda DSL的Lambda表达式
     * @receiver
     */
    fun multipagePanel(lambda: CXMultipagePanel.() -> Unit) {
        var multiPagePanel = CXMultipagePanel(height)
        multiPagePanel.apply(lambda)
        this.mainPanel = multiPagePanel
    }

    init {
        mainPanel = CXPanel(height, "")
    }

    /**
     * 设置此窗口的主容器为一个普通容器
     *
     * @param panel 主容器 可以包含各种组件
     */
    fun setPanel(panel: CXPanel) {
        if (panel.height > height) throw IllegalArgumentException()
        mainPanel = panel
    }

    /**
     * 设置此窗口的主容器为一个多页容器
     *
     * @param panel 主容器 可以包含各种组件
     */
    fun setPanel(panel: CXMultipagePanel) {
        if (panel.height > height) throw IllegalArgumentException()
        mainPanel = panel
    }

    /**
     * 获取此窗口的主容器
     *
     * @return 此窗口的主容器
     */
    fun getPanel(): Any? {
        return mainPanel
    }
}
