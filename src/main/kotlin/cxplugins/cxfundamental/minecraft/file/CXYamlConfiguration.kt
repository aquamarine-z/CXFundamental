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
 * 简化配置文件访问提供的类
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
     * 加载配置文件
     *
     * @return 若先前没有设置过路径 则返回false 表示加载失败
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
     * 加载配置文件 其路径为".\\plugins\\FolderPath\\File_Name"
     *
     * @param folderPath 文件夹路径
     * @param fileName 文件名字
     * @return 若此文件为新建文件则返回false 若此文件已存在则返回true
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
            // TODO 自动生成的 catch 块
            println("文件打开异常")
            e.printStackTrace()
        } catch (e: InvalidConfigurationException) {
            println("文件打开异常")
            e.printStackTrace()
        }

        this.folderPath = folderPath
        this.fileName = fileName
        try {
            path = File(".\\plugins\\$folderPath\\$fileName").canonicalPath
        } catch (e: IOException) {
            // TODO 自动生成的 catch 块
            e.printStackTrace()
        }

        return Exist
    }

    /**
     * 设置此配置文件的默认值:若此值存在 则不做修改 若不存在 则新建此值 值为设定好的值
     * @param path 路径
     * @param a 值
     */
    fun setDefault(path: String, a: Any) {
        defaultData.put(path, a)
        return
    }

    /**
     * 删除默认值
     *
     * @param path 默认值的路径
     */
    fun removeDefault(path: String) {
        defaultData.remove(path)
        return
    }

    /**
     * 保存默认值 将默认值保存到配置文件类中(要使用save()才能保存到文件中)
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
     * 保存此配置文件到磁盘
     *
     * @return true为保存成功 false表示先前并没有使用load()或有参构造器提供文件的路径
     */
    fun save(): Boolean {

        try {
            this.save(file)
        } catch (e: IOException) {
            // TODO 自动生成的 catch 块
            println("文件保存异常")
        }

        return true
    }

    /**
     * 生成一个新的配置文件类:路径".\\plugins\\FolderPath\\File_Name"
     *
     * @param folder 文件夹路径
     * @param file 文件名字
     **/
    constructor(folder: String, file: String) {
        this.load(folder, file)
    }

    /**
     * 生成一个新的配置文件类

     **/
    constructor() {
        // TODO 自动生成的构造函数存根
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
         * 在".\\plugins"路径下新建文件夹
         *
         *
         * @param path 文件夹路径
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
         * 在".\\plugins"路径下新建文件
         *
         *
         * @param path 文件路径
         **/
        @JvmStatic
        fun createFile(path: String): Boolean {
            if (!File(".\\plugins\\$path").exists()) {
                try {
                    File(".\\plugins\\$path").createNewFile()
                } catch (e: IOException) {
                    // TODO 自动生成的 catch 块
                    e.printStackTrace()
                }

                return true
            } else
                return false
        }

        /**
         * 加载配置文件 其路径为".\\plugins\\FolderPath\\File_Name"
         *
         * @param folderPath 文件夹路径
         * @param fileName 文件名字
         * @return 此配置文件类
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
         * 在其路径为".\\plugins\\FolderPath\\File_Name"的位置查找是否有配置文件
         *
         * @param folderPath 文件夹路径
         * @param fileName 文件名字
         * @return 若有文件 则返回true 否则返回false
         * */
        @JvmStatic
        fun isExist(folderPath: String, fileName: String): Boolean {
            return File(".\\plugins\\$folderPath\\$fileName").exists()
        }

        /**
         * 注册一个类 使其能够进行序列化操作
         *
         * @param classToSerialize 需要注册的类 需实现ConfigurationSerializable接口
         * @return 总是返回注册成功的信息(true)
         * */
        @JvmStatic
        fun register(classToSerialize: Class<out ConfigurationSerializable>): Boolean {
            ConfigurationSerialization.registerClass(classToSerialize)
            return true
        }
    }
}
