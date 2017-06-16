package com.justforfun.simplexml.model.java_models;

import com.justforfun.simplexml.annotation.XmlName;

/**
 * Created by Vladimir on 6/16/17.
 */

@XmlName(name = "build_info")
public class TestBuildInfoWithNumbers {
    @XmlName(name = "app_code_version") public int app_code_version;
    @XmlName(name = "build_version") public int build_version;

    @Override
    public String toString() {
        return "TestBuildInfoWithNumbers{" +
                "app_code_version=" + app_code_version +
                ", build_version=" + build_version +
                '}';
    }
}