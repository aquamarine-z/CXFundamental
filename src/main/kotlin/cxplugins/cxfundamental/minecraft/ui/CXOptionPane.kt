package cxplugins.cxfundamental.minecraft.ui

import cxplugins.cxfundamental.minecraft.server.CXItemStack
import org.bukkit.event.inventory.InventoryClickEvent

/**
 * ������Java Swing����Ϣ��
 *
 * @property title ����Ϣ���ı���
 * @constructor
 *
 * @param height ����Ϣ���ĸ߶�
 * @param buttonTypes ����Ϣ��������һЩ��ť
 */
open class CXOptionPane(height:Int=3, var title:String, vararg buttonTypes:CXOptionPaneButtonType) : CXFrame(height){
    val OK=CXOptionPaneButtonType.OK
    val CONFIRM=CXOptionPaneButtonType.CONFIRM
    val NO=CXOptionPaneButtonType.NO
    val CLOSE=CXOptionPaneButtonType.CLOSE
    val CANCEL=CXOptionPaneButtonType.CANCEL
    init{
        title=title ?: ""
        val panel=CXPanel(height,title)
        for(buttonType in buttonTypes)
            when(buttonType) {
                OK -> {
                    panel.set(0, height - 1, object : CXButton(CXItemStack(160, 1, "&3&l��", " ", 5)) {
                        override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                            event.isCancelled = true
                            onClickOk(event, frame)
                        }
                    })
                }
                NO -> {
                    panel.set(8, height - 1, object : CXButton(CXItemStack(160, 1, "&4&l��", " ", 14)) {
                        override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                            event.isCancelled = true
                            onClickNo(event, frame)
                        }
                    })
                }
                CLOSE -> {
                    panel.set(1, height - 1, object : CXButton(CXItemStack(160, 1, "&3&l�ر�", " ", 14)) {
                        override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                            event.isCancelled = true
                            onClickClose(event, frame)
                        }
                    })
                }
                CANCEL -> {
                    panel.set(7, height - 1, object : CXButton(CXItemStack(160, 1, "&4&lȡ��", " ", 14)) {
                        override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                            event.isCancelled = true
                            onClickCancel(event, frame)
                        }
                    })
                }
                CONFIRM-> {
                    panel.set(5, height - 1, object : CXButton(CXItemStack(160, 1, "&3&lȷ��", " ", 5)) {
                        override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                            event.isCancelled = true
                            onClickConfirm(event, frame)
                        }
                    })
                }

            }

        this.setPanel(panel)
    }

    /**
     * ������OK����ʱ����������Ӧ
     *
     * @param event ��ҵ������¼�
     * @param frame �����Ĵ���
     */
    open fun onClickOk(event: InventoryClickEvent, frame: CXFrame){

    }
    /**
     * ������NO����ʱ����������Ӧ
     *
     * @param event ��ҵ������¼�
     * @param frame �����Ĵ���
     */
    open fun onClickNo(event: InventoryClickEvent, frame: CXFrame){

    }
    /**
     * ������Close����ʱ����������Ӧ
     *
     * @param event ��ҵ������¼�
     * @param frame �����Ĵ���
     */
    open fun onClickClose(event: InventoryClickEvent, frame: CXFrame){

    }
    /**
     * ������Cancel����ʱ����������Ӧ
     *
     * @param event ��ҵ������¼�
     * @param frame �����Ĵ���
     */
    open fun onClickCancel(event: InventoryClickEvent, frame: CXFrame){

    }
    /**
     * ������Confirm����ʱ����������Ӧ
     *
     * @param event ��ҵ������¼�
     * @param frame �����Ĵ���
     */
    open fun onClickConfirm(event: InventoryClickEvent, frame: CXFrame){

    }
}
enum class CXOptionPaneButtonType{
    OK,CONFIRM,NO,CANCEL,CLOSE
}