package cxplugins.cxfundamental.minecraft.datastructure

import java.io.File
import java.util.ArrayList

/**
 * Ϊʵ����״�ṹ�ṩ����
 *
 * @param ValueClass ֵ����
 * @property value �˽ڵ㱣���ֵ
 * @constructor �½�һ��������ڵ�
 */
public class CXTree<ValueClass>(var value: ValueClass?) {
    /**
     * �������б�
     */
    private var son: MutableList<CXTree<ValueClass>>? = null

    /**
     * ���ڵ�
     */
    private var father: CXTree<ValueClass>? = null

    /**
     * �ӽڵ�����
     */
    val sonDepth: Int
        /**
         * ��ȡ�ӽڵ�����
         */
        get() {
            var maxSon = 0
            for (tree in this.son!!) {
                val result = tree.sonDepth
                if (result > maxSon) maxSon = result
            }
            return maxSon + 1
        }

    /**
     * ��������ڸ��ڵ�����
     */
    val selfDepth: Int
        /**
         * ��ȡ��������ڸ��ڵ�����
         */
        get() = if (this.father == null) 1 else this.father!!.selfDepth + 1

    /**
     * ���ڵ�
     */
    val root: CXTree<ValueClass>
        /**
         * ��ȡ���ڵ�
         */
        get() = if (this.father == null) this else this.father!!.root

    init {
        this.son = ArrayList()
        this.father = null
    }// TODO �Զ����ɵĹ��캯�����

    override fun hashCode(): Int {
        return this.value!!.hashCode()
    }

    override fun equals(obj: Any?): Boolean {
        return if (obj !is CXTree<*>) {
            false
        } else this.value == obj.value
    }

    /**
     * ������������
     *
     * @param arg0 ��������
     */
    fun setSons(arg0: MutableList<CXTree<ValueClass>>) {
        this.son = arg0
    }

    /**
     * �������
     *
     * @param arg0 ��ӵ�����
     */
    fun addSon(arg0: CXTree<ValueClass>) {
        arg0.father = this
        this.son!!.add(arg0)
    }

    /**
     * ��ȡ��������
     *
     * @return �������б�
     */
    fun getSons(): List<CXTree<ValueClass>>? {
        return this.son
    }

    /**
     * δ�깤�ķ��� �������������������
     *
     */
    fun depthFirstSearch() {

    }

    companion object {
        /**
         * �����Ľṹ��ĳһ���ļ���
         *
         * @param name �ļ���·��
         * @return ���������ļ�����
         */
        fun openDictoryAsTree(name: File): CXTree<File> {
            val file = CXTree(name)
            for (fileName in file.value!!.listFiles()!!) {
                if (fileName.name == name.name) continue
                if (fileName.isFile) file.addSon(CXTree(fileName))
                if (fileName.isDirectory) file.addSon(openDictoryAsTree(fileName))
            }
            return file
        }
    }
}
	
