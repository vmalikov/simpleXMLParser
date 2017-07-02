package com.justforfun.simplexml.model.kt_models

import com.justforfun.simplexml.annotation.XmlName

/**
 * Created by Vladimir on 6/16/17.
 */
@XmlName(name = "item")
data class TestFeedEntryConstructorParams(@XmlName(name = "title") var title: String = "",
    @XmlName(names = *arrayOf("content:encoded", "description")) var description: String = "",
    @XmlName(name = "link") var link: String = "",
    @XmlName(name = "pubDate") var pubDate: String = "")

