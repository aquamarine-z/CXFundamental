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
     * @param Amount ����Ʒ������
     * @param Display ��Ʒ��չʾ��
     * @param Lore ����Ʒ�Ľ��� ��'|'������
     * @param Dura ����Ʒ��ĥ���
     */
    constructor(id: Int, Amount: Int, Display: String, lores: Array<out String>, Dura: Short = 0) {
        this.typeId = id
        this.amount = Amount
        val IM = this.itemMeta
        IM.displayName = Display.replace("&".toRegex(), "��")
        val L = lores.toMutableList()
        for (i in L.indices) {
            L[i] = L[i].replace("&".toRegex(), "��")
        }
        IM.lore = L
        this.durability = Dura
        this.itemMeta = IM
    }

    /**
     * ��չʾ����ע��ͨ��CXColor.toColor�����ٽ��й���Ĺ�����
     * @param ID ����Ʒ��ID��
     * @param Amount ����Ʒ������
     * @param Display ��Ʒ��չʾ��
     * @param Lore ����Ʒ�Ľ��� ��'|'������
     * @param Dura ����Ʒ��ĥ���
     */
    constructor(ID: Int, Amount: Int, Display: String, Lore: String, Dura: Short = 0) {
        this.typeId = ID
        this.amount = Amount
        val IM = this.itemMeta
        IM.displayName = Display.replace("&".toRegex(), "��")
        val L = Arrays.asList(*Lore.split("\\|".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())
        for (i in L.indices) {
            L[i] = L[i].replace("&".toRegex(), "��")
        }
        IM.lore = L
        this.durability = Dura.toShort()
        this.itemMeta = IM
    }

    /**
     * ��չʾ����ע��ͨ��CXColor.toColor�����ٽ��й���Ĺ�����
     * @param material ����Ʒ�Ĳ���
     * @param Amount ����Ʒ������
     * @param Display ��Ʒ��չʾ��
     * @param Lore ����Ʒ�Ľ��� ��'|'������
     * @param Dura ����Ʒ��ĥ���
     */
    constructor(material: Material, Amount: Int, Display: String, lores: Array<out String>, Dura: Short = 0) {
        this.type = material
        this.amount = Amount
        val IM = this.itemMeta
        IM.displayName = Display.replace("&".toRegex(), "��")
        val L = lores.toMutableList()
        for (i in L.indices) {
            L[i] = L[i].replace("&".toRegex(), "��")
        }
        IM.lore = L
        this.durability = Dura.toShort()
        this.itemMeta = IM
    }

    /**
     * ��չʾ����ע��ͨ��CXColor.toColor�����ٽ��й���Ĺ�����
     * @param material ����Ʒ�Ĳ���
     * @param Amount ����Ʒ������
     * @param Display ��Ʒ��չʾ��
     * @param Lore ����Ʒ�Ľ��� ��'|'������
     * @param Dura ����Ʒ��ĥ���
     */
    constructor(material: Material, Amount: Int, Display: String, Lore: String, Dura: Short = 0) {
        this.type = material
        this.amount = Amount
        val IM = this.itemMeta
        IM.displayName = Display.replace("&".toRegex(), "��")
        val L = Arrays.asList(*Lore.split("\\|".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())
        for (i in L.indices) {
            L[i] = L[i].replace("&".toRegex(), "��")
        }
        IM.lore = L
        this.durability = Dura.toShort()
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
         * @param ID ����Ʒ��ID��
         * @param Amount ����Ʒ������
         * @param Display ��Ʒ��չʾ��
         * @param Lore ����Ʒ�Ľ��� ��'|'������
         * @param Dura ����Ʒ��ĥ���
         */
        @JvmStatic
        fun create(ID: Int, Amount: Int, Display: String, Lore: String, Dura: Int): ItemStack {
            val Result = ItemStack(ID, Amount)
            val IM = Result.itemMeta
            IM.displayName = Display.replace("&".toRegex(), "��")
            val L = Arrays.asList(*Lore.split("\\|".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())
            for (i in L.indices) {
                L[i] = L[i].replace("&".toRegex(), "��")
            }
            IM.lore = L
            Result.itemMeta = IM
            Result.durability = Dura.toShort()
            return Result
        }

        /**
         * ��չʾ����ע��ͨ��CXColor.toColor������������Ʒ
         * @param ID ����Ʒ��ID��
         * @param Amount ����Ʒ������
         * @param Display ��Ʒ��չʾ��
         * @param Lore ����Ʒ�Ľ��� ��'|'������
         */
        @JvmStatic
        fun create(ID: Int, Amount: Int, Display: String, Lore: String): ItemStack {
            val Result = ItemStack(ID, Amount)
            val IM = Result.itemMeta
            IM.displayName = Display.replace("&".toRegex(), "��")
            val L = Arrays.asList(*Lore.split("\\|".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())
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
         * @param Item ����Ʒ
         * @param Amount �۳�������
         * @return �۳�֮�����Ʒ
         */
        @JvmStatic
        fun cost(Item: ItemStack, Amount: Int): ItemStack {
            val Result = Item
            Result.amount = Item.amount - Amount
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

