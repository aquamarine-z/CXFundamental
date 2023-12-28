package cxplugins.cxfundamental.minecraft.file

/**
 * 未完工的类 表示操作玩家配置文件时的动作
 */
interface PlayerEditAction {
    fun action(config: CXYamlConfiguration)
}
