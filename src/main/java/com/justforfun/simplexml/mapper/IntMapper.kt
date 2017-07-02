package com.justforfun.simplexml.mapper

import android.util.Log
import org.xmlpull.v1.XmlPullParser

/**
 * Created by Vladimir on 6/8/17.
 */
open class IntMapper: XmlMapper<Int>() {
    override fun parse(parser: XmlPullParser): Int? {
        return parser.text.toInt();
    }
}