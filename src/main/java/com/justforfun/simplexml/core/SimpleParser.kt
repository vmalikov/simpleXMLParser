package com.justforfun.simplexml.core

import android.text.TextUtils
import com.justforfun.simplexml.core.util.FieldsHelper
import com.justforfun.simplexml.mapper.XmlMapper
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParser.*
import org.xmlpull.v1.XmlPullParserFactory
import java.io.InputStream

/**
 * Created by Vladimir on 6/7/17.
 */
open class SimpleParser {

    var mappers: HashMap<Class<*>, XmlMapper<*>>? = XmlMapper.getMappers()

    /**
     * Entry point which wrap {@link java.io.InputStream} with {@link org.xmlpull.v1.XmlPullParser}.
     *
     * @param input - InputStream with XML data
     * @param clazz - XML data should be parsed to an instance of this {@link java.lang.Class} object
     * @return instance of param clazz
     */
    fun <T> parse(input: InputStream?, clazz: Class<T>): T? {

        val factory = XmlPullParserFactory.newInstance()
        val parser = factory.newPullParser()
        parser.setInput(input, null)

        return parseFor(parser, clazz)
    }

    fun <T> parseFor(parser: XmlPullParser, clazz: Class<T>): T? {
        val targetTagName = FieldsHelper.targetTagName(clazz) ?: throw NullPointerException("Mark target class with annotation XmlName(name)")
        val fields = FieldsHelper.getFieldsMap(clazz)

        var eventType = parser.eventType
        while (eventType != END_DOCUMENT) {
            when (eventType) {
                START_TAG -> {

                    if (TextUtils.equals(parser.name, targetTagName)) {
                        return parseFor(parser, targetTagName, fields, clazz)
                    }
                }
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
                    fields[name]?.let { field ->
                        f = field

                        if(field.isPrimitive()) {
                            m = mappers?.get(field.type())
                        } else {
                            setValueToField(field.type(), parser, name, field, instance)
                        }
                    }
                }
                TEXT -> {
                    m?.parse(parser)?.let { value ->
                        f?.setValue(instance, value)
                        f = null;
                        m = null;
                    }
                }
            }
            eventType = parser.next()
        }

        return instance
    }

    private fun <T> setValueToField(type: Class<*>, parser: XmlPullParser, name: String,
                                    field: CleverField, instance: T?) {

        val localFields = FieldsHelper.getFieldsMap(type)
        val localInstance = parseFor(parser, name, localFields, type)

        field.setValue(instance, localInstance)
    }
}