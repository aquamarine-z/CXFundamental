package cxplugins.cxfundamental.minecraft.server

import org.bukkit.inventory.ItemStack

/**
 * �ԶԻ���Ի�����ɫ�����ṩ����
 *
 */
class CXColor {


    /**
     * �ԶԻ���Ի�����ɫ�����ṩ���� ��̬����
     *
     *
     */
    companion object {
        /**
        ��ɫ
         */
        val RED = "��4"

        /**
        ����ɫ
         */
        val DARK_BLUE = "��1"

        /**
        ��ɫ
         */
        val GREEN = "��2"

        /**
        ��ɫ
         */
        val BLUE = "��3"

        /**
        ���ɫ
         */
        val DEEP_PINK = "��5"

        /**
        ��ɫ
         */
        val GOLDEN = "��6"

        /**
        ��ɫ
         */
        val GRAY = "��7"

        /**
        ��ɫ
         */
        val BLACK = "��0"

        /**
        ���ɫ
         */
        val DARK_GRAY = "��8"

        /**
        ��ɫ
         */
        val PURPLE = "��9"

        /**
        ����ɫ
         */
        val LBLUE = "��b"

        /**
        ӣ�Һ�
         */
        val LPINK = "��c"

        /**
        �л���
         */
        val LMIDLINE = "��m"

        /**
        �»���
         */
        val LUNDERLINE = "��n"

        /**
        ����ɫ
         */
        val LYELLOW = "��e"

        /**
        ��ɫ
         */
        val LWHITE = "��f"

        /**
        �Ӵ�����
         */
        val LBOLD = "��l"

        /**
        ǳ��ɫ
         */
        val LGREEN = "��a"

        /**
        ����ɫ
         */
        val LPURPLE = "��d"

        /**
        ��ɫ����'��'
         */
        val colorCode = "��"

        /**
         * ���ַ���������ɫ���� ����'&'��Ϊ'��'
         *
         * @param From ��Ҫ������ַ���
         * @return ��������ַ���
         */
        @JvmStatic
        fun toColor(From: String): String {
            return From.replace("&".toRegex(), "��")
        }

        /**
         * ����Ʒ�ı����Լ����ܽ�����ɫ���� ����'&'��Ϊ'��'
         *
         * @param From ��Ҫ�������Ʒ
         * @return ���������Ʒ
         */
        @JvmStatic
        fun toColor(From: ItemStack): ItemStack {
            CXReplace.add("&", "��")
            val Result = CXReplace.replace(From)
            CXReplace.remove("&")
            return Result
        }
    }

}
