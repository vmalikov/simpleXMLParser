package com.justforfun.simplexml.model.kt_models

/**
 * Created by Vladimir on 6/16/17.
 */
@com.justforfun.simplexml.annotation.XmlName("resources")
class TestResourcesEntry {
    @com.justforfun.simplexml.annotation.XmlName(name = "app_name") lateinit var app_name: String
    @com.justforfun.simplexml.annotation.XmlName(name = "app_version") lateinit var app_version: String
    @com.justforfun.simplexml.annotation.XmlName("build_info") lateinit var buildInfo: com.justforfun.simplexml.model.kt_models.TestBuildInfo
}

@com.justforfun.simplexml.annotation.XmlName("build_info")
class TestBuildInfo {
    @com.justforfun.simplexml.annotation.XmlName("app_code_version") lateinit var app_code_version: String
    @com.justforfun.simplexml.annotation.XmlName("build_version") lateinit var build_version: String
}