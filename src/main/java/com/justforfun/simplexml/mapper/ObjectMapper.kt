package com.justforfun.simplexml.mapper

import android.util.Log
import org.xmlpull.v1.XmlPullParser

/**
 * Created by Vladimir on 6/8/17.
 */
class ObjectMapper: XmlMapper<Any>() {
    override fun parse(parser: XmlPullParser): Any? {
        val result = super.parse(parser);
        Log.i("XmlMapper", javaClass.name + " parsed: $result");
        return result
    }
}