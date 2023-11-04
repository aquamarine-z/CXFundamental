package cxplugins.cxfundamental.minecraft.math.geometry

class Vector2D(var x: Double, var y: Double) {
    fun toVector2I(): Vector2I {
        return Vector2I(x.toInt(), y.toInt())
    }

    operator fun minus(another: Vector2D): Vector2D {
        val vector = Vector2D(x, y)
        vector.x -= another.x
        vector.y -= another.y
        return vector
    }

    operator fun minusAssign(another: Vector2D) {
        val vector = Vector2D(x, y)
        vector.x -= another.x
        vector.y -= another.y
        this.x = vector.x
        this.y = vector.y
    }

    operator fun plus(another: Vector2D): Vector2D {
        val vector = Vector2D(x, y)
        vector.x += another.x
        vector.y += another.y
        return vector
    }

    operator fun plusAssign(another: Vector2D) {
        val vector = Vector2D(x, y)
        vector.x += another.x
        vector.y += another.y
        this.x = vector.x
        this.y = vector.y
    }
}

class Vector2I(var x: Int, var y: Int) {
    fun toVector2D(): Vector2D {
        return Vector2D(x.toDouble(), y.toDouble())
    }

    operator fun minus(another: Vector2I): Vector2I {
        val vector = Vector2I(x, y)
        vector.x -= another.x
        vector.y -= another.y
        return vector
    }

    operator fun minusAssign(another: Vector2I) {
        val vector = Vector2I(x, y)
        vector.x -= another.x
        vector.y -= another.y
        this.x = vector.x
        this.y = vector.y
    }

    operator fun plus(another: Vector2I): Vector2I {
        val vector = Vector2I(x, y)
        vector.x += another.x
        vector.y += another.y
        return vector
    }

    operator fun plusAssign(another: Vector2I) {
        val vector = Vector2I(x, y)
        vector.x += another.x
        vector.y += another.y
        this.x = vector.x
        this.y = vector.y
    }
}