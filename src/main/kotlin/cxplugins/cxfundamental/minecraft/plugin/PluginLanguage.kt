package cxplugins.cxfundamental.minecraft.plugin

import cxplugins.cxfundamental.minecraft.language.LanguageManager
import cxplugins.cxfundamental.minecraft.language.LanguagePack

class PluginLanguage {

    companion object {
        val englishLanguagePack = LanguagePack()
        val chineseLanguagePack = LanguagePack()
        fun initializeEnglishLanguagePack() {
            val manager = LanguageManager(CXFundamentalMain.pluginMain)

            englishLanguagePack["cxplugins.cxfundamental.minecraft.language.LanguageManager.RegisterManagerTwice"] =
                "The languagemanager of this plugin has been already created!"
            englishLanguagePack["cxplugins.cxfundamental.minecraft.swing.SwingFrame.HeightOutOfBounds"] =
                "The height of the window can only be in 1..6"
            englishLanguagePack["cxplugins.cxfundamental.minecraft.swing.SwingFrame.WidthError"] =
                "The width of the window can only be 9"
            englishLanguagePack["cxplugins.cxfundamental.minecraft.swing.SwingFrame.Unrelocatable"] =
                "The position of the window can't be set"
            englishLanguagePack["cxplugins.cxfundamental.minecraft.swing.SwingPanel.PositionOutOfBounds"] =
                "The position out of the bound of this container"
            englishLanguagePack["cxplugins.cxfundamental.minecraft.swing.SwingPanel.WidthError"] =
                "The width of this container can only be in 1..9"
            englishLanguagePack["cxplugins.cxfundamental.minecraft.swing.SwingPanel.HeightError"] =
                "The height of this container can only be in 1..6"
            englishLanguagePack["cxplugins.cxfundamental.minecraft.swing.SwingPanel.PositionError"] =
                "The position set can only be (x in 0..8,y in 0..5)"
            englishLanguagePack["cxplugins.cxfundamental.minecraft.swing.SwingBaseMultipagePanel.PageOutOfBounds"] =
                "The page index out of the range of this multipagepanel"
            englishLanguagePack["cxplugins.cxfundamental.minecraft.swing.SwingBaseMultipagePanel.PositionError"] =
                "The position set can only be (x in 0..8,y in 0..5)"

            manager.languageMap["English"] = englishLanguagePack
            manager.selectedLanguage = "English"


        }
        fun initializeChineseLanguagePack() {
            val manager = LanguageManager(CXFundamentalMain.pluginMain)

            chineseLanguagePack["cxplugins.cxfundamental.minecraft.language.LanguageManager.RegisterManagerTwice"] =
                "此插件的语言管理器已经创建过了!"
            chineseLanguagePack["cxplugins.cxfundamental.minecraft.swing.SwingFrame.HeightOutOfBounds"] =
                "窗口高度错误 高度只能为1~6"
            chineseLanguagePack["cxplugins.cxfundamental.minecraft.swing.SwingFrame.WidthError"] =
                "窗口的宽度只能为9格"
            chineseLanguagePack["cxplugins.cxfundamental.minecraft.swing.SwingFrame.Unrelocatable"] =
                "窗口无法设定位置"
            chineseLanguagePack["cxplugins.cxfundamental.minecraft.swing.SwingPanel.PositionOutOfBounds"] =
                "位置参数超出该容器的范围"
            chineseLanguagePack["cxplugins.cxfundamental.minecraft.swing.SwingPanel.WidthError"] =
                "容器的宽度只能为1~9"
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