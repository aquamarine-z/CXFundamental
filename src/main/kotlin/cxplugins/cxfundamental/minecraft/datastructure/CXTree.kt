package cxplugins.cxfundamental.minecraft.datastructure

import java.io.File
import java.util.ArrayList

/**
 * 为实现树状结构提供的类
 *
 * @param ValueClass 值的类
 * @property value 此节点保存的值
 * @constructor 新建一个树根或节点
 */
public class CXTree<ValueClass>(var value: ValueClass?) {
    /**
     * 子树的列表
     */
    private var son: MutableList<CXTree<ValueClass>>? = null

    /**
     * 父节点
     */
    private var father: CXTree<ValueClass>? = null

    /**
     * 子节点的深度
     */
    val sonDepth: Int
        /**
         * 获取子节点的深度
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
     * 自身相对于根节点的深度
     */
    val selfDepth: Int
        /**
         * 获取自身相对于根节点的深度
         */
        get() = if (this.father == null) 1 else this.father!!.selfDepth + 1

    /**
     * 根节点
     */
    val root: CXTree<ValueClass>
        /**
         * 获取根节点
         */
        get() = if (this.father == null) this else this.father!!.root

    init {
        this.son = ArrayList()
        this.father = null
    }// TODO 自动生成的构造函数存根

    override fun hashCode(): Int {
        return this.value!!.hashCode()
    }

    override fun equals(obj: Any?): Boolean {
        return if (obj !is CXTree<*>) {
            false
        } else this.value == obj.value
    }

    /**
     * 设置所有子树
     *
     * @param arg0 所有子树
     */
    fun setSons(arg0: MutableList<CXTree<ValueClass>>) {
        this.son = arg0
    }

    /**
     * 添加子树
     *
     * @param arg0 添加的子树
     */
    fun addSon(arg0: CXTree<ValueClass>) {
        arg0.father = this
        this.son!!.add(arg0)
    }

    /**
     * 获取所有子树
     *
     * @return 子树的列表
     */
    fun getSons(): List<CXTree<ValueClass>>? {
        return this.son
    }

    /**
     * 未完工的方法 深度优先搜索遍历此树
     *
     */
    fun depthFirstSearch() {

    }

    companion object {
        /**
         * 以树的结构打开某一个文件夹
         *
         * @param name 文件夹路径
         * @return 包含所有文件的树
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
	
