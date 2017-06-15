package com.justforfun.simplexml.mapper

import android.util.Log
import org.xmlpull.v1.XmlPullParser

/**
 * Created by Vladimir on 6/8/17.
 */
class StringMapper: XmlMapper<String>() {
    override fun parse(parser: XmlPullParser): String {
        val result = parser.text;
        Log.i("XmlMapper", javaClass.name + " parsed: $result");
        return result
    }
}