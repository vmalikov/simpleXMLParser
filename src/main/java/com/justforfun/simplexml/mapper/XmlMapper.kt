package com.justforfun.simplexml.mapper

import org.xmlpull.v1.XmlPullParser

/**
 * Created by Vladimir on 6/8/17.
 */
open class XmlMapper<out T> {
    open fun parse(parser: XmlPullParser): T? {
        return null
    }

    companion object {
        // basic mappers
        open fun getMappers(): HashMap<Class<*>, XmlMapper<*>> {
            val mappers = HashMap<Class<*>, XmlMapper<*>>()

            mappers.put(Object::class.java, ObjectMapper())
            mappers.put(String::class.java, StringMapper())

            val byteMapper = ByteMapper()
            mappers.put(Byte::class.java, byteMapper)
            mappers.put(java.lang.Byte::class.java, byteMapper)

            val shortMapper = ShortMapper()
            mappers.put(Short::class.java, shortMapper)
            mappers.put(java.lang.Short::class.java, shortMapper)

            val intMapper = IntMapper()
            mappers.put(Int::class.java, intMapper)
            mappers.put(Integer::class.java, intMapper)

            val longMapper = LongMapper()
            mappers.put(Long::class.java, longMapper)
            mappers.put(java.lang.Long::class.java, longMapper)

            val floatMapper = FloatMapper()
            mappers.put(Float::class.java, floatMapper)
            mappers.put(java.lang.Float::class.java, floatMapper)

            val doubleMapper = DoubleMapper()
            mappers.put(Double::class.java, doubleMapper)
            mappers.put(java.lang.Double::class.java, doubleMapper)

            val booleanMapper = BooleanMapper()
            mappers.put(Boolean::class.java, booleanMapper)
            mappers.put(java.lang.Boolean::class.java, booleanMapper)

            return mappers
        }
    }
}