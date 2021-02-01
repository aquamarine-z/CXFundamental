package cxplugins.cxfundamental.minecraft.datastructure

import java.util.HashMap

/**
 * 为实现双值字典提供的类
 *
 * @param Key 键的类型
 * @param Value1 值1的类型
 * @param Value2 值2的类型
 * @constructor 创建双值字典
 */
public class CXDoubleValueMap<Key, Value1, Value2> {
    /**
     * 值1的字典
     */
    internal var value1Map: HashMap<Key, Value1>? = null
    /**
     * 值2的字典
     */
    internal var value2Map: HashMap<Key, Value2>? = null

    init {
        value1Map = HashMap()
        value2Map = HashMap()
        // TODO 自动生成的构造函数存根
    }

    /**
     * 添加键-值对
     *
     * @param key 键
     * @param value1 值1
     * @param value2 值2
     */
    fun put(key: Key, value1: Value1, value2: Value2) {
        value1Map!!.put(key,value1)
        value2Map!!.put(key,value2)
    }

    /**
     * 获取值1
     *
     * @param key 键
     * @return 值1的值
     */
    fun getFirstValue(key: Key): Value1? {
        return value1Map!![key]
    }

    /**
     * 获取值2
     *
     * @param key 键
     * @return 值2的值
     */
    fun getSecondValue(key: Key): Value2? {
        return value2Map!![key]
    }
}
