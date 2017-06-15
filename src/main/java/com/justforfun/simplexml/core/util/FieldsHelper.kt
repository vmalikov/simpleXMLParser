package com.justforfun.simplexml.core.util

import com.justforfun.simplexml.annotation.XmlAsArray
import com.justforfun.simplexml.annotation.XmlName
import com.justforfun.simplexml.core.CleverField

/**
 * Created by Vladimir on 6/15/17.
 */
class FieldsHelper {

    companion object {

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
                    fieldsMap.put(annotation.name, CleverField(field, true))
                }
            }

            return fieldsMap
        }

        fun isPrimitive(type: Class<*>): Boolean = type.isPrimitive || type == String::class.java
    }
}