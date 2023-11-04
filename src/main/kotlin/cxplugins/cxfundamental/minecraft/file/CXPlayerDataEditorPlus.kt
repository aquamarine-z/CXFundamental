package cxplugins.cxfundamental.minecraft.file

import org.bukkit.entity.Player
import java.io.File

/**
 * 此类是CXPlayerDataEditor的优化版本
 * 将不同插件的数据分区处理
 * 可以减少报错发生
 * 优化读取速度
 */
class CXPlayerDataEditorPlus(
    private val nameSpace: String,
    private val defaultFileKeepOperation: FileKeepOperation = FileKeepOperation.KEEP_IN_MEMORY,
    val defaultFileSaveOperation: FileSaveOperation = FileSaveOperation.SAVE_RIGHT_NOW
) {
    companion object {
        private val registeredEditors = mutableMapOf<String, CXPlayerDataEditorPlus>()
        fun register(nameSpace: String): CXPlayerDataEditorPlus {
            return if (!registeredEditors.keys.contains(nameSpace)) {
                val newEditor = CXPlayerDataEditorPlus(nameSpace)
                registeredEditors[nameSpace] = newEditor
                newEditor
            } else {
                registeredEditors[nameSpace]!!
            }
        }
    }

    val keepFileMap = mutableMapOf<String, CXYamlConfiguration>()
    private fun getFilePathByPlayer(player: Player): String {
        return ("./plugins/CXFundamental/FileSystem/${nameSpace}/${player.uniqueId}")
    }

    fun openFile(player: Player): CXYamlConfiguration {
        val uid = player.uniqueId.toString()
        if (defaultFileKeepOperation == FileKeepOperation.CLOSE_AFTER_EDIT) {
            val filePath = getFilePathByPlayer(player)
            val configuration = CXYamlConfiguration()
            configuration.load(File(filePath))
            return configuration
        }
        return if (keepFileMap.keys.contains(uid)) {
            keepFileMap[uid]!!
        } else {
            val filePath = getFilePathByPlayer(player)
            val configuration = CXYamlConfiguration()
            configuration.load(File(filePath))
            if (defaultFileKeepOperation == FileKeepOperation.KEEP_IN_MEMORY) {
                keepFileMap[uid] = configuration
            }
            configuration
        }
    }

    fun saveDataToFile(player: Player, configuration: CXYamlConfiguration) {
        if (defaultFileSaveOperation == FileSaveOperation.SAVE_RIGHT_NOW) {
            val path = getFilePathByPlayer(player)
            configuration.save(File(path))
        }
    }

    fun saveData(player: Player, key: String, data: Any?) {
        val configuration = openFile(player)
        configuration[key] = data
        saveDataToFile(player, configuration)
    }

    fun getData(player: Player, key: String, default: Any? = null): Any? {
        val configuration = openFile(player)
        return configuration[key] ?: return default
    }

}

enum class FileKeepOperation {
    KEEP_IN_MEMORY,
    CLOSE_AFTER_EDIT
}

enum class FileSaveOperation {
    SAVE_RIGHT_NOW,
}