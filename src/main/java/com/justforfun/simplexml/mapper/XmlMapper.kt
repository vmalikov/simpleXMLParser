package com.justforfun.simplexml.mapper

import org.xmlpull.v1.XmlPullParser

/**
 * Created by Vladimir on 6/8/17.
 */
open class XmlMapper<T> {
    open fun parse(parser: XmlPullParser): T? {
        return null
    }

    companion object {
        // basic mappers
        open fun getMappers(): HashMap<Class<*>, XmlMapper<*>> {
            val mappers = HashMap<Class<*>, XmlMapper<*>>()

            mappers.put(Object::class.java, ObjectMapper())
            mappers.put(String::class.java, StringMapper())
            mappers.put(Int::class.java, IntMapper())
            mappers.put(Byte::class.java, ByteMapper())
            mappers.put(Short::class.java, ShortMapper())
            mappers.put(Float::class.java, FloatMapper())
            mappers.put(Double::class.java, DoubleMapper())
            mappers.put(Boolean::class.java, BooleanMapper())
            return mappers
        }
    }
}