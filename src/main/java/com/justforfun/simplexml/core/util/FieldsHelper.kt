package com.justforfun.simplexml.core.util

import android.text.TextUtils
import com.justforfun.simplexml.annotation.XmlAsArray
import com.justforfun.simplexml.annotation.XmlName
import com.justforfun.simplexml.core.CleverField
import java.lang.reflect.Field

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
          processWithXmlNameAnnotation(field, fieldsMap)
        } else if (field.isAnnotationPresent(XmlAsArray::class.java)) {
          processWithXmlAsArrayAnnotation(field, fieldsMap)
        }
      }

      return fieldsMap
    }

    private fun processWithXmlNameAnnotation(field: Field, fieldsMap: HashMap<String, CleverField>) {
      val annotation = field.getAnnotation(XmlName::class.java)

      addToMapIfNeed(annotation.name, field, fieldsMap)

      for (it in annotation.names) {
        addToMapIfNeed(it, field, fieldsMap)
      }
    }

    private fun processWithXmlAsArrayAnnotation(field: Field, fieldsMap: HashMap<String, CleverField>) {
      val annotation = field.getAnnotation(XmlAsArray::class.java)
      fieldsMap.put(annotation.name, CleverField(field, true))
    }

    private fun addToMapIfNeed(it: String, field: Field, fieldsMap: HashMap<String, CleverField>) {
      if (!TextUtils.isEmpty(it)) {
        fieldsMap.put(it, CleverField(field))
      }
    }
  }
}