package com.justforfun.simplexml.mapper

import android.util.Log
import org.xmlpull.v1.XmlPullParser

/**
 * Created by Vladimir on 6/8/17.
 */
open class DoubleMapper: XmlMapper<Double>() {
    override fun parse(parser: XmlPullParser): Double? {
        return parser.text.toDouble();
    }
}