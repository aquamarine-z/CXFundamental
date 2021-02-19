package cxplugins.cxfundamental.minecraft.server

import cxplugins.cxfundamental.minecraft.command.CommandException
import cxplugins.cxfundamental.minecraft.command.CPMLCommandExecutor
import cxplugins.cxfundamental.minecraft.kotlindsl.toColor
import org.bukkit.Material

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin

open class CXPluginMain(item:ItemStack?) : JavaPlugin(){
    var noticePrefix:String
    var description="A CXPlugin"
    var itemTypeInMenu= Material.WATER_BUCKET
    var itemInMenu: ItemStack?=null
    init{
        this.noticePrefix="&3&l[${this.name}]:".toColor()
        itemInMenu=if(item==null){
            CXItemStack(itemTypeInMenu,1,this.name,"")
        }
        else{
            item
        }
    }
    override fun onEnable() {

    }
    open fun reload(){
        throw NoSuchMethodException()
    }
    private fun sendReportToSender(sender:CommandSender,report:String){
        if(sender is Player){
            CXMessage.sendMessage(sender,report)
        }
        else{
            CXMessage.sendToConsole(report)
        }
    }
    override fun onCommand(
        sender: CommandSender?,
        command: Command?,
        label: String?,
        args: Array<String>?
    ): Boolean {
        try {
            return cxplugins.cxfundamental.minecraft.command.CPMLCommandExecutor.execute(sender!!, command!!, label!!, args!!)
        }
        catch (exception:CommandException){
            var place=exception.place+1
            when(exception.reason){
                CommandException.Reason.BOOLEAN->{
                    sendReportToSender(sender!!,"$noticePrefix ��${place}����������Ϊtrue����false")
                    return true
                }
                CommandException.Reason.INTEGER->{
                    sendReportToSender(sender!!,"$noticePrefix ��${place}����������Ϊһ������")
                    return true
                }
                CommandException.Reason.DOUBLE->{
                    sendReportToSender(sender!!,"$noticePrefix ��${place}����������Ϊһ������")
                    return true
                }
                CommandException.Reason.FLOAT->{
                    sendReportToSender(sender!!,"$noticePrefix ��${place}����������Ϊһ������")
                    return true
                }
                CommandException.Reason.WORLD->{
                    sendReportToSender(sender!!,"$noticePrefix ��${place}����������Ϊһ���Ѿ����ڵ�����浵������")
                    return true
                }
                CommandException.Reason.PLAYER->{
                    sendReportToSender(sender!!,"$noticePrefix ��${place}����������Ϊһ���Ѿ�ע������������")
                    return true
                }
                CommandException.Reason.ONLINEPLAYER->{
                    sendReportToSender(sender!!,"$noticePrefix ��${place}����������Ϊһ���Ѿ�ע������������ �Ҵ���ұ�������")
                    return true
                }
                CommandException.Reason.MULTIPAMAMETERNOTFOUNDEND->{
                    sendReportToSender(sender!!,"$noticePrefix ���ϲ���ȱ�ٽ������")
                    return true
                }
                CommandException.Reason.MULTIPAMAMETERNOTFOUNDSTART->{
                    sendReportToSender(sender!!,"$noticePrefix ���ϲ���ȱ�ٿ�ʼ���")
                    return true
                }
                CommandException.Reason.WRONGAMOUNT->{
                    sendReportToSender(sender!!,"$noticePrefix ������������")
                    return true
                }
                CommandException.Reason.BYTE->{
                    sendReportToSender(sender!!,"$noticePrefix ��${place}����������Ϊһ������")
                    return true
                }
                CommandException.Reason.SHORT->{
                    sendReportToSender(sender!!,"$noticePrefix ��${place}����������Ϊһ������")
                    return true
                }
                CommandException.Reason.LONG->{
                    sendReportToSender(sender!!,"$noticePrefix ��${place}����������Ϊһ������")
                    return true
                }
            }
        }
        return false
    }
}