package com.justforfun.simplexml.core

import android.text.TextUtils
import android.util.Log
import com.justforfun.simplexml.annotation.XmlAsArray
import com.justforfun.simplexml.annotation.XmlName
import com.justforfun.simplexml.mapper.XmlMapper
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParser.*
import org.xmlpull.v1.XmlPullParserFactory
import java.io.InputStream
import java.lang.reflect.ParameterizedType

/**
 * Created by Vladimir on 6/7/17.
 */
open class SimpleParser {
    val TAG = SimpleParser::class.java.name

    var mappers: HashMap<Class<*>, XmlMapper<*>>? = null

    var inited = false;

    companion object {
        open fun <T> parse(input: InputStream?, clazz: Class<T>): T? {
            return SimpleParser().parse<T>(input, clazz)
        }
    }

    fun init() {
        if (inited) return
        inited = true;

        mappers = XmlMapper.Companion.getMappers()
    }

    /**
     * Entry point which wrap {@link java.io.InputStream} with {@link org.xmlpull.v1.XmlPullParser}.
     *
     * @param input - InputStream with XML data
     * @param clazz - XML data should be parsed to an instance of this {@link java.lang.Class} object
     * @return instance of param clazz
     */
    fun <T> parse(input: InputStream?, clazz: Class<T>): T? {
        init()

        val factory = XmlPullParserFactory.newInstance()
        val parser = factory.newPullParser()
        parser.setInput(input, null)

        Log.i(TAG, "start parsing")
        val instance = parseFor(parser, clazz)
        Log.i(TAG, "finish parsing " + instance.toString())

        return instance
    }

    fun <T> parseFor(parser: XmlPullParser, clazz: Class<T>): T? {
        val targetTagName = targetTagName(clazz) ?: throw NullPointerException("Mark target class with annotation XmlName(name)")
        val fields = getFieldsMap(clazz)

        var eventType = parser.eventType
        while (eventType != END_DOCUMENT) {
            when (eventType) {
                START_TAG -> {
                    Log.i(TAG, "${parser.depth} start tag " + parser.name)
                    if (TextUtils.equals(parser.name, targetTagName)) {
                        return parseFor(parser, targetTagName, fields, clazz)
                    }
                }
                TEXT -> Log.i(TAG, "${parser.depth} text " + parser.text)
                END_TAG -> Log.i(TAG, "${parser.depth} end tag " + parser.name)

            }
            eventType = parser.next()
        }

        return null
    }

    fun <T> parseFor(parser: XmlPullParser, oldTargetName: String, fields: Map<String, CleverField>, clazz: Class<T>): T {
        val instance = clazz.newInstance()

        parser.next()

        var m: XmlMapper<*>? = null
        var f: CleverField? = null

        var eventType = parser.eventType
        while (!(eventType == END_TAG && TextUtils.equals(parser.name, oldTargetName))) {

            val name = parser.name
            when (eventType) {
                START_TAG -> {
                    Log.i(TAG, "${parser.depth} start tag " + name)
                    f = fields.get(name)
                    if (f != null) {
                        if (f.as_array) {
                            var typeArguments = (f.field.genericType as ParameterizedType).actualTypeArguments
                            if(typeArguments != null && typeArguments.size > 0) {
                                var stringClassName = (typeArguments.get(0) as Class<*>).name
                                var type = Class.forName(stringClassName)

                                val localFields = getFieldsMap(type)
                                val localInstance = parseFor(parser, name, localFields, type)

                                val addMethod = f.type().getDeclaredMethod("add", Object::class.java)
                                addMethod.isAccessible = true
                                addMethod.invoke(f.get(instance), localInstance)
                            }
                        } else {
                            val type = f.type()
                            if (type.isPrimitive || type == String::class.java) {
                                m = mappers?.get(type)
                            } else {
                                val localFields = getFieldsMap(type)
                                val localInstance = parseFor(parser, name, localFields, type)
                                f.set(instance, localInstance)
                            }
                        }
                    } else {
                        Log.i(TAG, "${parser.depth} wan't able to find field for " + name)
                    }
                }
                END_TAG -> Log.i(TAG, "${parser.depth} end tag " + name)
                TEXT -> {
                    Log.i(TAG, "${parser.depth} text " + parser.text)
                    val value = m?.parse(parser)
                    if (value == null) {
                        Log.i(TAG, "${parser.depth} text is empty!")
                    } else {
                        f?.set(instance, value)
                    }
                    f = null;
                    m = null;
                }
            }
            eventType = parser.next()
        }

        return instance
    }

    fun <T> targetTagName(clazz: Class<T>): String? {
        if (clazz.isAnnotationPresent(XmlName::class.java)) {
            val annotation = clazz.getAnnotation(XmlName::class.java)
            return annotation.name
        }
        return null
    }

    /**
     * @param clazz represents type with fields which will be stored to HashMap
     * @return Map with name in annotation as key and field itself as value
     */
    fun <T> getFieldsMap(clazz: Class<T>): Map<String, CleverField> {
        val fieldsMap = HashMap<String, CleverField>()

        for (field in clazz.declaredFields) {
            if (field.isAnnotationPresent(XmlName::class.java)) {
                val annotation = field.getAnnotation(XmlName::class.java)
                fieldsMap.put(annotation.name, CleverField(field, false))

            } else if (field.isAnnotationPresent(XmlAsArray::class.java)) {
                val annotation = field.getAnnotation(XmlAsArray::class.java)
                // FIXME: add ability to mark if item should be stared in array
                fieldsMap.put(annotation.name, CleverField(field, true))
            }
        }

        return fieldsMap;
    }
}

/*
        var eventType = parser.eventType
        while (eventType != END_DOCUMENT) {
            when (eventType) {
                START_DOCUMENT -> Log.i(TAG, "start document")
                START_TAG -> Log.i(TAG, "start tag " + parser.name)
                END_TAG -> Log.i(TAG, "end tag " + parser.name)
                TEXT -> Log.i(TAG, "text "+ parser.text)
                END_DOCUMENT -> Log.i(TAG, "end document")
            }
            eventType = parser.next()
        }
        */