package cxplugins.cxfundamental.minecraft.file

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.io.File
import java.util.*
import java.util.List

/**
 * Ϊ�޸���ұ�����".\\plugins\\CXPlugins\\Players"�е������ļ����в����ļ���
 *
 */
object CXPlayerDataEditor {
    @JvmStatic
            /**
             *".\\plugins\\CXPlugins\\Players"�ļ�����������������ļ����б�
             *
             *
             * */
    val playerDataList: List<CXYamlConfiguration>
        /**
         *��ȡ".\\plugins\\CXPlugins\\Players"�ļ�����������������ļ����б�
         *
         *
         * */
        get() {
            val result = ArrayList<CXYamlConfiguration>()
            val fileNames = Arrays.asList(*File(".\\plugins\\CXPlugins\\Players").list()!!)
            for (fileName in fileNames) {

                result.add(CXYamlConfiguration("\\CXPlugins\\Players", fileName))

            }
            return result as List<CXYamlConfiguration>
        }

    @JvmStatic
            /**
             * �ж��Ƿ���ĳֵ
             * @param path ֵ·��
             * @param Player �������
             * @return ���д�ֵ ����true ���򷵻�false
             *
             */

    fun hasData(player: Player, path: String): Boolean {
        var configuration = getPlayerConfiguration(player)
        return configuration.contains(path)
    }

    /**
     * �ж���Players�ļ������Ƿ���ĳ���ļ�
     * @param fileName �ļ�����
     *
     * @return ���д��ļ� ����true ���򷵻�false
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
     * ����ĳ����������ļ��е�ĳֵ
     * @param playerName �������
     *
     * @param dataPath ֵ��·��
     *
     * @param data ֵ
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
     * ��ȡĳ����������ļ��е�ĳֵ
     * @param playerName �������
     *
     * @param dataPath ֵ��·��
     * @return ֵ
     *
     */
    @JvmStatic
    fun get(playerName: String, dataPath: String): Any? {
        val config = CXYamlConfiguration()
        config.load("CXPlugins\\Players", "$playerName.yml")
        return config.get(dataPath)

    }

    /**
     * ��ȡĳ����ҵ������ļ�
     * @param playerName �������
     * @return �����ļ�
     *
     */
    @JvmStatic
    fun getPlayerConfiguration(playerName: String): CXYamlConfiguration {
        return CXYamlConfiguration("\\CXPlugins\\Players", "$playerName.yml")
    }

    /**
     * ��ȡĳ����ҵ������ļ�
     * @param player ���
     * @return �����ļ�
     *
     */
    @JvmStatic
    fun getPlayerConfiguration(player: Player): CXYamlConfiguration {
        return getPlayerConfiguration(player.name)
    }

    @JvmStatic
            /**
            δ�깤�ķ���
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
             * ����ĳ����������ļ��е�ĳֵ
             * @param player ����
             *
             * @param dataPath ֵ��·��
             *
             * @param data ֵ
             *
             */
    fun edit(player: Player, dataPath: String, data: Any?) {
        edit(player.name, dataPath, data)
    }

    /**
     * ��ȡĳ����������ļ��е�ĳֵ
     * @param player ���
     *
     * @param dataPath ֵ��·��
     * @return ֵ
     *
     */
    @JvmStatic
    fun get(player: Player, dataPath: String): Any? {
        return get(player.name, dataPath)
    }

}
