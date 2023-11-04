package cxplugins.cxfundamental.minecraft.datastructure

/**
 * 为实现二叉树结构提供的类
 *
 * @param ValueClass 需要此Tree存储的类型
 * @constructor Create empty binary tree
 *
 */
class CXBinaryTree<ValueClass> {
    /**
     * 该节点保存的值
     */
    var value: ValueClass? = null

    /**
     * 左子树
     */
    internal var leftSon: CXBinaryTree<ValueClass>? = null

    /**
     * 右子树
     */
    internal var rightSon: CXBinaryTree<ValueClass>? = null

    /**
     * 父节点
     */
    internal var father: CXBinaryTree<ValueClass>? = null

    /**
     * 子节点的深度
     */
    val sonDepth: Int
        /**
         * 获取子节点的深度
         */
        get() {
            if (leftSon == null && rightSon == null)
                return 1
            else if (leftSon == null) return rightSon!!.sonDepth + 1
            return leftSon!!.sonDepth + 1

        }

    /**
     * 自己关于父节点的深度
     */
    val selfDepth: Int
        /**
         * 获取自己关于父节点的深度
         */
        get() = if (father == null) 1 else father!!.selfDepth + 1

    /**
     * 无参构造函数
     */
    constructor() {
        // TODO 自动生成的构造函数存根
    }

    /**
     * 构造函数
     * @param arg0 保存在此节点的值
     */
    constructor(arg0: ValueClass) {
        value = arg0
        // TODO 自动生成的构造函数存根
    }

    /**
     * 设置左子树
     *
     * @param arg0 左子树
     */
    fun setLeftSon(arg0: CXBinaryTree<ValueClass>) {
        leftSon = arg0
        leftSon!!.father = this
    }

    /**
     * 获取左子树
     *
     * @return 左子树
     */
    fun getLeftSon(): CXBinaryTree<ValueClass>? {
        return leftSon
    }

    /**
     * 设置右子树
     *
     * @param arg0 右子树
     */
    fun setRightSon(arg0: CXBinaryTree<ValueClass>) {
        rightSon = arg0
        rightSon!!.father = this
    }

    /**
     * 获取右子树
     *
     * @return 右子树
     */
    fun getrightSon(): CXBinaryTree<ValueClass>? {
        return rightSon
    }

}
