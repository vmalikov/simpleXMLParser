package com.justforfun.simplexml.core

import java.lang.reflect.Field
import java.lang.reflect.ParameterizedType

/**
 * Created by Vladimir on 6/7/17.
 */
class CleverField(var field: Field, var as_array: Boolean) {

    fun setValue(obj: Any?, value: Any?) {
        if (as_array) {
            val addMethod = field.type.getDeclaredMethod("add", Object::class.java)
            addMethod.isAccessible = true
            addMethod.invoke(get(obj), value)

        } else {
            set(obj, value)
        }
    }

    fun isPrimitive(): Boolean {
        return type().isPrimitive
                || type() == String::class.java

                || type() == Byte::class.java
                || type() == java.lang.Byte::class.java

                || type() == Short::class.java
                || type() == java.lang.Short::class.java

                || type() == Integer::class.java
                || type() == Int::class.java

                || type() == Long::class.java
                || type() == java.lang.Long::class.java

                || type() == Float::class.java
                || type() == java.lang.Float::class.java

                || type() == Double::class.java
                || type() == java.lang.Double::class.java

                || type() == Boolean::class.java
                || type() == java.lang.Boolean::class.java
    }

    fun type(): Class<*> {
        return if (as_array) getGenericType() else field.type
    }

    fun getGenericType(): Class<*> {
        getStringClassName()?.let { stringClassName -> return Class.forName(stringClassName) }
        throw IllegalArgumentException("Can not load class for ${field.genericType}")
    }

    private fun set(obj: Any?, value: Any?) {
        if (obj == null) throw IllegalArgumentException("object MUST be NOT null!")
        if (value == null) throw IllegalArgumentException("value MUST be NOT null!")

        field.isAccessible = true
        field.set(obj, value)
        field.isAccessible = false
    }

    private fun <T> get(instance: T): Any? {
        if (instance == null) throw IllegalArgumentException("object MUST be NOT null!")

        field.isAccessible = true
        val result = field.get(instance)
        field.isAccessible = false
        return result
    }

    private fun getStringClassName(): String? {
        val typeArguments = (field.genericType as ParameterizedType).actualTypeArguments
        if (typeArguments != null && typeArguments.isNotEmpty()) {
            return (typeArguments.get(0) as Class<*>).name
        } else {
            return null
        }
    }
}