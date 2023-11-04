package cxplugins.cxfundamental.minecraft.language

import cxplugins.cxfundamental.minecraft.file.CXYamlConfiguration

class LanguagePack : HashMap<String, String>() {
    fun loadFromYamlConfiguration(configuration: CXYamlConfiguration) {
        for (i in configuration.getKeys(true)) {
            this[i] = configuration[i].toString()
        }
    }
}