package com.justforfun.simplexml.annotation

/**
 * Created by Vladimir on 6/8/17.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class XmlAsArray (val name: String)