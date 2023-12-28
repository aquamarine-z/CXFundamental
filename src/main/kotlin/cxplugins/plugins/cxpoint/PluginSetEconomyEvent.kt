package cxplugins.plugins.cxpoint

import org.bukkit.event.Event
import org.bukkit.event.HandlerList

/**
 * 当其他插件更改玩家银行账户的数目的时候 触发此事件
 *
 */
class PluginSetEconomyEvent : Event() {
    /**
     * 此事件是否被取消
     */
    private val cancelled: Boolean = false

    /**
     * 获取handerlist
     *
     * @return handerlist
     */
    override fun getHandlers(): HandlerList {
        return handlerList
    }

    /**
     * 静态成员
     */
    companion object {
        /**
         * handerlist
         */
        val handlerList = HandlerList()
    }
}
