package cxplugins.cxfundamental.minecraft.server

import org.bukkit.Bukkit
import org.bukkit.entity.Player

/**
 *Ϊ��������Ϣ�ķ����ṩ����
 */
class CXMessage {

    /**
     * ��̬����
     */
    companion object {
        @JvmStatic
                /**
                 * ����Ľ��� ����
                 */
        var help: MutableList<String> = ArrayList()

        @JvmStatic
                /**
                 * �������ʱ�������ʾ�Ĵ���
                 */
        var errors: MutableMap<String, String> = HashMap()

        @JvmStatic
                /**
                 * ������� �����ʱ������ǰ׺
                 */
        var prefix = ""

        @JvmStatic
        /**
         * ����ִ�н��ȵļ�����
         */
        internal var Test_Report = 0

        @JvmStatic
                /**
                 * ���һ�������������б�
                 *
                 * @param h ��������
                 */
        fun addHelp(h: String) {
            help.add(h)
        }

        @JvmStatic
                /**
                 * ɾ��ĳһ������
                 *
                 * @param l �˰�����Ϣ��λ��
                 */
        fun removeHelp(l: Int) {
            help.removeAt(l)
        }

        @JvmStatic
                /**
                 * ��������Ϣ���͵����
                 *
                 * @param p ���
                 */
        fun sendHelpToPlayer(p: Player) {

            for (f in finalHelp) {
                sendMessage(p, f)
            }
            return
        }

        //System.out.println(help.size());
        //System.out.println(a.size());
        @JvmStatic
        private val finalHelp: List<String>
            get() {
                val a = ArrayList<String>()
                for (s in help) {
                    a.add(prefix + s)
                }
                return a
            }

        @JvmStatic
        private fun getError(Key: String): String? {
            return errors[Key]
        }

        @JvmStatic
        private fun setError(Key: String, Value: String) {
            errors.put(Key, Value)
            return
        }

        @JvmStatic
        private fun setDefaultErrors() {
            /*Errors["InCorrectParameters"] = CXColor.toColor("&4[Error]The parameters you input is NOT correct!")
            Errors["FileNotFount"] = CXColor.toColor("&4[Error]Could'n find the config file(files)")
            Errors["NotPlayer"] = CXColor.toColor("&4[Error]You're not a player")
            Errors["HaveNotPermission"] = CXColor.toColor("&4[Error]Sorry you don't have the permission")*/
        }

        /**
         * ��ĳһ����ҷ���һ������CXColor.toColor�������Ϣ
         *
         * @param P Ŀ�����
         * @param Message ���͵���Ϣ
         */
        @JvmStatic
        fun sendMessage(P: Player, Message: String) {
            P.sendMessage(CXColor.toColor(Message))
        }

        /**
         * ���̨������Ϣ
         *
         * @param Message ���͵���Ϣ
         */
        @JvmStatic
        fun sendToConsole(Message: String) {
            println("[CXMessage_ConsoleReporter]$Message")
        }

        /**
         * ����ȫ������
         *
         * @param Message ȫ�����������
         */
        @JvmStatic
        fun announce(Message: String) {
            Bukkit.broadcastMessage(CXColor.toColor(Message))
        }

        /**
         * ���̨���ͷ��������ȱ���
         */
        @JvmStatic
        fun processReport() {
            println("[CXMessage_ProcessReporter]$Test_Report")
            Test_Report++
        }

        /**
         *����������Ϊ0
         *
         */
        @JvmStatic
        fun processReset() {
            Test_Report = 0
        }
    }
}
