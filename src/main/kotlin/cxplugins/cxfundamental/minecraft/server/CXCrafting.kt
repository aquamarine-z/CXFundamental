package cxplugins.cxfundamental.minecraft.server

import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

/**
 * 未完工的类 主要表示一种九个格子的合成表
 */
class CraftingRecipe(
    item1: ItemStack,
    item2: ItemStack,
    item3: ItemStack,
    item4: ItemStack,
    item5: ItemStack,
    item6: ItemStack,
    item7: ItemStack,
    item8: ItemStack,
    item9: ItemStack
) {
    //var recipe=Array(3,{Array(3,{it -> 0}))
    var recipe = Array(3) { Array(3, { it -> ItemStack(0) }) }

    init {
        recipe[0][0] = item1
        recipe[0][1] = item2
        recipe[0][2] = item3
        recipe[1][0] = item4
        recipe[1][1] = item5
        recipe[1][2] = item6
        recipe[2][0] = item7
        recipe[2][1] = item8
        recipe[2][2] = item9
    }
    /*constructor(rec:Array<Array<ItemStack>>){
        recipe[0][0]=rec[0][0]
        recipe[0][1]=rec[0][1]
        recipe[0][2]=rec[0][2]
        recipe[1][0]=rec[1][0]
        recipe[1][1]=rec[1][1]
        recipe[1][2]=rec[1][2]
        recipe[2][0]=rec[2][0]
        recipe[2][1]=rec[2][1]
        recipe[2][2]=rec[2][2]
    }
    constructor(rec:Array<ItemStack>){
        for(i in 0 until 3){
            for(j in 0 until 3)
            {
                recipe[i][j]=rec[i*3+j]
            }
        }
    }*/


}

/**
 * (未完工) 当玩家在合成的时候触发的异常
 *
 */
class CXCraftingException : Exception() {
    /**
     * 异常原因
     */
    enum class Reason(reason: String, errcode: Int)
}

/**
 * (未完工) 提供了玩家在一个容器界面合成的类
 *
 */
class CXCrafting {
    /**
     * (未完工) 控制合成的静态方法
     */
    companion object {
        /**
         *通过普通的容器进行合成
         */
        fun craftByInventory(inventory: Inventory): Inventory? {
            return null
        }
    }

}