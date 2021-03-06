package com.teamwizardry.librarianlib.features.saving.serializers.builtin.generics

import com.teamwizardry.librarianlib.features.autoregister.SerializerFactoryRegister
import com.teamwizardry.librarianlib.features.kotlin.forEachIndexed
import com.teamwizardry.librarianlib.features.kotlin.readBooleanArray
import com.teamwizardry.librarianlib.features.kotlin.safeCast
import com.teamwizardry.librarianlib.features.kotlin.writeBooleanArray
import com.teamwizardry.librarianlib.features.methodhandles.MethodHandleHelper
import com.teamwizardry.librarianlib.features.saving.FieldType
import com.teamwizardry.librarianlib.features.saving.serializers.Serializer
import com.teamwizardry.librarianlib.features.saving.serializers.SerializerFactory
import com.teamwizardry.librarianlib.features.saving.serializers.SerializerFactoryMatch
import com.teamwizardry.librarianlib.features.saving.serializers.SerializerRegistry
import io.netty.buffer.ByteBuf
import net.minecraft.nbt.NBTBase
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTTagList

/**
 * Created by TheCodeWarrior
 */
@SerializerFactoryRegister
object SerializeListFactory : SerializerFactory("List") {
    override fun canApply(type: FieldType): SerializerFactoryMatch {
        return this.canApplySubclass(type, List::class.java)
    }

    override fun create(type: FieldType): Serializer<*> {
        return SerializeList(type, type.resolveGeneric(List::class.java, 0))
    }

    class SerializeList(type: FieldType, val generic: FieldType) : Serializer<MutableList<Any?>>(type) {

        val serGeneric: Serializer<Any> by SerializerRegistry.lazy(generic)
        val constructor = createConstructorMH()

        override fun readNBT(nbt: NBTBase, existing: MutableList<Any?>?, syncing: Boolean): MutableList<Any?> {
            val list = nbt.safeCast(NBTTagList::class.java)

            @Suppress("UNCHECKED_CAST")
            val array = (existing ?: constructor())

            while (array.size > list.tagCount())
                array.removeAt(array.size - 1)

            list.forEachIndexed<NBTTagCompound> { i, container ->
                val tag = container.getTag("-")
                val v = if (tag == null) null else serGeneric.read(tag, array.getOrNull(i), syncing)
                if (i >= array.size) {
                    array.add(v)
                } else {
                    array[i] = v
                }
            }

            return array
        }

        override fun writeNBT(value: MutableList<Any?>, syncing: Boolean): NBTBase {
            val list = NBTTagList()

            for (i in 0..value.size - 1) {
                val container = NBTTagCompound()
                list.appendTag(container)
                val v = value[i]
                if (v != null) {
                    container.setTag("-", serGeneric.write(v, syncing))
                }
            }

            return list
        }

        override fun readBytes(buf: ByteBuf, existing: MutableList<Any?>?, syncing: Boolean): MutableList<Any?> {
            val nullsig = buf.readBooleanArray()

            @Suppress("UNCHECKED_CAST")
            val array = (existing ?: constructor())

            while (array.size > nullsig.size)
                array.removeAt(array.size - 1)

            for (i in 0..nullsig.size - 1) {
                val v = if (nullsig[i]) null else serGeneric.read(buf, array.getOrNull(i), syncing)
                if (i >= array.size) {
                    array.add(v)
                } else {
                    array[i] = v
                }
            }
            return array
        }

        override fun writeBytes(buf: ByteBuf, value: MutableList<Any?>, syncing: Boolean) {
            val nullsig = BooleanArray(value.size) { value[it] == null }
            buf.writeBooleanArray(nullsig)

            (0..value.size - 1)
                    .filterNot { nullsig[it] }
                    .forEach { serGeneric.write(buf, value[it]!!, syncing) }
        }

        private fun createConstructorMH(): () -> MutableList<Any?> {
            if (type.clazz == List::class.java) {
                return { mutableListOf<Any?>() }
            } else {
                val mh =  MethodHandleHelper.wrapperForConstructor<MutableList<Any?>>(type.clazz)
                return { mh(arrayOf()) }
            }
        }
    }
}
