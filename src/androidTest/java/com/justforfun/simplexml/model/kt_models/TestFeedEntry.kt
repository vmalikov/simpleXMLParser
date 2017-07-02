package com.justforfun.simplexml.model.kt_models

import com.justforfun.simplexml.annotation.XmlName

/**
 * Created by Vladimir on 6/16/17.
 */
@com.justforfun.simplexml.annotation.XmlName(name = "item")
class TestFeedEntry {
    @com.justforfun.simplexml.annotation.XmlName(name = "title")
    var title: String? = null
    @com.justforfun.simplexml.annotation.XmlName(names = *arrayOf("content:encoded", "description"))
    var description: String? = null
    @com.justforfun.simplexml.annotation.XmlName(name = "link")
    var link: String? = null
    @com.justforfun.simplexml.annotation.XmlName(name = "pubDate")
    var pubDate: String? = null

}