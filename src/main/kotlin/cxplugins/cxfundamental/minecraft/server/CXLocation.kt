package cxplugins.cxfundamental.minecraft.server

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.util.Vector

/**
 * ΪLocation��������л��ṩ����
 * @param world
 * @param x
 * @param y
 * @param z
 */
class CXLocation : Location, ConfigurationSerializable {

    /**
     * ͨ�� world x y z ������й���Ĺ�����
     */
    constructor(world: World, x: Double, y: Double, z: Double) : super(world, x, y, z) {
        // TODO �Զ����ɵĹ��캯�����
    }

    /**
     * ���л��ķ���
     */
    override fun serialize(): Map<String, Any> {
        // TODO �Զ����ɵķ������
        val result = HashMap<String, Any>()
        result["WorldName"] = super.getWorld().name
        result["Vector"] = super.toVector().clone()
        result["Yaw"] = super.getYaw()
        result["Pitch"] = super.getPitch()
        return result
    }

    /**
     * ͨ������Location����Ĺ�����
     */
    constructor(arg0: Location) : super(arg0.world, arg0.x, arg0.y, arg0.z) {
        super.setDirection(arg0.toVector().clone())
        super.setPitch(arg0.pitch)
        super.setYaw(arg0.yaw)
    }

    /**
     * �����л��Ĺ�����
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
