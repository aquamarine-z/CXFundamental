package cxplugins.cxfundamental.minecraft.file


import cxplugins.cxfundamental.minecraft.command.CXScript
import cxplugins.cxfundamental.minecraft.server.CXCuboid
import cxplugins.cxfundamental.minecraft.server.CXInventory
import cxplugins.cxfundamental.minecraft.server.CXLocation
import org.bukkit.configuration.InvalidConfigurationException
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.ConfigurationSerialization
import java.io.File
import java.io.IOException

/**
 * �������ļ������ṩ����
 *
 * @constructor Create empty CXYamlonfiguration
 */
class CXYamlConfiguration : YamlConfiguration {

    private var path: String? = null
    private val defaultData = HashMap<String, Any>()
    private var folderPath: String? = null
    private var fileName: String? = null
    private var file: File? = null

    override fun load(file: File?) {
        this.file = file
        super.load(file)
    }

    /**
     * ���������ļ�
     *
     * @return ����ǰû�����ù�·�� �򷵻�false ��ʾ����ʧ��
     */
    fun load(): Boolean {
        if (path == null)
            return false
        else {
            this.load(folderPath, fileName)
            return true
        }
    }

    /**
     * ���������ļ� ��·��Ϊ".\\plugins\\FolderPath\\File_Name"
     *
     * @param folderPath �ļ���·��
     * @param fileName �ļ�����
     * @return �����ļ�Ϊ�½��ļ��򷵻�false �����ļ��Ѵ����򷵻�true
     */
    fun load(folderPath: String?, fileName: String?): Boolean {
        var Exist = true
        if (!loaded) {
            registerAllClasses()
            loaded = true
        }
        if (createFolder(folderPath)) Exist = false
        if (createFile(folderPath + "\\" + fileName)) Exist = false
        try {
            val file = File(".\\plugins\\$folderPath\\$fileName")
            this.load(file)
            this.file = file
        } catch (e: IOException) {
            // TODO �Զ����ɵ� catch ��
            println("�ļ����쳣")
            e.printStackTrace()
        } catch (e: InvalidConfigurationException) {
            println("�ļ����쳣")
            e.printStackTrace()
        }

        this.folderPath = folderPath
        this.fileName = fileName
        try {
            path = File(".\\plugins\\$folderPath\\$fileName").canonicalPath
        } catch (e: IOException) {
            // TODO �Զ����ɵ� catch ��
            e.printStackTrace()
        }

        return Exist
    }

    /**
     * ���ô������ļ���Ĭ��ֵ:����ֵ���� �����޸� �������� ���½���ֵ ֵΪ�趨�õ�ֵ
     * @param path ·��
     * @param a ֵ
     */
    fun setDefault(path: String, a: Any) {
        defaultData.put(path, a)
        return
    }

    /**
     * ɾ��Ĭ��ֵ
     *
     * @param path Ĭ��ֵ��·��
     */
    fun removeDefault(path: String) {
        defaultData.remove(path)
        return
    }

    /**
     * ����Ĭ��ֵ ��Ĭ��ֵ���浽�����ļ�����(Ҫʹ��save()���ܱ��浽�ļ���)
     *
     */
    fun saveDefault() {
        val Keys = ArrayList(this.getKeys(false))
        for (Path in defaultData.keys) {
            if (!Keys.contains(Path)) this.set(Path, defaultData[Path])
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
        private var loaded = false

        init {
            if (!loaded) {
                registerAllClasses()
                loaded = true
            }
        }

        @JvmStatic
        fun registerAllClasses() {
            register(CXLocation::class.java)
            register(CXInventory::class.java)
            register(CXCuboid::class.java)
            register(CXScript::class.java)
        }

        /**
         * ��".\\plugins"·�����½��ļ���
         *
         *
         * @param path �ļ���·��
         **/
        @JvmStatic
        fun createFolder(path: String?): Boolean {
            if (!File(".\\plugins\\" + path!!).exists()) {
                File(".\\plugins\\$path").mkdirs()
                return true
            } else
                return false
        }

        /**
         * ��".\\plugins"·�����½��ļ�
         *
         *
         * @param path �ļ�·��
         **/
        @JvmStatic
        fun createFile(path: String): Boolean {
            if (!File(".\\plugins\\$path").exists()) {
                try {
                    File(".\\plugins\\$path").createNewFile()
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
         * @param folderPath �ļ���·��
         * @param fileName �ļ�����
         * @return �������ļ���
         * */
        @JvmStatic
        fun open(folderPath: String, fileName: String): YamlConfiguration {
            createFolder(folderPath)
            createFile(folderPath + "\\" + fileName)
            if (!loaded) {
                registerAllClasses()
                loaded = true
            }
            return CXYamlConfiguration(folderPath, fileName)
        }

        /**
         * ����·��Ϊ".\\plugins\\FolderPath\\File_Name"��λ�ò����Ƿ��������ļ�
         *
         * @param folderPath �ļ���·��
         * @param fileName �ļ�����
         * @return �����ļ� �򷵻�true ���򷵻�false
         * */
        @JvmStatic
        fun isExist(folderPath: String, fileName: String): Boolean {
            return File(".\\plugins\\$folderPath\\$fileName").exists()
        }

        /**
         * ע��һ���� ʹ���ܹ��������л�����
         *
         * @param classToSerialize ��Ҫע����� ��ʵ��ConfigurationSerializable�ӿ�
         * @return ���Ƿ���ע��ɹ�����Ϣ(true)
         * */
        @JvmStatic
        fun register(classToSerialize: Class<out ConfigurationSerializable>): Boolean {
            ConfigurationSerialization.registerClass(classToSerialize)
            return true
        }
    }
}
