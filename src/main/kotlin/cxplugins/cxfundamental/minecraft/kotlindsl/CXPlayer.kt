package cxplugins.cxfundamental.minecraft.kotlindsl


import cxplugins.cxfundamental.minecraft.file.CXPlayerDataEditor
import cxplugins.cxfundamental.minecraft.file.CXYamlConfiguration
import cxplugins.plugins.cxpoint.CXEconomy
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable

/**
 * �������ͬ�����߳�������
 * �������첽�߳��е���
 * @param reason ���߳���ԭ��
 * @param plugin ִ��ͬ�������Ĳ��
 */
fun Player.kickSynchronously(reason: String, plugin: JavaPlugin) {
    var thread = object : BukkitRunnable() {
        override fun run() {
            this@kickSynchronously.kickPlayer(reason)
        }
    }
    thread.runTask(plugin)
}

/**
 * ��ȡ��Ҵ洢��Players�ļ����������ļ��е�����
 * (�������CXPlayerDataEditor)
 * @param path ���ݵ�·��
 * @return ���ݵ�ֵ
 */
fun Player.getData(path: String): Any? {
    return CXPlayerDataEditor.get(this, path)
}

/**
 * ������Ҵ洢��Players�ļ����������ļ��е�����
 * (�������CXPlayerDataEditor)
 * @param path ���ݵ�·��
 * @param value ���ĵ�ֵ
 */
fun Player.editData(path: String, value: Any?) {
    CXPlayerDataEditor.edit(this, path, value)

}

/**
 * ��ȡ��Ҵ洢��Players�ļ����е������ļ�
 * (�������CXPlayerDataEditor)
 * @return ����ҵ������ļ�
 */
fun Player.getPlayerDataConfiguration(): CXYamlConfiguration {
    return CXPlayerDataEditor.getPlayerConfiguration(this)
}

/**
 * �ж���������ļ����Ƿ���ĳһ������
 * (�������CXPlayerDataEditor)
 * @param path �����ݵ�·��
 * @return ���д����� �򷵻�true ���򷵻�false
 */
fun Player.hasData(path: String): Boolean {
    return CXPlayerDataEditor.hasData(this, path)
}

/**
 * �ж������Players�ļ������Ƿ��������ļ�
 * (�������CXPlayerDataEditor)
 * @return ���д��ļ� �򷵻�true ���򷵻�false
 */
fun Player.hasDataFile(): Boolean {
    return CXPlayerDataEditor.hasPlayerData(this.displayName + ".yml")
}

/**
 * ʹ��ҵ������˻��п۳�һ����ĳһ�ֻ���
 * (�������CXEconomy)
 * @param pointName ��������
 * @param amount ����
 * @return �Ƿ�۳��ɹ� ͨ������true
 */
fun Player.costPoint(pointName: String, amount: Double): Boolean {
    return CXEconomy.cost(this, pointName, amount)
}

/**
 * ʹ��ҵ������˻������һ����ĳһ�ֻ���
 *(�������CXEconomy)
 * @param pointName ��������
 * @param amount ����
 * @return �Ƿ���ӳɹ� ͨ������true
 */
fun Player.givePoint(pointName: String, amount: Double): Boolean {
    return CXEconomy.give(this, pointName, amount)
}

/**
 * ʹ��ҵ������˻�ĳһ�ֻ��ҵ���������Ϊһ��ֵ
 *(�������CXEconomy)
 * @param pointName ��������
 * @param amount ����
 * @return �Ƿ����óɹ� ͨ������true
 */
fun Player.setPoint(pointName: String, amount: Double): Boolean {
    return CXEconomy.set(this, pointName, amount)
}

/**
 * ��ȡ��ҵ������˻�ĳһ�ֻ��ҵ�����
 *(�������CXEconomy)
 * @param pointName ��������
 * @return �����˻��еĻ�������
 */
fun Player.getPoint(pointName: String): Double {
    return CXEconomy.get(this, pointName)
}

/**
 * ʹ�������һ���֧��һ��������ĳ�ֻ���
 *(�������CXEconomy)
 * @param pointName ���ҵ�����
 * @param amount ֧��������
 * @param destination ֧����Ŀ��
 * @return ������һ����������� �򷵻�false ���򷵻�true
 */
fun Player.payPoint(pointName: String, amount: Double, destination: Player): Boolean {
    return CXEconomy.pay(this, destination, pointName, amount)
}
