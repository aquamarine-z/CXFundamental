package cxplugins.cxfundamental.minecraft.plugin

import cxplugins.cxfundamental.minecraft.command.CPMLCommandExecutor.Companion.register
import cxplugins.cxfundamental.minecraft.command.CommandSenderType
import cxplugins.cxfundamental.minecraft.kotlindsl.openFrame
import cxplugins.cxfundamental.minecraft.kotlindsl.openFrameSynchronously
import cxplugins.cxfundamental.minecraft.kotlindsl.sendMessageWithColor
import cxplugins.cxfundamental.minecraft.permission.CXCommandPermission
import cxplugins.cxfundamental.minecraft.swing.SwingFrame
import cxplugins.cxfundamental.minecraft.swing.dsl.*
import cxplugins.cxfundamental.minecraft.swing.windows.SwingInputWindow
import org.bukkit.entity.Player
import org.bukkit.permissions.PermissionDefault
import java.io.*
import java.net.MalformedURLException
import java.net.URL
import kotlin.concurrent.thread

internal fun getAndSave(address: String, destination: File, fileName: String = address) {
    val url: URL
    val inputStream: BufferedInputStream
    val file: FileOutputStream
    try {

        if (!File(destination.canonicalPath + "\\" + fileName).exists()) {
            if (!destination.exists()) destination.mkdirs()
            File(destination.canonicalPath + "\\" + fileName).createNewFile()
        }
        //println("Getting $address ..........")
        url = URL(address)
        inputStream = BufferedInputStream(url.openStream())

        file = FileOutputStream(File(destination.canonicalPath + "\\" + fileName))
        var t: Int
        while (inputStream.read().also { t = it } != -1) {
            file.write(t)
        }
        file.close()
        inputStream.close()
        //println("Getting $address successfully!")
    } catch (e: MalformedURLException) {
        e.printStackTrace()
    } catch (e: FileNotFoundException) {
        e.printStackTrace()
    } catch (e: IOException) {
        e.printStackTrace()
    }

}

fun registerCommands() {
    CXCommandPermission.register {
        name = "cxfundamental"
        permission {
            name = "op"
            default = PermissionDefault.OP
        }
    }
    /*register{
        target(CommandSenderType.PLAYER)
        plugin=CXFundamentalMain.pluginMain
        command="uitest"
        description="测试新UI系统 CXSwing"
        usage="/uitest"
        permission="cxfundamental.op"
        action {
            val frame=CXFrame(6)
            val button=CXButton()
            button.leftClick {
                it.event.isCancelled=true
            }
            val multipagePanel=object:CXMultipagePanel(2,2){
                override fun onPreviousPage(event: CXMultipagePanelEvent) {
                }
            }
            multipagePanel.setComponent(0,0,button)
            multipagePanel.addNewPage()
            multipagePanel.addNewPage()
            multipagePanel.cycle=true
            frame.setComponent(0,0,multipagePanel)
            //println(frame.getComponent(3,2))
            (sender as Player).openFrame(frame)
            true
        }
    }*/
    register {
        target(CommandSenderType.PLAYER)
        plugin = CXFundamentalMain.pluginMain
        command = "cxp"
        permission = "cxfundamental.op"
        description = "Open the cxplugins manager"
        usage = "/cxp debug"
        action {
            return@action if (sender !is Player) {
                sender.sendMessageWithColor("&4[Error]This command should be executed by a play")
                true
            } else {
                (sender as Player).openFrame(DownloadedPluginControlFrame(sender as Player))
                true
            }
        }
    }
    register {
        target(CommandSenderType.PLAYER)
        plugin = CXFundamentalMain.pluginMain
        command = "swing"
        permission = "cxfundamental.op"
        description = "swing test"
        usage = "/swing test"
        action {
            val window = SwingFrame("Test", 6)
            window.setContent {
                multipagePanel(0, 0, 4, 5) {

                    newPage {
                        button(0, 0) {
                            onLeftClick {
                                println(1)
                            }
                        }
                    }
                    newPage {
                        button(1, 1) {
                            onLeftClick {
                                println(2)
                            }
                        }
                    }
                    newPage {
                        button(2, 2) {
                            onLeftClick {
                                println(3)
                            }
                        }
                    }
                    newPage {
                        itemContainer(3, 3) {
                            title("ItemInside")
                            description("This is a default item inside the container")
                            amount(5)
                        }
                    }
                }
                multipagePanel(4, 0, 4, 5) {
                    newPage {
                        button(0, 0) {
                            onLeftClick {
                                println(1)
                            }
                        }
                    }
                    newPage {
                        button(1, 1) {
                            onLeftClick {
                                println(2)
                            }
                        }
                    }
                    newPage {
                        button(2, 2) {
                            onLeftClick {
                                println(3)
                            }
                        }
                    }
                    newPage {
                        itemContainer(3, 3) {
                            title("ItemInside")
                            description("This is a default item inside the container")
                            amount(5)
                        }
                    }
                }
            }
            SwingFrame.openFrame(sender as Player, SwingInputWindow())
            true
        }
    }
    register {
        target(CommandSenderType.PLAYER)
        plugin = CXFundamentalMain.pluginMain
        command = "cxp debug"
        permission = "cxfundamental.op"
        description = "CXFundamental debug mode"
        usage = "/cxp debug"
        action {
            debug = !debug
            sender.sendMessageWithColor("&2&l[CXFundamental]Debug mode has been switched to:$debug")
            true
        }
    }
    register {
        target(CommandSenderType.PLAYER)
        plugin = CXFundamentalMain.pluginMain
        command = "cxp store"
        permission = "cxfundamental.op"
        description = "CX插件管理界面"
        usage = "/cxp store"
        action {
            thread(start = true, isDaemon = true) {
                sender.sendMessageWithColor("&3&lDownloading plugins list file from Gitee...........")
                try {
                    getAndSave(
                        "https://gitee.com/yuncaiyuye/cxplugins/raw/master/plugins.yml",
                        File(".\\plugins\\CXPlugins"),
                        "plugins.yml"
                    )
                } catch (exception: Exception) {
                    sender.sendMessageWithColor("&2&l[Error]Failed to download,please check out the Internet connection!")

                } finally {
                    sender.sendMessageWithColor("&3&lDownloaded successfully!")
                }
                if (sender is Player) {
                    (sender as Player).openFrameSynchronously(OnlinePluginControlFrame(), CXFundamentalMain.pluginMain)
                }
            }
            true
        }
    }
}