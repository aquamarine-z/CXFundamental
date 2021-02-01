package cxplugins.cxfundamental.minecraft.server

import java.util.ArrayList

import cxplugins.cxfundamental.minecraft.*
import org.bukkit.Bukkit

import cxplugins.cxfundamental.minecraft.*
import cxplugins.cxfundamental.minecraft.command.CXScript
import cxplugins.cxfundamental.minecraft.file.CXYamlConfiguration

/**
 * 提供了一些关于该版本CXFundamental的基础信息的类
 */
object CXGuide {
    @JvmStatic
            /**
             * 此插件的版本号
             */
    val version: String
        get() = "Version: CXFundamental 1.0.0"
    @JvmStatic
            /**
             * 此版本新增的方法
             */
    val newMethods: List<String>
        get() {
            val result = ArrayList<String>()
            return result
        }
    @JvmStatic
            /**
             * 此版本有所修改的方法
             */
    val changedMethods: List<String>
        get() {
            val result = ArrayList<String>()

            return result
        }
    @JvmStatic
            /**
             * 此版本的简略介绍
             */
    val newVersionDescription: String
        get() {
            val result = ("We changed CXPrepositionPlus into CXFundamental which can make us program more easily,"
                    + "you can just use CXPreposition,CXPrepositionPlus and CXFundamental at the same time in the same server/plugin."
                    + "")
            return result
        }

}
