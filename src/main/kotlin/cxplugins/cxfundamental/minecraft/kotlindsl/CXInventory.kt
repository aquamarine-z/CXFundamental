package cxplugins.cxfundamental.minecraft.kotlindsl

import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

/**
 * 在物品栏里面删除某个物品 数量为参数costItem的数量
 * 若物品栏里面的物品不是堆叠状态 则依次删除所有与costItem相同的物品(忽略数量) 直到删除的数量达到costItem的数量为止
 *
 * @param costItem 删除的物品 其中costItem的数量(amount)代表删除物品的多少
 * @return 若背包内此物品总量大于删除的数量 则删除并返回true 否则不进行删除操作并返回false
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
