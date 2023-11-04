package cxplugins.cxfundamental.minecraft.math.geometry

import org.bukkit.configuration.serialization.ConfigurationSerializable
import java.lang.Double.max as maxD
import java.lang.Double.min as minD
import java.lang.Integer.max as maxI
import java.lang.Integer.min as minI

class Rectangle2D(var x1: Double, var y1: Double, var x2: Double, var y2: Double) : ConfigurationSerializable {
    constructor(v1: Vector2D, v2: Vector2D) : this(v1.x, v1.y, v2.x, v2.y) {
        compareValues()
    }

    private fun compareValues() {
        val tx1 = x1
        val tx2 = x2
        val ty1 = y1
        val ty2 = y2
        x1 = minD(tx1, tx2)
        x2 = maxD(tx1, tx2)
        y1 = minD(ty1, ty2)
        y2 = maxD(ty1, ty2)
    }

    init {
        compareValues()

    }

    fun toRectangle2I(): Rectangle2I {
        return Rectangle2I(x1.toInt(), y1.toInt(), x2.toInt(), y2.toInt())
    }

    fun getPoint1(): Vector2D {
        return Vector2D(x1, y1)
    }

    fun getPoint2(): Vector2D {
        return Vector2D(x2, y2)
    }

    fun isPointInside(point: Vector2D): Boolean {
        return point.x in x1..x2 && point.y >= y1 && point.y <= y2
    }

    fun isPointInside(point: Vector2I): Boolean {
        return point.x >= x1 && point.x <= x2 && point.y >= y1 && point.y <= y2
    }

    override fun serialize(): MutableMap<String, Any> {
        val map = mutableMapOf<String, Any>()
        map["x1"] = x1
        map["x2"] = x2
        map["y1"] = y1
        map["y2"] = y2
        return map
    }
}

class Rectangle2I(var x1: Int, var y1: Int, var x2: Int, var y2: Int) : ConfigurationSerializable {
    constructor(v1: Vector2I, v2: Vector2I) : this(v1.x, v1.y, v2.x, v2.y) {
        compareValues()
    }

    private fun compareValues() {
        val tx1 = x1
        val tx2 = x2
        val ty1 = y1
        val ty2 = y2
        x1 = minI(tx1, tx2)
        x2 = maxI(tx1, tx2)
        y1 = minI(ty1, ty2)
        y2 = maxI(ty1, ty2)
    }

    init {
        compareValues()
    }

    fun toRectangle2D(): Rectangle2D {
        return Rectangle2D(x1.toDouble(), y1.toDouble(), x2.toDouble(), y2.toDouble())
    }

    fun getPoint1(): Vector2I {
        return Vector2I(x1, y1)
    }

    fun getPoint2(): Vector2I {
        return Vector2I(x2, y2)
    }

    fun isPointInside(point: Vector2D): Boolean {
        return (point.x >= x1 && point.x <= x2 && point.y >= y1 && point.y <= y2)
    }

    fun isPointInside(point: Vector2I): Boolean {
        return (point.x in x1..x2) && (point.y in y1..y2)
    }

    override fun serialize(): MutableMap<String, Any> {
        val map = mutableMapOf<String, Any>()
        map["x1"] = x1
        map["x2"] = x2
        map["y1"] = y1
        map["y2"] = y2
        return map
    }

}