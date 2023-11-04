package cxplugins.cxfundamental.minecraft.plugin

import cxplugins.cxfundamental.minecraft.command.CPMLCommandExecutor.Companion.register
import cxplugins.cxfundamental.minecraft.command.CommandSenderType
import cxplugins.cxfundamental.minecraft.kotlindsl.openFrame
import cxplugins.cxfundamental.minecraft.kotlindsl.openFrameSynchronously
import cxplugins.cxfundamental.minecraft.kotlindsl.sendMessageWithColor
import cxplugins.cxfundamental.minecraft.math.geometry.Vector2I
import cxplugins.cxfundamental.minecraft.permission.CXCommandPermission
import cxplugins.cxfundamental.minecraft.server.CXItemStack
import cxplugins.cxfundamental.minecraft.swing.SwingButton
import cxplugins.cxfundamental.minecraft.swing.SwingFrame
import cxplugins.cxfundamental.minecraft.swing.SwingItemContainer
import cxplugins.cxfundamental.minecraft.swing.SwingMultipagePanel
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
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
        description="������UIϵͳ CXSwing"
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
        description = "CX����������"
        usage = "/cxp debug"
        action {
            return@action if (sender !is Player) {
                sender.sendMessageWithColor("&4[����]�����������һ�������ִ��")
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
            val multipagePanel1 = SwingMultipagePanel(Vector2I(0, 0), 4, 5)
            multipagePanel1.addEmptyPage()
            multipagePanel1.addEmptyPage()
            multipagePanel1.addEmptyPage()
            multipagePanel1.addEmptyPage()

            val button1 = object : SwingButton(Vector2I(0, 0)) {
                override fun onLeftClick(event: InventoryClickEvent) {
                    super.onLeftClick(event)
                    println(1)
                }
            }

            val button2 = object : SwingButton(Vector2I(1, 1)) {
                override fun onLeftClick(event: InventoryClickEvent) {
                    super.onLeftClick(event)
                    println(2)
                }
            }

            val button3 = object : SwingButton(Vector2I(2, 2)) {
                override fun onLeftClick(event: InventoryClickEvent) {
                    super.onLeftClick(event)
                    println(3)
                }
            }
            val button4 = object : SwingButton(Vector2I(3, 3)) {
                override fun onLeftClick(event: InventoryClickEvent) {
                    super.onLeftClick(event)
                    println(4)
                }
            }
            multipagePanel1.setComponent(0, button1)
            multipagePanel1.setComponent(1, button2)
            multipagePanel1.setComponent(2, button3)
            multipagePanel1.setComponent(3, button4)

            val multipagePanel2 = SwingMultipagePanel(Vector2I(4, 0), 4, 5)
            val container1 = object : SwingItemContainer(Vector2I(0, 0), itemInside = CXItemStack(1, 1, "", "")) {
                override fun onItemChange(event: InventoryClickEvent) {
                    super.onItemChange(event)
                    println(itemInside!!.type)
                }
            }
            val container2 = object : SwingItemContainer(Vector2I(1, 1)) {
                override fun onItemChange(event: InventoryClickEvent) {
                    super.onItemChange(event)
                    println(itemInside!!.type)
                }
            }
            val container3 = object : SwingItemContainer(Vector2I(2, 2)) {
                override fun onItemChange(event: InventoryClickEvent) {
                    super.onItemChange(event)
                    println(itemInside!!.type)
                }
            }
            val container4 = object : SwingItemContainer(Vector2I(3, 3)) {
                override fun onItemChange(event: InventoryClickEvent) {
                    super.onItemChange(event)
                    println(itemInside!!.type)
                }
            }

            multipagePanel2.addEmptyPage()
            multipagePanel2.addEmptyPage()
            multipagePanel2.addEmptyPage()
            multipagePanel2.addEmptyPage()
            multipagePanel2.setComponent(0, container1)
            multipagePanel2.setComponent(1, button2)
            multipagePanel2.setComponent(2, button3)
            multipagePanel2.setComponent(3, button4)
            window.setComponent(multipagePanel1)
            window.setComponent(multipagePanel2)
            SwingFrame.openFrame((sender as Player), window)
            true
        }
    }
    register {
        target(CommandSenderType.PLAYER)
        plugin = CXFundamentalMain.pluginMain
        command = "cxp debug"
        permission = "cxfundamental.op"
        description = "CX����������"
        usage = "/cxp debug"
        action {
            debug = !debug
            sender.sendMessageWithColor("&2&l[CXFundamental]Debugģʽ�Ѿ�����Ϊ$debug")
            true
        }
    }
    register {
        target(CommandSenderType.PLAYER)
        plugin = CXFundamentalMain.pluginMain
        command = "cxp store"
        permission = "cxfundamental.op"
        description = "CX����������"
        usage = "/cxp store"
        action {
            thread(start = true, isDaemon = true) {
                sender.sendMessageWithColor("&3&l���ڴ�Gitee����ȡ����б��ļ�...........")
                try {
                    getAndSave(
                        "https://gitee.com/yuncaiyuye/cxplugins/raw/master/plugins.yml",
                        File(".\\plugins\\CXPlugins"),
                        "plugins.yml"
                    )
                } catch (exception: Exception) {
                    sender.sendMessageWithColor("&2&l[����]��ȡʧ�� ������������!")

                } finally {
                    sender.sendMessageWithColor("&3&l��ȡ�ɹ�!")
                }
                if (sender is Player) {
                    (sender as Player).openFrameSynchronously(OnlinePluginControlFrame(), CXFundamentalMain.pluginMain)
                }
            }
            true
        }
    }
}