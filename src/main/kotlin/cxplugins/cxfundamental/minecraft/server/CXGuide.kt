package cxplugins.cxfundamental.minecraft.server

import java.util.ArrayList

import cxplugins.cxfundamental.minecraft.*
import org.bukkit.Bukkit

import cxplugins.cxfundamental.minecraft.*
import cxplugins.cxfundamental.minecraft.command.CXScript
import cxplugins.cxfundamental.minecraft.file.CXYamlConfiguration

/**
 * �ṩ��һЩ���ڸð汾CXFundamental�Ļ�����Ϣ����
 */
object CXGuide {
    @JvmStatic
            /**
             * �˲���İ汾��
             */
    val version: String
        get() = "Version: CXFundamental 1.0.0"
    @JvmStatic
            /**
             * �˰汾�����ķ���
             */
    val newMethods: List<String>
        get() {
            val result = ArrayList<String>()
            return result
        }
    @JvmStatic
            /**
             * �˰汾�����޸ĵķ���
             */
    val changedMethods: List<String>
        get() {
            val result = ArrayList<String>()

            return result
        }
    @JvmStatic
            /**
             * �˰汾�ļ��Խ���
             */
    val newVersionDescription: String
        get() {
            val result = ("We changed CXPrepositionPlus into CXFundamental which can make us program more easily,"
                    + "you can just use CXPreposition,CXPrepositionPlus and CXFundamental at the same time in the same server/plugin."
                    + "")
            return result
        }

}
