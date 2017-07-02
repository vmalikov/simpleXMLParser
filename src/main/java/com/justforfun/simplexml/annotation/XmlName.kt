package com.justforfun.simplexml.annotation

/**
 * Created by Vladimir on 6/7/17.
 */

@Target(AnnotationTarget.CLASS, AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class XmlName(val name: String = "", vararg val names: String = arrayOf<String>())