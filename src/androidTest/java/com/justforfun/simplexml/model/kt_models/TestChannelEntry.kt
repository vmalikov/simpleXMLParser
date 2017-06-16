package com.justforfun.simplexml.model.kt_models

/**
 * Created by Vladimir on 6/16/17.
 */

@com.justforfun.simplexml.annotation.XmlName(name = "channel")
class TestChannelEntry {
    @com.justforfun.simplexml.annotation.XmlName(name = "title")
    var title: String? = null
    @com.justforfun.simplexml.annotation.XmlName(name = "description")
    var description: String? = null
    @com.justforfun.simplexml.annotation.XmlName(name = "link")
    var link: String? = null
    @com.justforfun.simplexml.annotation.XmlName(name = "lastBuildDate")
    var lastBuildDate: String? = null
    @com.justforfun.simplexml.annotation.XmlName(name = "image")
    var image: TestChannelImage? = null
    @com.justforfun.simplexml.annotation.XmlAsArray(name = "item")
    var items: java.util.ArrayList<TestFeedEntry>? = java.util.ArrayList<TestFeedEntry>()
}