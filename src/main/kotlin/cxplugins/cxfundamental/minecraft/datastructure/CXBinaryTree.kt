package cxplugins.cxfundamental.minecraft.datastructure

/**
 * Ϊʵ�ֶ������ṹ�ṩ����
 *
 * @param ValueClass ��Ҫ��Tree�洢������
 * @constructor Create empty binary tree
 *
 */
class CXBinaryTree<ValueClass> {
    /**
     * �ýڵ㱣���ֵ
     */
    var value: ValueClass? = null

    /**
     * ������
     */
    internal var leftSon: CXBinaryTree<ValueClass>? = null

    /**
     * ������
     */
    internal var rightSon: CXBinaryTree<ValueClass>? = null

    /**
     * ���ڵ�
     */
    internal var father: CXBinaryTree<ValueClass>? = null

    /**
     * �ӽڵ�����
     */
    val sonDepth: Int
        /**
         * ��ȡ�ӽڵ�����
         */
        get() {
            if (leftSon == null && rightSon == null)
                return 1
            else if (leftSon == null) return rightSon!!.sonDepth + 1
            return leftSon!!.sonDepth + 1

        }

    /**
     * �Լ����ڸ��ڵ�����
     */
    val selfDepth: Int
        /**
         * ��ȡ�Լ����ڸ��ڵ�����
         */
        get() = if (father == null) 1 else father!!.selfDepth + 1

    /**
     * �޲ι��캯��
     */
    constructor() {
        // TODO �Զ����ɵĹ��캯�����
    }

    /**
     * ���캯��
     * @param arg0 �����ڴ˽ڵ��ֵ
     */
    constructor(arg0: ValueClass) {
        value = arg0
        // TODO �Զ����ɵĹ��캯�����
    }

    /**
     * ����������
     *
     * @param arg0 ������
     */
    fun setLeftSon(arg0: CXBinaryTree<ValueClass>) {
        leftSon = arg0
        leftSon!!.father = this
    }

    /**
     * ��ȡ������
     *
     * @return ������
     */
    fun getLeftSon(): CXBinaryTree<ValueClass>? {
        return leftSon
    }

    /**
     * ����������
     *
     * @param arg0 ������
     */
    fun setRightSon(arg0: CXBinaryTree<ValueClass>) {
        rightSon = arg0
        rightSon!!.father = this
    }

    /**
     * ��ȡ������
     *
     * @return ������
     */
    fun getrightSon(): CXBinaryTree<ValueClass>? {
        return rightSon
    }

}
