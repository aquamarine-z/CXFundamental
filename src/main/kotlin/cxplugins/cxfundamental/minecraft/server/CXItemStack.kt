package cxplugins.cxfundamental.minecraft.server

import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import java.util.*

/**
 * ��ItemStack��������չ����
 */
class CXItemStack : ItemStack {
    /**
     * ��չʾ����ע��ͨ��CXColor.toColor�����ٽ��й���Ĺ�����
     * @param ID ����Ʒ��ID��
     * @param amount ����Ʒ������
     * @param display ��Ʒ��չʾ��
     * @param Lore ����Ʒ�Ľ��� ��'|'������
     * @param durability ����Ʒ��ĥ���
     */
    constructor(id: Int, amount: Int, display: String, lores: Array<out String>, durability: Short = 0) {
        this.typeId = id
        this.amount = amount
        val IM = this.itemMeta
        IM.displayName = display.replace("&".toRegex(), "��")
        val L = lores.toMutableList()
        for (i in L.indices) {
            L[i] = L[i].replace("&".toRegex(), "��")
        }
        IM.lore = L
        this.durability = durability
        this.itemMeta = IM
    }

    /**
     * ��չʾ����ע��ͨ��CXColor.toColor�����ٽ��й���Ĺ�����
     * @param id ����Ʒ��ID��
     * @param amount ����Ʒ������
     * @param display ��Ʒ��չʾ��
     * @param lore ����Ʒ�Ľ��� ��'|'������
     * @param durability ����Ʒ��ĥ���
     */
    constructor(id: Int, amount: Int, display: String, lore: String, durability: Short = 0) {
        this.typeId = id
        this.amount = amount
        val IM = this.itemMeta
        IM.displayName = display.replace("&".toRegex(), "��")
        val L = Arrays.asList(*lore.split("\\|".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())
        for (i in L.indices) {
            L[i] = L[i].replace("&".toRegex(), "��")
        }
        IM.lore = L
        this.durability = durability.toShort()
        this.itemMeta = IM
    }

    /**
     * ��չʾ����ע��ͨ��CXColor.toColor�����ٽ��й���Ĺ�����
     * @param material ����Ʒ�Ĳ���
     * @param amount ����Ʒ������
     * @param display ��Ʒ��չʾ��
     * @param Lore ����Ʒ�Ľ��� ��'|'������
     * @param durability ����Ʒ��ĥ���
     */
    constructor(material: Material, amount: Int, display: String, lores: Array<out String>, durability: Short = 0) {
        this.type = material
        this.amount = amount
        val IM = this.itemMeta
        IM.displayName = display.replace("&".toRegex(), "��")
        val L = lores.toMutableList()
        for (i in L.indices) {
            L[i] = L[i].replace("&".toRegex(), "��")
        }
        IM.lore = L
        this.durability = durability.toShort()
        this.itemMeta = IM
    }

    /**
     * ��չʾ����ע��ͨ��CXColor.toColor�����ٽ��й���Ĺ�����
     * @param material ����Ʒ�Ĳ���
     * @param amount ����Ʒ������
     * @param display ��Ʒ��չʾ��
     * @param lore ����Ʒ�Ľ��� ��'|'������
     * @param durability ����Ʒ��ĥ���
     */
    constructor(material: Material, amount: Int, display: String, lore: String, durability: Short = 0) {
        this.type = material
        this.amount = amount
        val IM = this.itemMeta
        IM.displayName = display.replace("&".toRegex(), "��")
        val L = Arrays.asList(*lore.split("\\|".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())
        for (i in L.indices) {
            L[i] = L[i].replace("&".toRegex(), "��")
        }
        IM.lore = L
        this.durability = durability.toShort()
        this.itemMeta = IM
    }

    /**
     * ���������ж�������Ʒ�Ƿ���ͬ
     *
     * @param arg0 ����һ����Ʒ
     * @return ����� �򷵻�true ���򷵻�false
     */
    fun equalsIgnoreAmount(arg0: ItemStack): Boolean {
        val a = this.clone()
        val b = arg0.clone()
        return a == b
    }

    /**
     * ���ڴ�����Ʒ �ж���Ʒ��ͬ�ȷ����ľ�̬��
     */
    companion object {
        /**
         * ��չʾ����ע��ͨ��CXColor.toColor������������Ʒ
         * @param id ����Ʒ��ID��
         * @param amount ����Ʒ������
         * @param display ��Ʒ��չʾ��
         * @param lore ����Ʒ�Ľ��� ��'|'������
         * @param durability ����Ʒ��ĥ���
         */
        @JvmStatic
        fun create(id: Int, amount: Int, display: String, lore: String, durability: Int): ItemStack {
            val Result = ItemStack(id, amount)
            val IM = Result.itemMeta
            IM.displayName = display.replace("&".toRegex(), "��")
            val L = Arrays.asList(*lore.split("\\|".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())
            for (i in L.indices) {
                L[i] = L[i].replace("&".toRegex(), "��")
            }
            IM.lore = L
            Result.itemMeta = IM
            Result.durability = durability.toShort()
            return Result
        }

        /**
         * ��չʾ����ע��ͨ��CXColor.toColor������������Ʒ
         * @param id ����Ʒ��ID��
         * @param amount ����Ʒ������
         * @param display ��Ʒ��չʾ��
         * @param lore ����Ʒ�Ľ��� ��'|'������
         */
        @JvmStatic
        fun create(id: Int, amount: Int, display: String, lore: String): ItemStack {
            val Result = ItemStack(id, amount)
            val IM = Result.itemMeta
            IM.displayName = display.replace("&".toRegex(), "��")
            val L = Arrays.asList(*lore.split("\\|".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())
            for (i in L.indices) {
                L[i] = L[i].replace("&".toRegex(), "��")
            }
            IM.lore = L
            Result.itemMeta = IM
            return Result
        }

        /**
         * ��ĳ����Ʒ�۳�һ������
         *
         * @param item ����Ʒ
         * @param amount �۳�������
         * @return �۳�֮�����Ʒ
         */
        @JvmStatic
        fun cost(item: ItemStack, amount: Int): ItemStack {
            val Result = item
            Result.amount = item.amount - amount
            return Result
        }

        /**
         * ���������ж�������Ʒ�Ƿ���ͬ
         *
         * @param arg0 ����һ����Ʒ
         * @return ����� �򷵻�true ���򷵻�false
         */
        @JvmStatic
        fun equalsIgnoreAmount(a: ItemStack, b: ItemStack): Boolean {
            var a = a
            var b = b
            a = a.clone()
            b = b.clone()
            a.amount = 1
            b.amount = 1
            return a == b
        }
    }

}

