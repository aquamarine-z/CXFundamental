package cxplugins.cxfundamental.minecraft.file

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.io.File
import java.util.*

/**
 * 为修改玩家保存在".\\plugins\\CXPlugins\\Players"中的配置文件进行操作的简化类
 *
 */
object CXPlayerDataEditor {
    @JvmStatic
            /**
             *".\\plugins\\CXPlugins\\Players"文件夹中所有玩家配置文件的列表
             *
             *
             * */
    val playerDataList: List<CXYamlConfiguration>
        /**
         *获取".\\plugins\\CXPlugins\\Players"文件夹中所有玩家配置文件的列表
         *
         *
         * */
        get() {
            val result = ArrayList<CXYamlConfiguration>()
            val fileNames = Arrays.asList(*File(".\\plugins\\CXPlugins\\Players").list()!!)
            for (fileName in fileNames) {

                result.add(CXYamlConfiguration("\\CXPlugins\\Players", fileName))

            }
            return result
        }

    @JvmStatic
            /**
             * 判断是否有某值
             * @param path 值路径
             * @param Player 玩家名字
             * @return 若有此值 返回true 否则返回false
             *
             */

    fun hasData(player: Player, path: String): Boolean {
        var configuration = getPlayerConfiguration(player)
        return configuration.contains(path)
    }

    /**
     * 判断在Players文件夹中是否有某个文件
     * @param fileName 文件名字
     *
     * @return 若有此文件 返回true 否则返回false
     *
     */
    @JvmStatic
    fun hasPlayerData(fileName: String): Boolean {
        val Folder = File(".\\plugins\\CXPlugins\\Players")
        val file = Arrays.asList(*Folder.listFiles()!!)
        val name = ArrayList<String>()
        for (f in file) {
            name.add(f.name)
        }
        return name.contains(fileName)
    }

    /**
     * 操作某个玩家配置文件中的某值
     * @param playerName 玩家名字
     *
     * @param dataPath 值的路径
     *
     * @param data 值
     *
     */
    @JvmStatic
    fun edit(playerName: String, dataPath: String, data: Any?) {
        val config = CXYamlConfiguration()
        config.load("CXPlugins\\Players", "$playerName.yml")
        config.set(dataPath, data)
        config.save()
    }

    /**
     * 获取某个玩家配置文件中的某值
     * @param playerName 玩家名字
     *
     * @param dataPath 值的路径
     * @return 值
     *
     */
    @JvmStatic
    fun get(playerName: String, dataPath: String): Any? {
        val config = CXYamlConfiguration()
        config.load("CXPlugins\\Players", "$playerName.yml")
        return config.get(dataPath)

    }

    /**
     * 获取某个玩家的配置文件
     * @param playerName 玩家名字
     * @return 配置文件
     *
     */
    @JvmStatic
    fun getPlayerConfiguration(playerName: String): CXYamlConfiguration {
        return CXYamlConfiguration("\\CXPlugins\\Players", "$playerName.yml")
    }

    /**
     * 获取某个玩家的配置文件
     * @param player 玩家
     * @return 配置文件
     *
     */
    @JvmStatic
    fun getPlayerConfiguration(player: Player): CXYamlConfiguration {
        return getPlayerConfiguration(player.name)
    }

    @JvmStatic
            /**
            未完工的方法
             *
             */
    fun editAll(selector: PlayerSelector, action: PlayerEditAction) {
        val fileNames = Arrays.asList(*File(".\\plugins\\CXPlugins\\Players").list()!!)
        for (fileName in fileNames) {
            val player = Bukkit.getPlayer(fileName.replace(".yml", ""))
            if (selector.isSelected(player)) {
                val configuration = CXYamlConfiguration("\\CXPlugins\\Players", fileName)
                action.action(configuration)

            }
        }
    }

    @JvmStatic
            /**
             * 操作某个玩家配置文件中的某值
             * @param player 名字
             *
             * @param dataPath 值的路径
             *
             * @param data 值
             *
             */
    fun edit(player: Player, dataPath: String, data: Any?) {
        edit(player.name, dataPath, data)
    }

    /**
     * 获取某个玩家配置文件中的某值
     * @param player 玩家
     *
     * @param dataPath 值的路径
     * @return 值
     *
     */
    @JvmStatic
    fun get(player: Player, dataPath: String): Any? {
        return get(player.name, dataPath)
    }

}
