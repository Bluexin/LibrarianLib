package com.teamwizardry.librarianlib.common.util.math

data class Vec2d(val x: Double, val y: Double) {

    constructor(x: Float, y: Float) : this(x.toDouble(), y.toDouble())
    constructor(x: Int, y: Int) : this(x.toDouble(), y.toDouble())

    val xf: Float
    val yf: Float
    val xi: Int
    val yi: Int

    init {
        this.xf = x.toFloat()
        this.yf = y.toFloat()
        this.xi = Math.floor(x).toInt()
        this.yi = Math.floor(y).toInt()
    }

    fun floor(): Vec2d {
        return Vec2d(Math.floor(x), Math.floor(y))
    }

    fun ceil(): Vec2d {
        return Vec2d(Math.ceil(x), Math.ceil(y))
    }

    fun setX(value: Double): Vec2d {
        return Vec2d(value, y)
    }

    fun setY(value: Double): Vec2d {
        return Vec2d(x, value)
    }

    fun add(other: Vec2d): Vec2d {
        return Vec2d(x + other.x, y + other.y)
    }

    fun add(otherX: Double, otherY: Double): Vec2d {
        return Vec2d(x + otherX, y + otherY)
    }

    operator fun minus(other: Vec2d) = this.sub(other)
    fun sub(other: Vec2d): Vec2d {
        return Vec2d(x - other.x, y - other.y)
    }

    fun sub(otherX: Double, otherY: Double): Vec2d {
        return Vec2d(x - otherX, y - otherY)
    }

    fun mul(other: Vec2d): Vec2d {
        return Vec2d(x * other.x, y * other.y)
    }

    fun mul(otherX: Double, otherY: Double): Vec2d {
        return Vec2d(x * otherX, y * otherY)
    }

    fun mul(amount: Double): Vec2d {
        return Vec2d(x * amount, y * amount)
    }

    fun divide(other: Vec2d): Vec2d {
        return Vec2d(x / other.x, y / other.y)
    }

    fun divide(otherX: Double, otherY: Double): Vec2d {
        return Vec2d(x / otherX, y / otherY)
    }

    fun divide(amount: Double): Vec2d {
        return Vec2d(x / amount, y / amount)
    }

    infix fun dot(point: Vec2d): Double {
        return x * point.x + y * point.y
    }

    fun length(): Double {
        return Math.sqrt(x * x + y * y)
    }

    fun normalize(): Vec2d {
        val norm = length()
        return Vec2d(x / norm, y / norm)
    }

    fun squareDist(vec: Vec2d): Double {
        val d0 = vec.x - x
        val d1 = vec.y - y
        return d0 * d0 + d1 * d1
    }

    fun projectOnTo(other: Vec2d): Vec2d {
        val norm = other.normalize()
        return norm.mul(this.dot(norm))
    }
    //=============================================================================

    override fun toString(): String {
        return "($x,$y)"
    }

    companion object {

        @JvmField val ZERO = Vec2d(0.0, 0.0)
        @JvmField val INFINITY = Vec2d(Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY)
        @JvmField val NEG_INFINITY = Vec2d(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY)

        @JvmField val ONE = Vec2d(1.0, 1.0)
        @JvmField val X = Vec2d(1.0, 0.0)
        @JvmField val Y = Vec2d(0.0, 1.0)
        @JvmField val X_INFINITY = Vec2d(Double.POSITIVE_INFINITY, 0.0)
        @JvmField val Y_INFINITY = Vec2d(0.0, Double.POSITIVE_INFINITY)

        @JvmField val NEG_ONE = Vec2d(-1.0, -1.0)
        @JvmField val NEG_X = Vec2d(-1.0, 0.0)
        @JvmField val NEG_Y = Vec2d(0.0, -1.0)
        @JvmField val NEG_X_INFINITY = Vec2d(Double.NEGATIVE_INFINITY, 0.0)
        @JvmField val NEG_Y_INFINITY = Vec2d(0.0, Double.NEGATIVE_INFINITY)

        @JvmStatic
        fun min(a: Vec2d, b: Vec2d): Vec2d {
            return Vec2d(Math.min(a.x, b.x), Math.min(a.y, b.y))
        }

        @JvmStatic
        fun max(a: Vec2d, b: Vec2d): Vec2d {
            return Vec2d(Math.max(a.x, b.x), Math.max(a.y, b.y))
        }
    }
}
