package cxplugins.cxfundamental.minecraft.server

import java.util.ArrayList

import java.util.HashMap
import org.bukkit.inventory.ItemStack

/**
 * ΪһЩ���ݵ��滻�ṩ����
 *
 */
object CXReplace {

    private var Replaces: MutableMap<String, String> = HashMap()
    @JvmStatic
            /**
             * ���һ���滻��
             *
             * @param Key ���滻������
             * @param Value �滻������
             */
    fun add(Key: String, Value: String) {
        Replaces[Key] = Value
    }
    @JvmStatic
            /**
             * ɾ��һ���滻��
             *
             * @param Key ��ɾ�����滻����
             */
    fun remove(Key: String) {
        Replaces.remove(Key)
    }
    @JvmStatic
            /**
             * ��һ���ַ�������һ���滻����
             *
             * @param R ���滻���ַ���
             * @return �滻����ַ���
             */
    fun replace(R: String): String {
        var R = R
        val From = ArrayList(Replaces.keys)

        for (i in From.indices) {
            R = R.replace(From[i].toRegex(), Replaces[From[i]].toString())
        }
        return R

    }
    /**
     * ��һ���б����һ���滻����
     *
     * @param R ���滻���б�
     * @return �滻����б�
     */
    @JvmStatic
    fun replace(R: MutableList<String>): List<String> {
        val From = ArrayList(Replaces.keys)
        for (j in R.indices) {
            for (i in From.indices) {
                R[j] = R[j].replace(From[i].toRegex(), Replaces[From[i]].toString())
            }
        }
        return R
    }
    /**
     * ��һ����Ʒ�Ľ��������ֽ���һ���滻����
     *
     * @param Item ���滻����Ʒ
     * @return �滻�����Ʒ
     */
    @JvmStatic
    fun replace(Item: ItemStack): ItemStack {
        val IM = Item.itemMeta
        IM.displayName = replace(IM.displayName)
        IM.lore = replace(IM.lore)
        Item.itemMeta = IM
        return Item
    }
}
