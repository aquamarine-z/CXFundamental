package cxplugins.cxfundamental.minecraft.server

import java.util.HashMap

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.util.Vector
import org.bukkit.block.Block
import org.bukkit.World

/**
 * 表示一个三维的立方体
 * 通过x1 x2 y1 y2 z1 z2 来表示一个立方体
 * 此立方体体积为|x1-x2|*|y1-y2|*|z1-z2|
 * @constructor 创建一个立方体对象
 */
class CXCuboid : ConfigurationSerializable {
    /**
     * 表示这个立方体从[from] 到 [to] 即[from]为min(x1,x2),min(y1,y2),min(z1,z2)
     */
    var from: Vector
    /**
     * 表示这个立方体从[from] 到 [to] 即[to]为max(x1,x2),max(y1,y2),max(z1,z2)
     */
    var to: Vector

    /**
     * 表示此立方体所处的世界
     */
    var world: World?=null;

    /**
     * 表示此立方体所处的世界的名字
     */
    internal var WorldName: String=""
    val length: Int
        /**
         * 获取长方体的长度
         */
        get() = (to.x - from.x).toInt()
    val width: Int
        /**
         * 获取长方体的宽度
         */
        get() = (to.z - from.z).toInt()
    val height: Int
        /**
         * 获取长方体的高度
         */
        get() = (to.y - from.y).toInt()

    /**
     * 通过两个向量来创建立方体
     * @param arg0 第一个向量
     * @param arg1 第二个向量
     */
    constructor(arg0: Vector, arg1: Vector) {
        from = Vector.getMinimum(arg0, arg1)
        to = Vector.getMaximum(arg0, arg1)
    }
    /**
     * 通过两个向量创建立方体 指定立方体所在世界
     *  @param arg0 第一个向量
     * @param arg1 第二个向量
     */
    constructor(arg0: Vector, arg1: Vector, arg3: World) {
        from = Vector.getMinimum(arg0, arg1)
        to = Vector.getMaximum(arg0, arg1)
        world = arg3
    }

    /**
     * 判断某一个坐标是否在此立方体中
     *
     * @param arg0 此坐标
     * @return 若在立方体中 返回true 否则返回false
     */
    fun isLocationInCuboid(arg0: Location): Boolean {
        return isVectorInCuboid(arg0.toVector())
    }
    /**
     * 判断某一个向量是否在此立方体中
     *
     * @param arg0 此向量
     * @return 若在立方体中 返回true 否则返回false
     */
    fun isVectorInCuboid(arg0: Vector): Boolean {

        return arg0.isInAABB(from, to)
    }
    /**
     * 判断某一个方块是否在此立方体中
     *
     * @param arg0 此方块
     * @return 若在立方体中 返回true 否则返回false
     */
    fun isBlockInCuboid(arg0: Block): Boolean {
        return isLocationInCuboid(arg0.location)
    }
    /**
     * 判断此立方体是否与另外一个立方体有接触
     *
     * @param arg0 另外一个立方体
     * @return 若有接触 返回true 否则返回false
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
     * 将此立方体用某种方块填满
     *
     * @param blockID 填满的方块
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
     * 序列化
     */
    override fun serialize(): Map<String, Any> {
        val Result = HashMap<String, Any>()
        Result["From"] = from
        Result["To"] = to
        Result["WorldName"] = WorldName
        return Result
    }
    /**
     * 反序列化构造器
     */
    constructor(Map: Map<String, Any>) {
        this.from = Map["From"] as Vector
        this.to = Map["To"] as Vector
        this.WorldName = Map["WorldName"] as String
        this.world = Bukkit.getWorld(WorldName)
        return

    }
}
