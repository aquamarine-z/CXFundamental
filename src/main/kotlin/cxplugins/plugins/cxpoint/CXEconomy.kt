package cxplugins.plugins.cxpoint

import cxplugins.cxfundamental.minecraft.file.CXYamlConfiguration
import cxplugins.cxfundamental.minecraft.server.CXGuide
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import cxplugins.*
import kotlin.collections.ArrayList
/*
* Error Methods:
* static getPointNameList()
* static hasPoint()*/
/**
 * ������������˻��Լ������б����
 */
class CXEconomy {

    /**
     * ��̬����
     */
    companion object{
        /**
         * ��ʾ�˷������Ѵ��������л��������б�
         */

        @JvmStatic
        val pointNameList: ArrayList<String>
            /**
             * ��ȡ�˷������Ѵ��������л��������б�
             */
            get() {

                var Config = CXYamlConfiguration("CXPlugins", "CXPoints.yml")

                val Names = ArrayList<String>(Config.getKeys(false));

                return Names
            }
        /**
         * �۳�ĳ�����һ����ĳ�ֻ���
         * @param arg0 �������
         * @param arg1 ��������
         * @param arg2 �۳����
         * @return �Ƿ�۳��ɹ�
         */
        @JvmStatic
        fun cost(arg0: String, arg1: String, arg2: Double): Boolean {
            if(!hasPoint(arg1)){
                return false
            }
            val Config=CXYamlConfiguration()
            Config.load("CXPlugins\\Players", "$arg0.yml")
            val AccountAmount = Config.getDouble("CXPoint.$arg1", 0.0)
            if (AccountAmount < arg2)
                return false
            else {
                Config.set("CXPoint.$arg1", AccountAmount - arg2)
                Config.save()
                val Event = PluginSetEconomyEvent()
                Bukkit.getServer().pluginManager.callEvent(Event)
                return true
            }
        }
        /**
         * �۳�ĳ�����һ����ĳ�ֻ���
         * @param arg0 ���
         * @param arg1 ��������
         * @param arg2 �۳����
         * @return �Ƿ�۳��ɹ�
         */
        @JvmStatic
        fun cost(arg0: Player, arg1: String, arg2: Double): Boolean {
            //CXYamlConfiguration Config=CXGuide.GYamlConfiguration();
            return cost(arg0.name, arg1, arg2)
        }
        /**
         * ����ĳ�����һ����ĳ�ֻ���
         * @param arg0 �������
         * @param arg1 ��������
         * @param arg2 ���ҽ��
         * @return �Ƿ����ɹ�
         */
        @JvmStatic
        fun give(arg0: String, arg1: String, arg2: Double): Boolean {
            if(!hasPoint(arg1)){
                return false
            }
            val Config=CXYamlConfiguration("CXPlugins\\Players", "$arg0.yml")
            //Config.load()
            val AccountAmount = Config.getDouble("CXPoint.$arg1", 0.0)
            Config.set("CXPoint.$arg1", AccountAmount + arg2)
            Config.save()
            val Event = PluginSetEconomyEvent()

            Bukkit.getServer().pluginManager.callEvent(Event)
            return true
        }
        /**
         * ����ĳ�����һ����ĳ�ֻ���
         * @param arg0 �������
         * @param arg1 ��������
         * @param arg2 ���ҽ��
         * @return �Ƿ����óɹ�
         */
        @JvmStatic
        fun set(arg0: String, arg1: String, arg2: Double): Boolean {
            if(!hasPoint(arg1)){
                return false
            }
            val Config=CXYamlConfiguration("CXPlugins\\Players", "$arg0.yml")
            //Config.load()

            Config.set("CXPoint.$arg1", arg2)
            Config.save()

            val Event = PluginSetEconomyEvent()

            Bukkit.getServer().pluginManager.callEvent(Event)
            return true
        }
        /**
         * ����ĳ�����һ����ĳ�ֻ���
         * @param arg0 ���
         * @param arg1 ��������
         * @param arg2 ���ҽ��
         * @return �Ƿ����óɹ�
         */
        @JvmStatic
        fun set(arg0: Player, arg1: String, arg2: Double): Boolean {
            return set(arg0.name, arg1, arg2)
        }
        /**
         * ����ĳ�����һ����ĳ�ֻ���
         * @param arg0 ���
         * @param arg1 ��������
         * @param arg2 ���ҽ��
         * @return �Ƿ����ɹ�
         */
        @JvmStatic
        fun give(arg0: Player, arg1: String, arg2: Double): Boolean {
            //CXYamlConfiguration Config=CXGuide.GYamlConfiguration();
            return give(arg0.name, arg1, arg2)
        }
        /**
         * �жϷ��������Ƿ񴴽���ĳ�ֻ���
         * @param arg0 ��������
         * @return ������ ����true ���򷵻�false
         */
        @JvmStatic
        fun hasPoint(arg0: String): Boolean {
            var Config = CXYamlConfiguration("CXPlugins", "CXPoints.yml")

            val Names = ArrayList<String>(Config.getKeys(false));
            return if (Names.contains(arg0))
                true
            else
                false
        }

        /**
         * ɾ���Ѿ�������ĳ�ֻ���
         *
         * @param arg0 ��������
         * @return �Ƿ�ɾ���ɹ� �ɹ��򷵻�true ���򷵻�false
         */
        @JvmStatic
        fun delete(arg0: String): Boolean {
            if(!hasPoint(arg0)){
                return false
            }
            var Config = CXYamlConfiguration("CXPlugins", "CXPoints.yml")
            //Config.load("CXPlugins", "CXPoints.yml")
            Config.set(arg0, null)
            Config.save()
            val Event = PluginSetEconomyEvent()

            Bukkit.getServer().pluginManager.callEvent(Event)
            return true
        }
        /**
         * ����ĳ�ֻ����ܷ�֧�����������
         *
         * @param arg0 ��������
         * @param arg1 �ܷ�֧��
         * @return �Ƿ����óɹ� �ɹ��򷵻�true ���򷵻�false
         */
        @JvmStatic
        fun setPayable(arg0: String, arg1: Boolean): Boolean {
            if(!hasPoint(arg0)){
                return false
            }
            var Config = CXYamlConfiguration("CXPlugins", "CXPoints.yml")
            //Config.load("CXPlugins", "CXPoints.yml")
            Config.set("$arg0.Payable", arg1)
            Config.save()
            val Event = PluginSetEconomyEvent()

            Bukkit.getServer().pluginManager.callEvent(Event)
            return true
        }
        /**
         * �ж�ĳ�ֻ����ܷ�֧�����������
         *
         * @param arg0 ��������
         * @return �Ƿ��ܹ�֧�� ���򷵻�true ���ܷ���false
         */
        @JvmStatic
        fun getPayable(arg0: String): Boolean {
            if(!hasPoint(arg0)){
                return false
            }
            var Config = CXYamlConfiguration("CXPlugins", "CXPoints.yml")
            return Config.getBoolean("$arg0.Payable")
        }
        /**
         * ����ĳ�ֻ���
         *
         * @param arg0 ��������
         * @param arg1 �ܷ�֧��
         * @return �Ƿ񴴽��ɹ� �ɹ�����true ���򷵻�false
         */
        @JvmStatic
        fun create(arg0: String, arg1: Boolean=false): Boolean {

            var Config = CXYamlConfiguration("CXPlugins", "CXPoints.yml")
            //Config.load()
            Config.set("$arg0.Payable", arg1)
            Config.save()
            val Event = PluginSetEconomyEvent()
            Bukkit.getServer().pluginManager.callEvent(Event)
            return true
        }

        /**
         * ��ĳ����ҵ�һ��������ĳ�ֻ���֧��������һ�����
         *
         * @param From ֧�������
         * @param To ֧����Ŀ��
         * @param PointName ֧���Ļ�������
         * @param Amount ���
         * @return �Ƿ�֧���ɹ�
         */
        @JvmStatic
        fun pay(From: String, To: String, PointName: String, Amount: Double): Boolean {
            if(!hasPoint(PointName)){
                return false
            }
            //var Config = CXYamlConfiguration("CXPlugins\\Players", "$From.yml")

            //Config.load()
            if (Amount <= 0) return false
            if (get(From, PointName) <= Amount)
                return false
            else {
                cost(From, PointName, Amount)
                give(To, PointName, Amount)
            }
            return true

        }
        /**
         * ��ĳ����ҵ�һ��������ĳ�ֻ���֧��������һ�����
         *
         * @param From ֧�������
         * @param To ֧����Ŀ��
         * @param PointName ֧���Ļ�������
         * @param Amount ���
         * @return �Ƿ�֧���ɹ�
         */
        @JvmStatic
        fun pay(From: Player, To: Player, PointName: String, Amount: Double): Boolean {
            return pay(From.name, To.name, PointName, Amount)
        }

        /**
         * ��ȡĳ���ĳ�ֻ��ҵĴ��
         *
         * @param arg0 �����
         * @param arg1 ������
         * @return ��ҵĴ����
         */
        @JvmStatic
        fun get(arg0: String, arg1: String): Double {
            if(!hasPoint(arg1)){
                return 0.0
            }
            var Config = CXYamlConfiguration("CXPlugins\\Players", "$arg0.yml")
            //Config.load()
            return Config.getDouble("CXPoint.$arg1", 0.0)
        }
        /**
         * ��ȡĳ���ĳ�ֻ��ҵĴ��
         *
         * @param arg0 ���
         * @param arg1 ������
         * @return ��ҵĴ����
         */
        @JvmStatic
        fun get(arg0: Player, arg1: String): Double {
            return get(arg0.name, arg1)
        }
    }


}

