package cxplugins.plugins.cxpoint

import org.bukkit.event.Event
import org.bukkit.event.HandlerList

/**
 * ���������������������˻�����Ŀ��ʱ�� �������¼�
 *
 */
class PluginSetEconomyEvent : Event() {
    /**
     * ���¼��Ƿ�ȡ��
     */
    private val cancelled: Boolean = false

    /**
     * ��ȡhanderlist
     *
     * @return handerlist
     */
    override fun getHandlers(): HandlerList {
        return handlerList
    }

    /**
     * ��̬��Ա
     */
    companion object {
        /**
         * handerlist
         */
        val handlerList = HandlerList()
    }
}
