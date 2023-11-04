package cxplugins.cxfundamental.minecraft.language

import cxplugins.cxfundamental.minecraft.plugin.CXFundamentalMain
import cxplugins.cxfundamental.minecraft.server.CXPluginMain

val managerList = mutableListOf<LanguageManager>()

class LanguageManager(val plugin: CXPluginMain) {
    init {

        for (manager in managerList) {
            if (plugin.name == manager.plugin.name) {
                val languagePack = getLanguageManager(CXFundamentalMain.pluginMain)!!.getSelectedLanguage()
                throw Exception(languagePack["cxplugins.cxfundamental.minecraft.language.LanguageManager.RegisterManagerTwice"])
            }
        }
    }

    val languageMap = mutableMapOf<String, LanguagePack>()
    var selectedLanguage = "Chinese"
    fun getSelectedLanguage(): LanguagePack {
        return languageMap[selectedLanguage]!!
    }

    companion object {
        fun getLanguageManager(plugin: CXPluginMain): LanguageManager? {
            for (manager in managerList) {
                if (manager.plugin.name == plugin.name) {
                    return manager
                }
            }
            return null
        }
    }
}