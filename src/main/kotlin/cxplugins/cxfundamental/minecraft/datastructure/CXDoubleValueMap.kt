package cxplugins.cxfundamental.minecraft.datastructure

import java.util.HashMap

/**
 * Ϊʵ��˫ֵ�ֵ��ṩ����
 *
 * @param Key ��������
 * @param Value1 ֵ1������
 * @param Value2 ֵ2������
 * @constructor ����˫ֵ�ֵ�
 */
public class CXDoubleValueMap<Key, Value1, Value2> {
    /**
     * ֵ1���ֵ�
     */
    internal var value1Map: HashMap<Key, Value1>? = null
    /**
     * ֵ2���ֵ�
     */
    internal var value2Map: HashMap<Key, Value2>? = null

    init {
        value1Map = HashMap()
        value2Map = HashMap()
        // TODO �Զ����ɵĹ��캯�����
    }

    /**
     * ��Ӽ�-ֵ��
     *
     * @param key ��
     * @param value1 ֵ1
     * @param value2 ֵ2
     */
    fun put(key: Key, value1: Value1, value2: Value2) {
        value1Map!!.put(key,value1)
        value2Map!!.put(key,value2)
    }

    /**
     * ��ȡֵ1
     *
     * @param key ��
     * @return ֵ1��ֵ
     */
    fun getFirstValue(key: Key): Value1? {
        return value1Map!![key]
    }

    /**
     * ��ȡֵ2
     *
     * @param key ��
     * @return ֵ2��ֵ
     */
    fun getSecondValue(key: Key): Value2? {
        return value2Map!![key]
    }
}
