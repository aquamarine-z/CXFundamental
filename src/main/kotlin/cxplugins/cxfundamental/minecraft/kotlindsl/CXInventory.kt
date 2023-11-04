package cxplugins.cxfundamental.minecraft.kotlindsl

import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

/**
 * ����Ʒ������ɾ��ĳ����Ʒ ����Ϊ����costItem������
 * ����Ʒ���������Ʒ���Ƕѵ�״̬ ������ɾ��������costItem��ͬ����Ʒ(��������) ֱ��ɾ���������ﵽcostItem������Ϊֹ
 *
 * @param costItem ɾ������Ʒ ����costItem������(amount)����ɾ����Ʒ�Ķ���
 * @return �������ڴ���Ʒ��������ɾ�������� ��ɾ��������true ���򲻽���ɾ������������false
 */
fun Inventory.costItem(costItem: ItemStack): Boolean {
    var items = this.contents
    var itemAmount = 0
    var itemPlaces = ArrayList<Int>()
    var i = 0
    var costAmount = costItem.amount
    for (item in items) {
        if (item.equalsIgnoreAmount(costItem)) {
            itemAmount += item.amount
            itemPlaces.add(i)
        }
        i++
    }
    if (itemAmount < costAmount) {
        return false
    } else {
        for (place in itemPlaces) {
            var itemStack = this.getItem(place)
            if (itemStack.amount >= costAmount) {
                this.setItem(place, itemStack.cloneWithAmount(itemStack.amount - costAmount))
                return true
            } else {
                this.remove(place)
                costAmount -= itemStack.amount
            }
            if (costAmount <= 0) return true
        }
    }
    return true
}
