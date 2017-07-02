package com.justforfun.simplexml.model.kt_models

import com.justforfun.simplexml.annotation.XmlName

/**
 * Created by Vladimir on 6/16/17.
 */
@XmlName("resources")
class TestResourcesEntryWithNumbers {
    @XmlName(name = "app_name") lateinit var app_name: String
    @XmlName(name = "app_version") var app_version: Int? = 0
    @XmlName("build_info") lateinit var buildInfo: TestBuildInfoWithNumbers
}

@XmlName("build_info")
class TestBuildInfoWithNumbers {
    @XmlName("app_code_version") var app_code_version: Int? = 0
    @XmlName("build_version") var build_version: Int? = 0
}