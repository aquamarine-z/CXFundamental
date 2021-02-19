package cxplugins.cxfundamental.minecraft.file


import cxplugins.cxfundamental.minecraft.command.CXScript
import cxplugins.cxfundamental.minecraft.server.CXCuboid
import cxplugins.cxfundamental.minecraft.server.CXInventory
import java.io.File
import java.io.IOException
import java.util.ArrayList
import java.util.HashMap

import org.bukkit.configuration.InvalidConfigurationException
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.ConfigurationSerialization

import cxplugins.*
import cxplugins.cxfundamental.minecraft.server.CXLocation

/**
 * �������ļ������ṩ����
 *
 * @constructor Create empty CXYamlonfiguration
 */
class CXYamlConfiguration : YamlConfiguration {
    private var Path: String? = null
    private val Defaults = HashMap<String, Any>()
    private var folderPath: String? = null
    private var fileName: String? = null
    private var file:File?=null
    override fun load(file: File?) {
        this.file=file
        super.load(file)
    }
    /**
     * ���������ļ�
     *
     * @return ����ǰû�����ù�·�� �򷵻�false ��ʾ����ʧ��
     */
    fun load(): Boolean {
        if (Path == null)
            return false
        else {
            this.load(folderPath, fileName)
            return true
        }
    }

    /**
     * ���������ļ� ��·��Ϊ".\\plugins\\FolderPath\\File_Name"
     *
     * @param FolderPath �ļ���·��
     * @param File_Name �ļ�����
     * @return �����ļ�Ϊ�½��ļ��򷵻�false �����ļ��Ѵ����򷵻�true
     */
    fun load(FolderPath: String?, File_Name: String?): Boolean {
        var Exist = true
        register(CXCuboid::class.java)
        register(CXInventory::class.java)
        register(CXScript::class.java)
        if (createFolder(FolderPath)) Exist = false
        if (createFile(FolderPath + "\\" + File_Name)) Exist = false
        try {
            val file=File(".\\plugins\\$FolderPath\\$File_Name")
            this.load(file)
            this.file=file
        } catch (e: IOException) {
            // TODO �Զ����ɵ� catch ��
            println("�ļ����쳣")
            e.printStackTrace()
        } catch (e: InvalidConfigurationException) {
            println("�ļ����쳣")
            e.printStackTrace()
        }

        folderPath = FolderPath
        fileName = File_Name
        try {
            Path = File(".\\plugins\\$FolderPath\\$File_Name").canonicalPath
        } catch (e: IOException) {
            // TODO �Զ����ɵ� catch ��
            e.printStackTrace()
        }

        return Exist
    }

    /**
     * ���ô������ļ���Ĭ��ֵ:����ֵ���� �����޸� �������� ���½���ֵ ֵΪ�趨�õ�ֵ
     * @param Path ·��
     * @param a ֵ
     */
    fun setDefault(Path: String, a: Any) {
        Defaults.put(Path,a)
        return
    }

    /**
     * ɾ��Ĭ��ֵ
     *
     * @param Path Ĭ��ֵ��·��
     */
    fun removeDefault(Path: String) {
        Defaults.remove(Path)
        return
    }

    /**
     * ����Ĭ��ֵ ��Ĭ��ֵ���浽�����ļ�����(Ҫʹ��save()���ܱ��浽�ļ���)
     *
     */
    fun saveDefault() {
        val Keys = ArrayList(this.getKeys(false))
        for (Path in Defaults.keys) {
            if (!Keys.contains(Path)) this.set(Path, Defaults[Path])
        }
        return
    }

    /**
     * ����������ļ�������
     *
     * @return trueΪ����ɹ� false��ʾ��ǰ��û��ʹ��load()���вι������ṩ�ļ���·��
     */
    fun save(): Boolean {

        try {
            this.save(file)
        } catch (e: IOException) {
            // TODO �Զ����ɵ� catch ��
            println("�ļ������쳣")
        }

        return true
    }
    /**
    * ����һ���µ������ļ���:·��".\\plugins\\FolderPath\\File_Name"
    *
    * @param folder �ļ���·��
    * @param file �ļ�����
     **/
    constructor(folder: String, file: String) {
        this.load(folder, file)
    }
    /**
     * ����һ���µ������ļ���

     **/
    constructor() {
        // TODO �Զ����ɵĹ��캯�����
        return
    }

    companion object {
        init{
            registerAllClasses()
        }
        @JvmStatic
        fun registerAllClasses(){
            CXYamlConfiguration.register(CXLocation::class.java)
            CXYamlConfiguration.register(CXInventory::class.java)
            CXYamlConfiguration.register(CXCuboid::class.java)
            CXYamlConfiguration.register(CXScript::class.java)
        }
        /**
         * ��".\\plugins"·�����½��ļ���
         *
         *
         * @param CPath �ļ���·��
         **/
        @JvmStatic
        fun createFolder(CPath: String?): Boolean {
            if (!File(".\\plugins\\" + CPath!!).exists()) {
                File(".\\plugins\\$CPath").mkdirs()
                return true
            } else
                return false
        }
        /**
         * ��".\\plugins"·�����½��ļ�
         *
         *
         * @param CPath �ļ�·��
         **/
        @JvmStatic
        fun createFile(CPath: String): Boolean {
            if (!File(".\\plugins\\$CPath").exists()) {
                try {
                    File(".\\plugins\\$CPath").createNewFile()
                } catch (e: IOException) {
                    // TODO �Զ����ɵ� catch ��
                    e.printStackTrace()
                }

                return true
            } else
                return false
        }
        /**
        * ���������ļ� ��·��Ϊ".\\plugins\\FolderPath\\File_Name"
        *
        * @param FolderPath �ļ���·��
        * @param File_Name �ļ�����
        * @return �������ļ���
         * */
        @JvmStatic
        fun open(FolderPath: String, File_Name: String): YamlConfiguration {
            createFolder(FolderPath)
            createFile(FolderPath + "\\" + File_Name)
            register(CXCuboid::class.java)
            register(CXInventory::class.java)
            register(CXScript::class.java)
            return CXYamlConfiguration(FolderPath, File_Name)
        }
        /**
         * ����·��Ϊ".\\plugins\\FolderPath\\File_Name"��λ�ò����Ƿ��������ļ�
         *
         * @param FolderPath �ļ���·��
         * @param File_Name �ļ�����
         * @return �����ļ� �򷵻�true ���򷵻�false
         * */
        @JvmStatic
        fun isExist(FolderPath: String, File_Name: String): Boolean {
            return File(".\\plugins\\$FolderPath\\$File_Name").exists()
        }
        /**
         * ע��һ���� ʹ���ܹ��������л�����
         *
         * @param Class ��Ҫע����� ��ʵ��ConfigurationSerializable�ӿ�
         * @return ���Ƿ���ע��ɹ�����Ϣ(true)
         * */
        @JvmStatic
        fun register(Class: Class<out ConfigurationSerializable>): Boolean {
            ConfigurationSerialization.registerClass(Class)
            return true
        }
    }
}
