package cxplugins.cxfundamental.minecraft.math.geometry

import java.lang.Double.max as maxD
import java.lang.Double.min as minD
import java.lang.Integer.max as maxI
import java.lang.Integer.min as minI

class Rectangle3D(var x1: Double, var y1: Double, var z1: Double, var x2: Double, var y2: Double, var z2: Double) {
    private fun compareValues() {
        val tx1 = x1
        val tx2 = x2
        val ty1 = y1
        val ty2 = y2
        val tz1 = z1
        val tz2 = z2
        x1 = minD(tx1, tx2)
        x2 = maxD(tx1, tx2)
        y1 = minD(ty1, ty2)
        y2 = maxD(ty1, ty2)
        z1 = minD(tz1, tz2)
        z2 = maxD(tz1, tz2)
    }

    init {
        compareValues()
    }

    fun toRectangle3I(): Rectangle3I {
        return Rectangle3I(x1.toInt(), y1.toInt(), z1.toInt(), x2.toInt(), y2.toInt(), z2.toInt())
    }

    fun getPoint1(): Vector3D {
        return Vector3D(x1, y1, z1)
    }

    fun getPoint2(): Vector3D {
        return Vector3D(x2, y2, z2)
    }

    fun isPointInside(point: Vector3D): Boolean {
        return point.x >= x1 && point.x <= x2 && point.y >= y1 && point.y <= y2 && point.z >= z1 && point.z <= z2
    }

    fun isPointInside(point: Vector3I): Boolean {
        return point.x >= x1 && point.x <= x2 && point.y >= y1 && point.y <= y2 && point.z >= z1 && point.z <= z2
    }
}

class Rectangle3I(var x1: Int, var y1: Int, var z1: Int, var x2: Int, var y2: Int, var z2: Int) {
    private fun compareValues() {
        val tx1 = x1
        val tx2 = x2
        val ty1 = y1
        val ty2 = y2
        val tz1 = z1
        val tz2 = z2
        x1 = minI(tx1, tx2)
        x2 = maxI(tx1, tx2)
        y1 = minI(ty1, ty2)
        y2 = maxI(ty1, ty2)
        z1 = minI(tz1, tz2)
        z2 = maxI(tz1, tz2)
    }

    init {
        compareValues()

    }

    fun toRectangle3D(): Rectangle3D {
        return Rectangle3D(x1.toDouble(), y1.toDouble(), z1.toDouble(), x2.toDouble(), y2.toDouble(), z2.toDouble())
    }

    fun getPoint1(): Vector3I {
        return Vector3I(x1, y1, z1)
    }

    fun getPoint2(): Vector3I {
        return Vector3I(x2, y2, z2)
    }

    fun isPointInside(point: Vector3D): Boolean {
        return point.x >= x1 && point.x <= x2 && point.y >= y1 && point.y <= y2 && point.z >= z1 && point.z <= z2
    }

    fun isPointInside(point: Vector3I): Boolean {
        return point.x >= x1 && point.x <= x2 && point.y >= y1 && point.y <= y2 && point.z >= z1 && point.z <= z2
    }
}