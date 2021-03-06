package com.teamwizardry.librarianlib.features.saving.serializers.builtin.basics

import com.teamwizardry.librarianlib.features.autoregister.SerializerRegister
import com.teamwizardry.librarianlib.features.kotlin.safeCast
import com.teamwizardry.librarianlib.features.math.Vec2d
import com.teamwizardry.librarianlib.features.saving.FieldType
import com.teamwizardry.librarianlib.features.saving.serializers.Serializer
import io.netty.buffer.ByteBuf
import net.minecraft.nbt.NBTBase
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.util.math.Vec3i

/**
 * Created by TheCodeWarrior
 */

@SerializerRegister(Vec3d::class)
object SerializeVec3d : Serializer<Vec3d>(FieldType.create(Vec3d::class.java)) {
    override fun readNBT(nbt: NBTBase, existing: Vec3d?, syncing: Boolean): Vec3d {
        val tag = nbt.safeCast<NBTTagCompound>()
        return Vec3d(tag.getDouble("x"), tag.getDouble("y"), tag.getDouble("z"))
    }

    override fun writeNBT(value: Vec3d, syncing: Boolean): NBTBase {
        val tag = NBTTagCompound()
        tag.setDouble("x", value.xCoord)
        tag.setDouble("y", value.yCoord)
        tag.setDouble("z", value.zCoord)
        return tag
    }

    override fun readBytes(buf: ByteBuf, existing: Vec3d?, syncing: Boolean): Vec3d {
        return Vec3d(buf.readDouble(), buf.readDouble(), buf.readDouble())
    }

    override fun writeBytes(buf: ByteBuf, value: Vec3d, syncing: Boolean) {
        buf.writeDouble(value.xCoord)
        buf.writeDouble(value.yCoord)
        buf.writeDouble(value.zCoord)
    }
}

@SerializerRegister(Vec3i::class)
object SerializeVec3i : Serializer<Vec3i>(FieldType.create(Vec3i::class.java)) {
    override fun readNBT(nbt: NBTBase, existing: Vec3i?, syncing: Boolean): Vec3i {
        val tag = nbt.safeCast(NBTTagCompound::class.java)
        return Vec3i(tag.getInteger("x"), tag.getInteger("y"), tag.getInteger("z"))
    }

    override fun writeNBT(value: Vec3i, syncing: Boolean): NBTBase {
        val tag = NBTTagCompound()
        tag.setInteger("x", value.x)
        tag.setInteger("y", value.y)
        tag.setInteger("z", value.z)
        return tag
    }

    override fun readBytes(buf: ByteBuf, existing: Vec3i?, syncing: Boolean): Vec3i {
        return Vec3i(buf.readInt(), buf.readInt(), buf.readInt())
    }

    override fun writeBytes(buf: ByteBuf, value: Vec3i, syncing: Boolean) {
        buf.writeInt(value.x)
        buf.writeInt(value.y)
        buf.writeInt(value.z)
    }
}

@SerializerRegister(BlockPos::class)
object SerializeBlockPos : Serializer<BlockPos>(FieldType.create(BlockPos::class.java)) {
    override fun readNBT(nbt: NBTBase, existing: BlockPos?, syncing: Boolean): BlockPos {
        val tag = nbt.safeCast(NBTTagCompound::class.java)
        return BlockPos(tag.getInteger("x"), tag.getInteger("y"), tag.getInteger("z"))
    }

    override fun writeNBT(value: BlockPos, syncing: Boolean): NBTBase {
        val tag = NBTTagCompound()
        tag.setInteger("x", value.x)
        tag.setInteger("y", value.y)
        tag.setInteger("z", value.z)
        return tag
    }

    override fun readBytes(buf: ByteBuf, existing: BlockPos?, syncing: Boolean): BlockPos {
        return BlockPos(buf.readInt(), buf.readInt(), buf.readInt())
    }

    override fun writeBytes(buf: ByteBuf, value: BlockPos, syncing: Boolean) {
        buf.writeInt(value.x)
        buf.writeInt(value.y)
        buf.writeInt(value.z)
    }
}

@SerializerRegister(Vec2d::class)
object SerializeVec2d : Serializer<Vec2d>(FieldType.create(Vec2d::class.java)) {
    override fun readNBT(nbt: NBTBase, existing: Vec2d?, syncing: Boolean): Vec2d {
        val tag = nbt.safeCast(NBTTagCompound::class.java)
        return Vec2d(tag.getDouble("x"), tag.getDouble("y"))
    }

    override fun writeNBT(value: Vec2d, syncing: Boolean): NBTBase {
        val tag = NBTTagCompound()
        tag.setDouble("x", value.x)
        tag.setDouble("y", value.y)
        return tag
    }

    override fun readBytes(buf: ByteBuf, existing: Vec2d?, syncing: Boolean): Vec2d {
        return Vec2d(buf.readDouble(), buf.readDouble())
    }

    override fun writeBytes(buf: ByteBuf, value: Vec2d, syncing: Boolean) {
        buf.writeDouble(value.x)
        buf.writeDouble(value.y)
    }
}
