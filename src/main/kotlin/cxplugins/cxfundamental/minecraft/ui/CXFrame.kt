package cxplugins.cxfundamental.minecraft.ui

/**
 * һ������ ������Inventory�Ľ��� һ������������԰���һ��Panel����һ��MultipagePanel
 *
 * @property height ���ڵĸ߶� 1..6
 */
open class CXFrame(internal var height: Int) {
    companion object {
        /**
         * ����Kotlin DSL������һ�����ڵķ���
         *
         * @param height �߶�
         * @param lambda DSL��Lambda���ʽ
         * @receiver
         * @return ���ɵĴ���
         */
        @JvmStatic
        fun create(height: Int, lambda: CXFrame.() -> Unit): CXFrame {
            var frame = CXFrame(height)
            frame.apply(lambda)
            return frame
        }
    }

    /**
     * �˴��ڵ�mainPanel ������ ����Ϊmultipagepanel��panel
     */
    var mainPanel: Any? = null

    /**
     * ����Kotlin DSL������һ�����ڵĹ�����
     *
     * @param height �߶�
     * @param lambda DSL��Lambda���ʽ
     * @receiver
     */
    constructor(height: Int, lambda: CXFrame.() -> Unit) : this(height) {
        this.apply(lambda)
    }

    /**
     * ʹ��DSL���趨�˴��ڵ�panel�ķ���
     *
     * @param title panel�ı���
     * @param lambda DSL��Lambda���ʽ
     * @receiver
     */
    fun panel(title: String, lambda: CXPanel.() -> Unit) {
        var panel = CXPanel(height, title)
        panel.apply(lambda)
        this.mainPanel = panel
    }

    /**
     * ʹ��DSL���趨�˴��ڵ�multipagepanel�ķ��� ע�� multipagepanel�л���Ƕ��panel
     * @param lambda DSL��Lambda���ʽ
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
     * ���ô˴��ڵ�������Ϊһ����ͨ����
     *
     * @param panel ������ ���԰����������
     */
    fun setPanel(panel: CXPanel) {
        if (panel.height > height) throw IllegalArgumentException()
        mainPanel = panel
    }

    /**
     * ���ô˴��ڵ�������Ϊһ����ҳ����
     *
     * @param panel ������ ���԰����������
     */
    fun setPanel(panel: CXMultipagePanel) {
        if (panel.height > height) throw IllegalArgumentException()
        mainPanel = panel
    }

    /**
     * ��ȡ�˴��ڵ�������
     *
     * @return �˴��ڵ�������
     */
    fun getPanel(): Any? {
        return mainPanel
    }
}
