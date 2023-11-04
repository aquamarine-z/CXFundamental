package cxplugins.cxfundamental.minecraft.plugin

import cxplugins.cxfundamental.minecraft.language.LanguageManager
import cxplugins.cxfundamental.minecraft.language.LanguagePack

class PluginLanguage {

    companion object {
        val chineseLanguagePack = LanguagePack()
        fun initializeChineseLanguagePack() {
            val manager = LanguageManager(CXFundamentalMain.pluginMain)

            chineseLanguagePack["cxplugins.cxfundamental.minecraft.language.LanguageManager.RegisterManagerTwice"] =
                "�˲�������Թ������Ѿ���������!"


            chineseLanguagePack["cxplugins.cxfundamental.minecraft.swing.SwingFrame.HeightOutOfBounds"] =
                "���ڸ߶ȴ��� �߶�ֻ��Ϊ1~6"
            chineseLanguagePack["cxplugins.cxfundamental.minecraft.swing.SwingFrame.WidthError"] = "���ڵĿ��ֻ��Ϊ9��"
            chineseLanguagePack["cxplugins.cxfundamental.minecraft.swing.SwingFrame.Unrelocatable"] = "�����޷��趨λ��"
            chineseLanguagePack["cxplugins.cxfundamental.minecraft.swing.SwingPanel.PositionOutOfBounds"] =
                "λ�ò��������������ķ�Χ"
            chineseLanguagePack["cxplugins.cxfundamental.minecraft.swing.SwingPanel.WidthError"] = "�����Ŀ��ֻ��Ϊ1~9"
            chineseLanguagePack["cxplugins.cxfundamental.minecraft.swing.SwingPanel.HeightError"] =
                "�����ĸ߶�ֻ��Ϊ1~6"
            chineseLanguagePack["cxplugins.cxfundamental.minecraft.swing.SwingPanel.PositionError"] =
                "�趨��λ��ֻ��Ϊ 1<=x<=9 1<=y<=6"
            chineseLanguagePack["cxplugins.cxfundamental.minecraft.swing.SwingBaseMultipagePanel.PageOutOfBounds"] =
                "ҳ������������������������"
            chineseLanguagePack["cxplugins.cxfundamental.minecraft.swing.SwingBaseMultipagePanel.PositionError"] =
                "�趨��λ��ֻ��Ϊ 1<=x<=9 1<=y<=6"

            manager.languageMap["Chinese"] = chineseLanguagePack
            manager.selectedLanguage = "Chinese"


        }
    }
}