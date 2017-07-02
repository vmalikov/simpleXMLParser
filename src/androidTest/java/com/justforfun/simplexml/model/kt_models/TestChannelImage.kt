package com.justforfun.simplexml.model.kt_models

import com.justforfun.simplexml.annotation.XmlName

/**
 * Created by Vladimir on 6/16/17.
 */

@com.justforfun.simplexml.annotation.XmlName(name = "image")
class TestChannelImage {
    @com.justforfun.simplexml.annotation.XmlName(name = "url")
    var url: String? = null
}