package com.justforfun.simplexml

import com.justforfun.simplexml.core.SimpleParser
import java.io.InputStream

/**
 * Created by Vladimir on 6/15/17.
 */
class SimpleXmlParser {
    companion object {
        fun <T> parse(input: InputStream?, clazz: Class<T>): T? {
            return SimpleParser().parse<T>(input, clazz)
        }

        fun <T> parse(input: String?, clazz: Class<T>): T? {
            return SimpleParser().parse<T>(input, clazz)
        }
    }
}