package cxplugins.cxfundamental.minecraft.server

import java.util.HashMap

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.util.Vector
import org.bukkit.block.Block
import org.bukkit.World

/**
 * ��ʾһ����ά��������
 * ͨ��x1 x2 y1 y2 z1 z2 ����ʾһ��������
 * �����������Ϊ|x1-x2|*|y1-y2|*|z1-z2|
 * @constructor ����һ�����������
 */
class CXCuboid : ConfigurationSerializable {
    /**
     * ��ʾ����������[from] �� [to] ��[from]Ϊmin(x1,x2),min(y1,y2),min(z1,z2)
     */
    var from: Vector
    /**
     * ��ʾ����������[from] �� [to] ��[to]Ϊmax(x1,x2),max(y1,y2),max(z1,z2)
     */
    var to: Vector

    /**
     * ��ʾ������������������
     */
    var world: World?=null;

    /**
     * ��ʾ�����������������������
     */
    internal var WorldName: String=""
    val length: Int
        /**
         * ��ȡ������ĳ���
         */
        get() = (to.x - from.x).toInt()
    val width: Int
        /**
         * ��ȡ������Ŀ��
         */
        get() = (to.z - from.z).toInt()
    val height: Int
        /**
         * ��ȡ������ĸ߶�
         */
        get() = (to.y - from.y).toInt()

    /**
     * ͨ����������������������
     * @param arg0 ��һ������
     * @param arg1 �ڶ�������
     */
    constructor(arg0: Vector, arg1: Vector) {
        from = Vector.getMinimum(arg0, arg1)
        to = Vector.getMaximum(arg0, arg1)
    }
    /**
     * ͨ�������������������� ָ����������������
     *  @param arg0 ��һ������
     * @param arg1 �ڶ�������
     */
    constructor(arg0: Vector, arg1: Vector, arg3: World) {
        from = Vector.getMinimum(arg0, arg1)
        to = Vector.getMaximum(arg0, arg1)
        world = arg3
    }

    /**
     * �ж�ĳһ�������Ƿ��ڴ���������
     *
     * @param arg0 ������
     * @return ������������ ����true ���򷵻�false
     */
    fun isLocationInCuboid(arg0: Location): Boolean {
        return isVectorInCuboid(arg0.toVector())
    }
    /**
     * �ж�ĳһ�������Ƿ��ڴ���������
     *
     * @param arg0 ������
     * @return ������������ ����true ���򷵻�false
     */
    fun isVectorInCuboid(arg0: Vector): Boolean {

        return arg0.isInAABB(from, to)
    }
    /**
     * �ж�ĳһ�������Ƿ��ڴ���������
     *
     * @param arg0 �˷���
     * @return ������������ ����true ���򷵻�false
     */
    fun isBlockInCuboid(arg0: Block): Boolean {
        return isLocationInCuboid(arg0.location)
    }
    /**
     * �жϴ��������Ƿ�������һ���������нӴ�
     *
     * @param arg0 ����һ��������
     * @return ���нӴ� ����true ���򷵻�false
     */
    fun contacts(arg0: CXCuboid): Boolean {
        var result = false
        if (arg0.world!!.name != world!!.name) return false
        if (arg0.isVectorInCuboid(from)) result = true
        if (arg0.isVectorInCuboid(to)) result = true
        if (isVectorInCuboid(arg0.from)) result = true
        if (isVectorInCuboid(arg0.to)) result = true
        return result
    }
    /**
     * ������������ĳ�ַ�������
     *
     * @param blockID �����ķ���
     */
    fun fill(blockID: Int) {
        for (i in from.blockX..to.blockX) {
            for (j in from.blockY..to.blockY) {
                for (k in from.blockZ..to.blockZ) {
                    val b = world!!.getBlockAt(i, j, k)
                    b.typeId = blockID

                }
            }
        }
    }
    /**
     * ���л�
     */
    override fun serialize(): Map<String, Any> {
        val Result = HashMap<String, Any>()
        Result["From"] = from
        Result["To"] = to
        Result["WorldName"] = WorldName
        return Result
    }
    /**
     * �����л�������
     */
    constructor(Map: Map<String, Any>) {
        this.from = Map["From"] as Vector
        this.to = Map["To"] as Vector
        this.WorldName = Map["WorldName"] as String
        this.world = Bukkit.getWorld(WorldName)
        return

    }
}
