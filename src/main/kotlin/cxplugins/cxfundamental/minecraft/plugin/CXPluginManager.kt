package cxplugins.cxfundamental.minecraft.plugin

import cxplugins.cxfundamental.minecraft.file.CXYamlConfiguration
import cxplugins.cxfundamental.minecraft.kotlindsl.*
import cxplugins.cxfundamental.minecraft.server.CXInventory
import cxplugins.cxfundamental.minecraft.server.CXItemStack
import cxplugins.cxfundamental.minecraft.server.CXPluginMain

import cxplugins.cxfundamental.minecraft.ui.*
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import java.io.BufferedOutputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.net.URL
import kotlin.concurrent.thread

class DownloadedPluginControlFrame(val owner: Player) : CXFrame(6) {
    init {
        var multiPagePanel = CXMultipagePanel(6)
        var plugins = Bukkit.getPluginManager().plugins
        var amount = 0
        for (plugin in plugins) {
            if (plugin is CXPluginMain) {
                var item = if (plugin.itemInMenu != null) {
                    plugin.itemInMenu!!.clone()
                } else {
                    var itemStack = CXItemStack(plugin.itemTypeInMenu, 1, plugin.name, "")

                    itemStack.clone()
                }
                item.appendLore("&3&lDescription:${plugin.description}".toColor())
                item.appendLore("&3&lLeftClick:Shutdown this plugin")
                item.appendLore("&3&lRightClick:Start this plugin")
                item.appendLore("&3&l[LeftClick+Shift]:Reload this plugin")
                item.appendLore("&4&l[RightClick+Shift]:Remove this plugin")
                val button = object : CXButton(item) {

                    override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                        event.isCancelled = true


                        if (Bukkit.getPluginManager().isPluginEnabled(plugin)) {
                            Bukkit.getPluginManager().disablePlugin(plugin)
                            owner.sendMessageWithColor("&2&l[CXFundamental]This plugin has been shutdown successfully")
                        } else {
                            owner.sendMessageWithColor("&4&l[CXFundamental]You can't shutdown a plugin which has been already shutdown")
                        }
                        return
                    }

                    override fun onRightClick(event: InventoryClickEvent, frame: CXFrame) {
                        event.isCancelled = true
                        if (!Bukkit.getPluginManager().isPluginEnabled(plugin)) {
                            Bukkit.getPluginManager().enablePlugin(plugin)
                            owner.sendMessageWithColor("&2&l[CXFundamental]This plugin has been started successfully")
                        } else {
                            owner.sendMessageWithColor("&4&l[CXFundamental]You can't start a plugin which is running now!")
                        }
                        return
                    }

                    override fun onLeftShiftClick(event: InventoryClickEvent, frame: CXFrame) {
                        event.isCancelled = true
                        try {
                            (plugin).reload()
                        } catch (exception: NoSuchMethodException) {
                            owner.sendMessageWithColor("&4&l[CXFundamental]This plugin doesn't contain the [Reload] method")
                            return
                        }
                        owner.sendMessageWithColor("&2&l[CXFundamental]This plugins has been reloaded successfully")

                    }

                    override fun onRightShiftClick(event: InventoryClickEvent, frame: CXFrame) {
                        super.onRightShiftClick(event, frame)
                        val optionPane = object : CXOptionPane(
                            1,
                            "&4Are you sure to remove this plugin?",
                            CXOptionPaneButtonType.CONFIRM,
                            CXOptionPaneButtonType.CANCEL
                        ) {
                            override fun onClickConfirm(event: InventoryClickEvent, frame: CXFrame) {
                                super.onClickConfirm(event, frame)
                                Bukkit.getPluginManager().disablePlugin(plugin)
                                if (File(".\\plugins\\${plugin.name}.jar").exists()) {
                                    File(".\\plugins\\${plugin.name}.jar").delete()
                                    owner.sendMessageWithColor("&2&l[CXFundamental]This plugin has been removed successfully")
                                } else {
                                    owner.sendMessageWithColor("&4&l[Error]Can't find the file of this plugin,please make sure the file is named <插件名>.jar")
                                }
                                owner.openFrame(DownloadedPluginControlFrame(owner))
                            }

                            override fun onClickCancel(event: InventoryClickEvent, frame: CXFrame) {
                                super.onClickCancel(event, frame)
                                owner.openFrame(DownloadedPluginControlFrame(owner))
                            }
                        }
                        owner.openFrame(optionPane)
                    }
                }
                //button.clickSound= Sound.BLOCK_NOTE_BELL
                var vector = CXInventory.integerToPos(amount % 45)
                multiPagePanel.setWithCreateNewPage(
                    amount / 45,
                    vector.blockX,
                    vector.blockY,
                    button,
                    "&3&lCXPlugins Management Frame".toColor()
                )

                amount++
            }
        }
        multiPagePanel.setButtonOnButtonBar(4) {
            item = CXItemStack(Material.COMMAND, 1, "&3&l联网下载CXPlugins", "&3&l点我获取CXPlugins网上下载菜单")
            leftClick { inventoryClickEvent: InventoryClickEvent, cxFrame: CXFrame ->
                thread(true, true) {

                    inventoryClickEvent.whoClicked.sendMessageWithColor("&3&l正在从Gitee中拉取插件列表文件...........")
                    try {
                        getAndSave(
                            "https://gitee.com/yuncaiyuye/cxplugins/raw/master/plugins.yml",
                            File(".\\plugins\\CXPlugins"),
                            "plugins.yml"
                        )
                    } catch (exception: Exception) {
                        inventoryClickEvent.whoClicked.sendMessageWithColor("&2&l[错误]拉取失败 请检查网络连接!")

                    } finally {
                        inventoryClickEvent.whoClicked.sendMessageWithColor("&3&l拉取成功!")
                    }
                    inventoryClickEvent.whoClicked.openFrameSynchronously(
                        OnlinePluginControlFrame(),
                        CXFundamentalMain.pluginMain
                    )
                }

            }
        }
        this.setPanel(multiPagePanel)
    }
}

class OnlinePluginVersionViewFrame(val pluginName: String) : CXFrame(6) {
    private fun readUrlFile(fileUrl: String?): ByteArray? {
        var r: ByteArray? = null
        var out: ByteArrayOutputStream? = null
        try {
            val url = URL(fileUrl)
            val `in` = url.openStream()
            out = ByteArrayOutputStream()
            val b = ByteArray(8 * 1024)
            var read: Int = 0
            while (`in`.read(b).also { read = it } != -1) out.write(b, 0, read)
            out.flush()
            r = out.toByteArray()
            out.close()
            `in`.close()
        } catch (e: java.lang.Exception) {
        }
        return r
    }

    private fun downloadPlugin(pluginName: String, address: String): Boolean {
        val file = File(".\\plugins\\$pluginName.jar")
        if (!file.exists()) {
            file.createNewFile()
        }
        val writer = BufferedOutputStream(FileOutputStream(file))
        //val onlineStream= BufferedInputStream(URL(URLDecoder.decode(address,"utf-8")).openStream())
        var buf = readUrlFile(address)
        writer.write(buf)
        writer.close()
        return true
    }

    init {
        val configuration = CXYamlConfiguration("CXPlugins", "plugins.yml")
        val section = configuration.getConfigurationSection("$pluginName")
        val versions = section.getKeys(false)
        var i = 0
        val multipagePanel = CXMultipagePanel(6)
        multipagePanel.addEmptyPage("&3下载:$pluginName")
        for (version in versions) {
            val button = object : CXButton() {
                init {
                    item = CXItemStack(Material.CHEST, 1, "&c&l$pluginName:$version", "")
                    val descriptionList = "&c&l介绍:${section["$version.description"] ?: "无"}".split("\\n")
                    for (description in descriptionList) {
                        item!!.appendLore(description)
                    }

                    item!!.appendLore("&c&l适用的Minecraft版本:${section["$version.version"] ?: "所有"}")
                    item!!.appendLore("&c&l下载链接:${section["$version.download"] ?: "暂无链接"}")
                }

                override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                    super.onLeftClick(event, frame)
                    val pluginNames = ArrayList<String>()
                    for (plugin in Bukkit.getPluginManager().plugins) {
                        pluginNames.add(plugin.name)
                    }
                    if (pluginName in pluginNames) {
                        val optionPane = object : CXOptionPane(
                            1,
                            "&4&l此插件已经存在 你确认要重新下载它吗?",
                            CXOptionPaneButtonType.CONFIRM,
                            CXOptionPaneButtonType.CANCEL
                        ) {
                            override fun onClickConfirm(event: InventoryClickEvent, frame: CXFrame) {
                                super.onClickConfirm(event, frame)
                                val thread = object : Thread() {
                                    override fun run() {
                                        try {
                                            event.whoClicked.sendMessageWithColor("&3&l正在下载$pluginName 此过程可能需要一段时间...")
                                            downloadPlugin(pluginName, "${section["$version.download"]}")
                                        } catch (exception: Exception) {
                                            exception.printStackTrace()
                                            event.whoClicked.sendMessageWithColor("&4[错误]下载失败 请检查网络设置:${exception.message}")
                                        } finally {
                                            event.whoClicked.sendMessageWithColor("&2下载 $pluginName 成功! 请手动重启服务器 更新此插件 命令:/reload")

                                        }
                                    }
                                }
                                thread.start()
                                event.whoClicked
                            }

                            override fun onClickCancel(event: InventoryClickEvent, frame: CXFrame) {
                                super.onClickCancel(event, frame)
                                event.whoClicked.openFrame(OnlinePluginVersionViewFrame(pluginName))
                            }
                        }
                        event.whoClicked.openFrame(optionPane)
                        return
                    }
                    val thread = object : Thread() {
                        override fun run() {
                            try {
                                downloadPlugin(pluginName, "${section["$version.download"]}")
                            } catch (exception: Exception) {
                                exception.printStackTrace()
                                event.whoClicked.sendMessageWithColor("&4[错误]下载失败 请检查网络设置:${exception.message}")
                            } finally {
                                event.whoClicked.sendMessageWithColor("&2下载 $pluginName 成功! 请手动重启服务器 更新此插件 命令:/reload")
                            }
                        }
                    }
                    thread.start()
                }
            }
            multipagePanel.setWithCreateNewPage(i, button, "&3下载:$pluginName")
            i++
        }
        multipagePanel.setButtonOnButtonBar(4) {
            item = CXItemStack(Material.IRON_DOOR, 1, "&3&l返回", "&3&l点我返回插件下载菜单")
            leftClick { inventoryClickEvent: InventoryClickEvent, cxFrame: CXFrame ->
                inventoryClickEvent.whoClicked.openFrame(OnlinePluginControlFrame())
            }
        }
        this.setPanel(multipagePanel)
    }
}

class OnlinePluginControlFrame : CXFrame(6) {


    init {
        val configuration = CXYamlConfiguration("CXPlugins", "plugins.yml")
        val multipagePanel = CXMultipagePanel(6)
        multipagePanel.addEmptyPage("&3&lCXPlugins:")
        val pluginNames = ArrayList<String>()
        for (plugin in Bukkit.getPluginManager().plugins) {
            pluginNames.add(plugin.name)
        }
        var index = 0
        for (pluginName in configuration.getKeys(false)) {

            val description = configuration.getString("$pluginName.description", "No description")
            val button = object : CXButton() {
                init {
                    this.item = CXItemStack(Material.CHEST, 1, "&9$pluginName", "&9$description|&9点我进入查看详细")
                }

                override fun onLeftClick(event: InventoryClickEvent, frame: CXFrame) {
                    event.whoClicked.openFrame(OnlinePluginVersionViewFrame(pluginName))
                }
            }

            multipagePanel.setWithCreateNewPage(index, button, "&3&lCXPlugins:")
            index++

        }
        multipagePanel.setButtonOnButtonBar(4) {
            item = CXItemStack(Material.IRON_DOOR, 1, "&3&l返回", "&3点我返回插件操作菜单")
            leftClick { event, cxFrame ->
                event.whoClicked.openFrame(DownloadedPluginControlFrame(event.whoClicked as Player))
            }
        }
        this.setPanel(multipagePanel)
    }

}

class OnlinePluginVersionViewingFrame(val pluginName: String) : CXFrame(6) {
    private fun readUrlFile(fileUrl: String?): ByteArray? {
        var r: ByteArray? = null
        var out: ByteArrayOutputStream? = null
        try {
            val url = URL(fileUrl)
            val `in` = url.openStream()
            out = ByteArrayOutputStream()
            val b = ByteArray(8 * 1024)
            var read: Int = 0
            while (`in`.read(b).also { read = it } != -1) out.write(b, 0, read)
            out.flush()
            r = out.toByteArray()
            out.close()
            `in`.close()
        } catch (e: java.lang.Exception) {
        }
        return r
    }

    private fun downloadPlugin(pluginName: String, address: String): Boolean {
        val file = File(".\\plugins\\$pluginName.jar")
        if (!file.exists()) {
            file.createNewFile()
        }
        val writer = BufferedOutputStream(FileOutputStream(file))
        //val onlineStream= BufferedInputStream(URL(URLDecoder.decode(address,"utf-8")).openStream())
        var buf = readUrlFile(address)
        writer.write(buf)
        writer.close()
        return true
    }

    init {
        this.apply {
            multipagePanel {
                val configuration = CXYamlConfiguration("CXPlugins", "plugins.yml")
                val section = configuration.getConfigurationSection("$pluginName")
                val versions = section.getKeys(false)
            }
        }
    }
}