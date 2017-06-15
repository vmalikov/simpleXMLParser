package com.justforfun.simplexml.annotation

/**
 * Created by Vladimir on 6/7/17.
 */

@Target(AnnotationTarget.CLASS, AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class XmlName (val name: String)