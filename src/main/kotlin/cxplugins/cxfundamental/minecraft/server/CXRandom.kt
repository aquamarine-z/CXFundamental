package cxplugins.cxfundamental.minecraft.server

import java.util.*

/**
 * 为一些随机性问题提供的类
 *
 */
class CXRandom {

    /**
     * 静态方法
     *
     */
    companion object {
        /**
         * 从列表中随机抽出一定数量的元素
         *  @param EList 此列表
         *  @param Amount 抽出的数量
         */
        @JvmStatic
        fun <Value> randomList(EList: MutableList<Value>, Amount: Int): List<Value> {
            val Result = ArrayList<Value>()
            val s = EList.size
            val Rand = Random()
            for (i in 0 until Amount) {
                if (EList.size > 0) {
                    val Place = Rand.nextInt(EList.size)
                    Result.add(EList[Place])
                    EList.removeAt(Place)
                } else {
                    val Place = Rand.nextInt(s)
                    Result.add(Result[Place])
                }
            }
            return Result

        }

        /**
         * 随机一个在某个范围间的整数
         *
         * @param min 范围最小值
         * @param max 范围最大值
         * @return
         */
        @JvmStatic
        fun nextInteger(min: Int, max: Int): Int {
            return (Math.random() * (max - min + 1) + min).toInt()
        }

        /**
         * 随机一个在某个范围间的数
         *
         * @param min 范围最小值
         * @param max 范围最大值
         * @return
         */
        @JvmStatic
        fun nextDouble(min: Double, max: Double): Double {
            return Math.random() * (max - min + 1) + min
        }

        /**
         * 模拟一次抽奖操作
         *
         * @param Probability 概率
         * @return 若抽中 则返回true 否则返回false
         */
        @JvmStatic
        fun lottery(Probability: Double): Boolean {
            val t = Random().nextInt(100)
            return Probability > t
        }
    }
}
