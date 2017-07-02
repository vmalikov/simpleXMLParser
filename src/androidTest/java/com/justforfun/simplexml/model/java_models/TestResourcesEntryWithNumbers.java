package com.justforfun.simplexml.model.java_models;

import com.justforfun.simplexml.annotation.XmlName;

/**
 * Created by Vladimir on 6/16/17.
 */
@XmlName(name = "resources")
public class TestResourcesEntryWithNumbers {
    @XmlName(name = "app_name") public String app_name;
    @XmlName(name = "app_version") public int app_version;
    @XmlName(name = "build_info") public TestBuildInfoWithNumbers buildInfo;

    @Override
    public String toString() {
        return "TestResourcesEntryWithNumbers{" +
                "app_name='" + app_name + '\'' +
                ", app_version=" + app_version +
                ", buildInfo=" + buildInfo +
                '}';
    }
}