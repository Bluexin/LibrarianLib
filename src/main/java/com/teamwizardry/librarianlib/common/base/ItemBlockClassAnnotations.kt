package com.teamwizardry.librarianlib.common.base

import com.teamwizardry.librarianlib.LibrarianLib
import com.teamwizardry.librarianlib.common.base.block.BlockMod
import com.teamwizardry.librarianlib.common.base.item.ItemMod
import com.teamwizardry.librarianlib.common.util.AnnotationHelper
import net.minecraft.block.Block
import net.minecraft.item.Item

/**
 * Created by Elad on 1/21/2017.
 */

/**
 * Apply this annotation to classes and their fields will automatically be initialized with
 * a new instance of the type they hold. This is to be used with Item and Block classes.
 * In case the resources are [Block]s or [Item]s, making them [BlockMod]s or
 * [ItemMod]s respectively will allow them to automatically be registered as needed
 * when populated to a resource class.
 * This annotation will only work on classes with fields with types with constructors
 * that only take one argument.
 * This annotation ignores fields makred as [Ignored].
 */
@Target(AnnotationTarget.CLASS)
annotation class ResourceClass

/**
 * Apply this annotation to fields on classes marked as [ResourceClass] in order
 * to have it ignored by the annotation.
 */
@Target(AnnotationTarget.FIELD)
annotation class Ignored

object ItemBlockClassAnnotationsHandler {
    init {
        AnnotationHelper.findAnnotatedClasses(LibrarianLib.PROXY.asmDataTable, Object::class.java, ResourceClass::class.java) {
            clazz, info ->
            clazz.declaredFields.filter { it.annotations.none { it is Ignored } }.forEach {
                try {
                    it.set(clazz.kotlin.objectInstance, it.type.newInstance())
                } catch(e: IllegalAccessException) {
                    throw RuntimeException(it.toString(), e)
                }
                Class.forName(it.type.name)
            }
        }
    }
}
