package cxplugins.cxfundamental.minecraft.server

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.util.Vector

/**
 * 为Location类进行序列化提供的类
 * @param world
 * @param x
 * @param y
 * @param z
 */
class CXLocation : Location, ConfigurationSerializable {

    /**
     * 通过 world x y z 坐标进行构造的构造器
     */
    constructor(world: World, x: Double, y: Double, z: Double) : super(world, x, y, z) {
        // TODO 自动生成的构造函数存根
    }

    /**
     * 序列化的方法
     */
    override fun serialize(): Map<String, Any> {
        // TODO 自动生成的方法存根
        val result = HashMap<String, Any>()
        result["WorldName"] = super.getWorld().name
        result["Vector"] = super.toVector().clone()
        result["Yaw"] = super.getYaw()
        result["Pitch"] = super.getPitch()
        return result
    }

    /**
     * 通过父类Location构造的构造器
     */
    constructor(arg0: Location) : super(arg0.world, arg0.x, arg0.y, arg0.z) {
        super.setDirection(arg0.toVector().clone())
        super.setPitch(arg0.pitch)
        super.setYaw(arg0.yaw)
    }

    /**
     * 反序列化的构造器
     */
    constructor(m: Map<String, Any>) : this(
        Bukkit.getWorld(m["WorldName"] as String),
        (m["Vector"] as Vector).x,
        (m["Vector"] as Vector).y,
        (m["Vector"] as Vector).z
    ) {

        val Yaw = m["Yaw"] as Double
        val Pitch = m["Pitch"] as Double

        super.setPitch(Pitch.toFloat())
        super.setYaw(Yaw.toFloat())


    }

}
