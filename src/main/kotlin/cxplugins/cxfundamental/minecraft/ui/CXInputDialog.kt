package cxplugins.cxfundamental.minecraft.ui

import cxplugins.cxfundamental.minecraft.kotlindsl.openFrame
import cxplugins.cxfundamental.minecraft.server.CXItemStack
import org.bukkit.event.inventory.InventoryClickEvent
import java.util.*

/**
 * 一个输入框 (Frame) 包含了a-z0-9 大小写切换等按钮 当玩家按下这些按钮就会在顶部的文本框里面加入按下按钮代表的文字
 *
 * @constructor
 *
 * @param title 此输入框的标题
 */
open class CXInputDialog(title: String = "InputDialog") : CXFrame(6) {
    var string = ""
    var lowerCase = true
    var index = 0

    /**
     * 刷新此窗口的内容
     *
     */
    fun validate() {
        //println(string)
        var ind = 1
        var i = index
        var panel = this.mainPanel as CXPanel
        for (j in 1..7) {
            panel.remove(j, 0)
        }
        while (i < index + 7 && i < string.length) {
            //println(index)
            var item = CXItemStack(341, i + 1, "&2&l" + string[i].toString(), "&3&l" + (i + 1).toString())
            var button = object : CXButton(item) {
                override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                    event.isCancelled = true
                }
            }
            panel.set(ind, 0, button)
            ind++
            i++


        }
        this.setPanel(panel)

    }

    /**
     * 当玩家单击enter之后 给出的响应操作
     *
     * @param event 玩家单击容器的事件
     * @param frame 玩家单击的窗口
     */
    open fun onEnter(event: InventoryClickEvent, frame: CXInputDialog) {

    }

    init {
        var panel = CXPanel(6, title)
        panel.set(1, 1, object : CXButton(CXItemStack(160, 1, "&3&l空格", " ", 3)) {
            val letter = " "
            override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {

                this@CXInputDialog.string += if (lowerCase) letter.lowercase(Locale.getDefault()) else letter
                this@CXInputDialog.validate()
                event.whoClicked.openFrame(this@CXInputDialog)

                event.isCancelled = true
            }
        })
        panel.set(0, 1, object : CXCheckBox("&3&l切换大小写") {

            override fun onSelect() {
                lowerCase = false
            }

            override fun onUnselect() {
                lowerCase = true
            }
        })
        panel.set(2, 1, object : CXButton(CXItemStack(160, 1, "&5&l删除", " ", 15)) {
            override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                if (this@CXInputDialog.string.length > 0) {
                    this@CXInputDialog.string =
                        this@CXInputDialog.string.substring(0, this@CXInputDialog.string.length - 1)
                    this@CXInputDialog.validate()
                    event.whoClicked.openFrame(this@CXInputDialog)
                }
                event.isCancelled = true
            }
        })
        panel.set(8, 1, object : CXButton(CXItemStack(160, 1, "&3&l确认", " ", 1)) {
            override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                event.isCancelled = true
                onEnter(event, frame as CXInputDialog)
            }
        })
        panel.set(0, 0, object : CXButton(CXItemStack(160, 1, "&3&l<", " ", 3)) {
            override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                if (this@CXInputDialog.index > 0) {
                    this@CXInputDialog.index--
                }
                this@CXInputDialog.validate()
                event.whoClicked.openFrame(this@CXInputDialog)
                event.isCancelled = true
            }
        })
        panel.set(8, 0, object : CXButton(CXItemStack(160, 1, "&3&l>", " ", 3)) {
            override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                if (this@CXInputDialog.index < this@CXInputDialog.string.length - 1) {
                    this@CXInputDialog.index++
                }
                this@CXInputDialog.validate()
                event.whoClicked.openFrame(this@CXInputDialog)
                event.isCancelled = true

            }
        })
        panel.set(0, 3, object : CXButton(CXItemStack(160, 1, "&3&lQ", " ", 3)) {
            val letter = "Q"
            override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {

                this@CXInputDialog.string += if (lowerCase) letter.lowercase(Locale.getDefault()) else letter
                this@CXInputDialog.validate()
                event.whoClicked.openFrame(this@CXInputDialog)

                event.isCancelled = true
            }
        })
        panel.set(1, 3, object : CXButton(CXItemStack(160, 1, "&3&lW", " ", 3)) {
            val letter = "W"
            override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                this@CXInputDialog.string += if (lowerCase) letter.lowercase(Locale.getDefault()) else letter
                this@CXInputDialog.validate()
                event.whoClicked.openFrame(this@CXInputDialog)
                event.isCancelled = true
            }
        })
        panel.set(2, 3, object : CXButton(CXItemStack(160, 1, "&3&lE", " ", 3)) {
            val letter = "E"
            override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                this@CXInputDialog.string += if (lowerCase) letter.lowercase(Locale.getDefault()) else letter
                this@CXInputDialog.validate()
                event.whoClicked.openFrame(this@CXInputDialog)
                event.isCancelled = true
            }
        })
        panel.set(3, 3, object : CXButton(CXItemStack(160, 1, "&3&lR", " ", 3)) {
            val letter = "R"
            override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                this@CXInputDialog.string += if (lowerCase) letter.lowercase(Locale.getDefault()) else letter
                this@CXInputDialog.validate()
                event.whoClicked.openFrame(this@CXInputDialog)
                event.isCancelled = true
            }
        })
        panel.set(4, 3, object : CXButton(CXItemStack(160, 1, "&3&lT", " ", 3)) {
            val letter = "T"
            override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                this@CXInputDialog.string += if (lowerCase) letter.lowercase(Locale.getDefault()) else letter
                this@CXInputDialog.validate()
                event.whoClicked.openFrame(this@CXInputDialog)
                event.isCancelled = true
            }
        })
        panel.set(5, 3, object : CXButton(CXItemStack(160, 1, "&3&lY", " ", 3)) {
            val letter = "Y"
            override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                this@CXInputDialog.string += if (lowerCase) letter.lowercase(Locale.getDefault()) else letter
                this@CXInputDialog.validate()
                event.whoClicked.openFrame(this@CXInputDialog)
                event.isCancelled = true
            }
        })
        panel.set(6, 3, object : CXButton(CXItemStack(160, 1, "&3&lU", " ", 3)) {
            val letter = "U"
            override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                this@CXInputDialog.string += if (lowerCase) letter.lowercase(Locale.getDefault()) else letter
                this@CXInputDialog.validate()
                event.whoClicked.openFrame(this@CXInputDialog)
                event.isCancelled = true
            }
        })
        panel.set(7, 3, object : CXButton(CXItemStack(160, 1, "&3&lI", " ", 3)) {
            val letter = "I"
            override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                this@CXInputDialog.string += if (lowerCase) letter.lowercase(Locale.getDefault()) else letter
                this@CXInputDialog.validate()
                event.whoClicked.openFrame(this@CXInputDialog)
                event.isCancelled = true
            }
        })
        panel.set(8, 3, object : CXButton(CXItemStack(160, 1, "&3&lO", " ", 3)) {
            val letter = "O"
            override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                this@CXInputDialog.string += if (lowerCase) letter.lowercase(Locale.getDefault()) else letter
                this@CXInputDialog.validate()
                event.whoClicked.openFrame(this@CXInputDialog)
                event.isCancelled = true
            }
        })
        panel.set(0, 4, object : CXButton(CXItemStack(160, 1, "&3&lA", " ", 3)) {
            val letter = "A"
            override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                this@CXInputDialog.string += if (lowerCase) letter.lowercase(Locale.getDefault()) else letter
                this@CXInputDialog.validate()
                event.whoClicked.openFrame(this@CXInputDialog)
                event.isCancelled = true
            }
        })
        panel.set(1, 4, object : CXButton(CXItemStack(160, 1, "&3&lS", " ", 3)) {
            val letter = "S"
            override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                this@CXInputDialog.string += if (lowerCase) letter.lowercase(Locale.getDefault()) else letter
                this@CXInputDialog.validate()
                event.whoClicked.openFrame(this@CXInputDialog)
                event.isCancelled = true
            }
        })
        panel.set(2, 4, object : CXButton(CXItemStack(160, 1, "&3&lD", " ", 3)) {
            val letter = "D"
            override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                this@CXInputDialog.string += if (lowerCase) letter.lowercase(Locale.getDefault()) else letter
                this@CXInputDialog.validate()
                event.whoClicked.openFrame(this@CXInputDialog)
                event.isCancelled = true
            }
        })
        panel.set(3, 4, object : CXButton(CXItemStack(160, 1, "&3&lF", " ", 3)) {
            val letter = "F"
            override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                this@CXInputDialog.string += if (lowerCase) letter.lowercase(Locale.getDefault()) else letter
                this@CXInputDialog.validate()
                event.whoClicked.openFrame(this@CXInputDialog)
                event.isCancelled = true
            }
        })
        panel.set(4, 4, object : CXButton(CXItemStack(160, 1, "&3&lG", " ", 3)) {
            val letter = "G"
            override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                this@CXInputDialog.string += if (lowerCase) letter.lowercase(Locale.getDefault()) else letter
                this@CXInputDialog.validate()
                event.whoClicked.openFrame(this@CXInputDialog)
                event.isCancelled = true
            }
        })
        panel.set(5, 4, object : CXButton(CXItemStack(160, 1, "&3&lH", " ", 3)) {
            val letter = "H"
            override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                this@CXInputDialog.string += if (lowerCase) letter.lowercase(Locale.getDefault()) else letter
                this@CXInputDialog.validate()
                event.whoClicked.openFrame(this@CXInputDialog)
                event.isCancelled = true
            }
        })
        panel.set(6, 4, object : CXButton(CXItemStack(160, 1, "&3&lJ", " ", 3)) {
            val letter = "J"
            override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                this@CXInputDialog.string += if (lowerCase) letter.lowercase(Locale.getDefault()) else letter
                this@CXInputDialog.validate()
                event.whoClicked.openFrame(this@CXInputDialog)
                event.isCancelled = true
            }
        })
        panel.set(7, 4, object : CXButton(CXItemStack(160, 1, "&3&lK", " ", 3)) {
            val letter = "K"
            override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                this@CXInputDialog.string += if (lowerCase) letter.lowercase(Locale.getDefault()) else letter
                this@CXInputDialog.validate()
                event.whoClicked.openFrame(this@CXInputDialog)
                event.isCancelled = true
            }
        })
        panel.set(8, 4, object : CXButton(CXItemStack(160, 1, "&3&lL", " ", 3)) {
            val letter = "L"
            override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                this@CXInputDialog.string += if (lowerCase) letter.lowercase(Locale.getDefault()) else letter
                this@CXInputDialog.validate()
                event.whoClicked.openFrame(this@CXInputDialog)
                event.isCancelled = true
            }
        })
        panel.set(0, 5, object : CXButton(CXItemStack(160, 1, "&3&lZ", " ", 3)) {
            val letter = "Z"
            override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                this@CXInputDialog.string += if (lowerCase) letter.lowercase(Locale.getDefault()) else letter
                this@CXInputDialog.validate()
                event.whoClicked.openFrame(this@CXInputDialog)
                event.isCancelled = true
            }
        })
        panel.set(1, 5, object : CXButton(CXItemStack(160, 1, "&3&lX", " ", 3)) {
            val letter = "X"
            override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                this@CXInputDialog.string += if (lowerCase) letter.lowercase(Locale.getDefault()) else letter
                this@CXInputDialog.validate()
                event.whoClicked.openFrame(this@CXInputDialog)
                event.isCancelled = true
            }
        })
        panel.set(2, 5, object : CXButton(CXItemStack(160, 1, "&3&lC", " ", 3)) {
            val letter = "C"
            override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                this@CXInputDialog.string += if (lowerCase) letter.lowercase(Locale.getDefault()) else letter
                this@CXInputDialog.validate()
                event.isCancelled = true
            }
        })
        panel.set(3, 5, object : CXButton(CXItemStack(160, 1, "&3&lV", " ", 3)) {
            val letter = "V"
            override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                this@CXInputDialog.string += if (lowerCase) letter.lowercase(Locale.getDefault()) else letter
                this@CXInputDialog.validate()
                event.whoClicked.openFrame(this@CXInputDialog)
                event.isCancelled = true
            }
        })
        panel.set(4, 5, object : CXButton(CXItemStack(160, 1, "&3&lB", " ", 3)) {
            val letter = "B"
            override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                this@CXInputDialog.string += if (lowerCase) letter.lowercase(Locale.getDefault()) else letter
                this@CXInputDialog.validate()
                event.whoClicked.openFrame(this@CXInputDialog)
                event.isCancelled = true
            }
        })
        panel.set(5, 5, object : CXButton(CXItemStack(160, 1, "&3&lN", " ", 3)) {
            val letter = "N"
            override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                this@CXInputDialog.string += if (lowerCase) letter.lowercase(Locale.getDefault()) else letter
                this@CXInputDialog.validate()
                event.whoClicked.openFrame(this@CXInputDialog)
                event.isCancelled = true
            }
        })
        panel.set(6, 5, object : CXButton(CXItemStack(160, 1, "&3&lM", " ", 3)) {
            val letter = "M"
            override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                this@CXInputDialog.string += if (lowerCase) letter.lowercase(Locale.getDefault()) else letter
                this@CXInputDialog.validate()
                event.whoClicked.openFrame(this@CXInputDialog)
                event.isCancelled = true
            }
        })
        panel.set(7, 5, object : CXButton(CXItemStack(160, 1, "&3&lP", " ", 5)) {
            val letter = "P"
            override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                this@CXInputDialog.string += if (lowerCase) letter.lowercase(Locale.getDefault()) else letter
                this@CXInputDialog.validate()
                event.whoClicked.openFrame(this@CXInputDialog)

                event.isCancelled = true
            }
        })
        panel.set(0, 2, object : CXButton(CXItemStack(160, 1, "&3&l1", " ", 5)) {
            val letter = "1"
            override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                this@CXInputDialog.string += if (lowerCase) letter.lowercase(Locale.getDefault()) else letter
                this@CXInputDialog.validate()
                event.whoClicked.openFrame(this@CXInputDialog)

                event.isCancelled = true
            }
        })
        panel.set(1, 2, object : CXButton(CXItemStack(160, 1, "&3&l2", " ", 5)) {
            val letter = "2"
            override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                this@CXInputDialog.string += if (lowerCase) letter.lowercase(Locale.getDefault()) else letter
                this@CXInputDialog.validate()
                event.whoClicked.openFrame(this@CXInputDialog)

                event.isCancelled = true
            }
        })
        panel.set(2, 2, object : CXButton(CXItemStack(160, 1, "&3&l3", " ", 5)) {
            val letter = "3"
            override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                this@CXInputDialog.string += if (lowerCase) letter.lowercase(Locale.getDefault()) else letter
                this@CXInputDialog.validate()
                event.whoClicked.openFrame(this@CXInputDialog)

                event.isCancelled = true
            }
        })
        panel.set(3, 2, object : CXButton(CXItemStack(160, 1, "&3&l4", " ", 5)) {
            val letter = "4"
            override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                this@CXInputDialog.string += if (lowerCase) letter.lowercase(Locale.getDefault()) else letter
                this@CXInputDialog.validate()
                event.whoClicked.openFrame(this@CXInputDialog)

                event.isCancelled = true
            }
        })
        panel.set(4, 2, object : CXButton(CXItemStack(160, 1, "&3&l5", " ", 5)) {
            val letter = "5"
            override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                this@CXInputDialog.string += if (lowerCase) letter.lowercase(Locale.getDefault()) else letter
                this@CXInputDialog.validate()
                event.whoClicked.openFrame(this@CXInputDialog)

                event.isCancelled = true
            }
        })
        panel.set(5, 2, object : CXButton(CXItemStack(160, 1, "&3&l6", " ", 5)) {
            val letter = "6"
            override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                this@CXInputDialog.string += if (lowerCase) letter.lowercase(Locale.getDefault()) else letter
                this@CXInputDialog.validate()
                event.whoClicked.openFrame(this@CXInputDialog)

                event.isCancelled = true
            }
        })
        panel.set(6, 2, object : CXButton(CXItemStack(160, 1, "&3&l7", " ", 5)) {
            val letter = "7"
            override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                this@CXInputDialog.string += if (lowerCase) letter.lowercase(Locale.getDefault()) else letter
                this@CXInputDialog.validate()
                event.whoClicked.openFrame(this@CXInputDialog)

                event.isCancelled = true
            }
        })
        panel.set(7, 2, object : CXButton(CXItemStack(160, 1, "&3&l8", " ", 5)) {
            val letter = "8"
            override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                this@CXInputDialog.string += if (lowerCase) letter.lowercase(Locale.getDefault()) else letter
                this@CXInputDialog.validate()
                event.whoClicked.openFrame(this@CXInputDialog)

                event.isCancelled = true
            }
        })
        panel.set(8, 2, object : CXButton(CXItemStack(160, 1, "&3&l9", " ", 5)) {
            val letter = "9"
            override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                this@CXInputDialog.string += if (lowerCase) letter.lowercase(Locale.getDefault()) else letter
                this@CXInputDialog.validate()
                event.whoClicked.openFrame(this@CXInputDialog)

                event.isCancelled = true
            }
        })
        panel.set(8, 5, object : CXButton(CXItemStack(160, 1, "&3&l0", " ", 3)) {
            val letter = "0"
            override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                this@CXInputDialog.string += if (lowerCase) letter.lowercase(Locale.getDefault()) else letter
                this@CXInputDialog.validate()
                event.whoClicked.openFrame(this@CXInputDialog)

                event.isCancelled = true
            }
        })
        this.setPanel(panel)
    }
}