package com.teamwizardry.librarianlib.math

class Vec2d(var x: Double, var y: Double) {

    val xf: Float
    val yf: Float
    val xi: Int
    val yi: Int

    //=============================================================================
    init {/* Simple math */
    }

    //=============================================================================
    init {/* Advanced math */
    }

    //=============================================================================
    init {/* Static */
    }

    //=============================================================================
    init {/* Boring object stuff */
    }

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

    fun sub(other: Vec2d): Vec2d {
        return Vec2d(x - other.x, y - other.y)
    }
    //=============================================================================

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

    fun dot(point: Vec2d): Double {
        return x * point.x + y * point.y
    }

    fun length(): Double {
        return Math.sqrt(x * x + y * y)
    }
    //=============================================================================

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
        var other = other
        other = other.normalize()
        return other.mul(this.dot(other))
    }
    //=============================================================================

    override fun hashCode(): Int {
        val prime = 31
        var result = 1
        var temp: Long
        temp = java.lang.Double.doubleToLongBits(x)
        result = prime * result + (temp xor temp.ushr(32)).toInt()
        temp = java.lang.Double.doubleToLongBits(y)
        result = prime * result + (temp xor temp.ushr(32)).toInt()
        return result
    }

    override fun equals(obj: Any?): Boolean {
        if (this === obj)
            return true
        if (obj == null)
            return false
        if (javaClass != obj.javaClass)
            return false
        val other = obj as Vec2d?
        if (java.lang.Double.doubleToLongBits(x) != java.lang.Double.doubleToLongBits(other!!.x))
            return false
        return java.lang.Double.doubleToLongBits(y) == java.lang.Double.doubleToLongBits(other.y)
    }

    override fun toString(): String {
        return "($x,$y)"
    }

    companion object {

        val ZERO = Vec2d(0.0, 0.0)

        val ONE = Vec2d(1.0, 1.0)
        val X = Vec2d(1.0, 0.0)
        val Y = Vec2d(0.0, 1.0)

        val NEG_ONE = Vec2d(-1.0, -1.0)
        val NEG_X = Vec2d(-1.0, 0.0)
        val NEG_Y = Vec2d(0.0, -1.0)

        fun min(a: Vec2d, b: Vec2d): Vec2d {
            return Vec2d(Math.min(a.x, b.x), Math.min(a.y, b.y))
        }
        //=============================================================================

        fun max(a: Vec2d, b: Vec2d): Vec2d {
            return Vec2d(Math.max(a.x, b.x), Math.max(a.y, b.y))
        }
    }
}