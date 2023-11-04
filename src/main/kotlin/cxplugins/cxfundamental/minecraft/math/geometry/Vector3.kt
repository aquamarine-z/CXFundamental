package cxplugins.cxfundamental.minecraft.math.geometry

class Vector3D(var x: Double, var y: Double, var z: Double) {
    fun toVector3I(): Vector3I {
        return Vector3I(x.toInt(), y.toInt(), z.toInt())
    }

    operator fun minus(another: Vector3D): Vector3D {
        val vector = Vector3D(x, y, z)
        vector.x -= another.x
        vector.y -= another.y
        vector.z -= another.z
        return vector
    }

    operator fun minusAssign(another: Vector3D) {
        val vector = Vector3D(x, y, z)
        vector.x -= another.x
        vector.y -= another.y
        vector.z -= another.z
        this.x = vector.x
        this.y = vector.y
        this.z = vector.z
    }

    operator fun plus(another: Vector3D): Vector3D {
        val vector = Vector3D(x, y, z)
        vector.x += another.x
        vector.y += another.y
        vector.z += another.z
        return vector
    }

    operator fun plusAssign(another: Vector3D) {
        val vector = Vector3D(x, y, z)
        vector.x += another.x
        vector.y += another.y
        vector.z += another.z
        this.x = vector.x
        this.y = vector.y
        this.z = vector.z
    }
}

class Vector3I(var x: Int, var y: Int, var z: Int) {
    fun toVector3D(): Vector3D {
        return Vector3D(x.toDouble(), y.toDouble(), z.toDouble())
    }

    operator fun minus(another: Vector3I): Vector3I {
        val vector = Vector3I(x, y, z)
        vector.x -= another.x
        vector.y -= another.y
        vector.z -= another.z
        return vector
    }

    operator fun minusAssign(another: Vector3I) {
        val vector = Vector3I(x, y, z)
        vector.x -= another.x
        vector.y -= another.y
        vector.z -= another.z
        this.x = vector.x
        this.y = vector.y
        this.z = vector.z
    }

    operator fun plus(another: Vector3I): Vector3I {
        val vector = Vector3I(x, y, z)
        vector.x += another.x
        vector.y += another.y
        vector.z += another.z
        return vector
    }

    operator fun plusAssign(another: Vector3I) {
        val vector = Vector3I(x, y, z)
        vector.x += another.x
        vector.y += another.y
        vector.z += another.z
        this.x = vector.x
        this.y = vector.y
        this.z = vector.z
    }
}