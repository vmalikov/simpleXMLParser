package com.justforfun.simplexml.core

import android.util.Log
import java.lang.reflect.Field

/**
 * Created by Vladimir on 6/7/17.
 */
class CleverField(var field: Field, var as_array: Boolean) {

    open fun type(): Class<*> {
        return field.type
    }

    open fun set(obj: Any?, value: Any?) {
        if(obj == null) throw IllegalArgumentException("object MUST be NOT null!")
        if(value == null) throw IllegalArgumentException("value MUST be NOT null!")

        field.isAccessible = true
        field.set(obj, value)
        field.isAccessible = false
    }

    fun <T> get(instance: T): Any? {
        if(instance == null) throw IllegalArgumentException("object MUST be NOT null!")

        field.isAccessible = true
        val result = field.get(instance)
        field.isAccessible = false
        return result
    }
}