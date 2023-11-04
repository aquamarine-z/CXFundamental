package cxplugins.cxfundamental.minecraft.plugin

import cxplugins.cxfundamental.minecraft.language.LanguageManager
import cxplugins.cxfundamental.minecraft.language.LanguagePack

class PluginLanguage {

    companion object {
        val chineseLanguagePack = LanguagePack()
        fun initializeChineseLanguagePack() {
            val manager = LanguageManager(CXFundamentalMain.pluginMain)

            chineseLanguagePack["cxplugins.cxfundamental.minecraft.language.LanguageManager.RegisterManagerTwice"] =
                "此插件的语言管理器已经创建过了!"


            chineseLanguagePack["cxplugins.cxfundamental.minecraft.swing.SwingFrame.HeightOutOfBounds"] =
                "窗口高度错误 高度只能为1~6"
            chineseLanguagePack["cxplugins.cxfundamental.minecraft.swing.SwingFrame.WidthError"] = "窗口的宽度只能为9格"
            chineseLanguagePack["cxplugins.cxfundamental.minecraft.swing.SwingFrame.Unrelocatable"] = "窗口无法设定位置"
            chineseLanguagePack["cxplugins.cxfundamental.minecraft.swing.SwingPanel.PositionOutOfBounds"] =
                "位置参数超出该容器的范围"
            chineseLanguagePack["cxplugins.cxfundamental.minecraft.swing.SwingPanel.WidthError"] = "容器的宽度只能为1~9"
            chineseLanguagePack["cxplugins.cxfundamental.minecraft.swing.SwingPanel.HeightError"] =
                "容器的高度只能为1~6"
            chineseLanguagePack["cxplugins.cxfundamental.minecraft.swing.SwingPanel.PositionError"] =
                "设定的位置只能为 1<=x<=9 1<=y<=6"
            chineseLanguagePack["cxplugins.cxfundamental.minecraft.swing.SwingBaseMultipagePanel.PageOutOfBounds"] =
                "页数参数超出该容器的容纳数"
            chineseLanguagePack["cxplugins.cxfundamental.minecraft.swing.SwingBaseMultipagePanel.PositionError"] =
                "设定的位置只能为 1<=x<=9 1<=y<=6"

            manager.languageMap["Chinese"] = chineseLanguagePack
            manager.selectedLanguage = "Chinese"


        }
    }
}