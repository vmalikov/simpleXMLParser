package com.justforfun.simplexml.java_tests

import android.support.test.InstrumentationRegistry
import android.text.TextUtils
import com.justforfun.simplexml.R
import com.justforfun.simplexml.SimpleXmlParser
import com.justforfun.simplexml.model.kt_models.TestResourcesEntryWithNumbers
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.InputStream

/**
 * Created by Vladimir on 6/16/17.
 */
class ResourcesWithNumbers_KT_Test {
    lateinit var entry: TestResourcesEntryWithNumbers
    lateinit var inputStream: InputStream

    @Before
    fun setUp() {
        inputStream = InstrumentationRegistry.getInstrumentation().getContext().getResources().openRawResource(R.raw.strings_sample)
        entry = SimpleXmlParser.parse<TestResourcesEntryWithNumbers>(inputStream, TestResourcesEntryWithNumbers::class.java!!) ?: TestResourcesEntryWithNumbers()
    }

    @Test fun testEntryAppName() {
        Assert.assertTrue("Entry app name: >${entry?.app_name}<", TextUtils.equals(entry?.app_name, "SimpleXmlParser"))
    }

    @Test fun testAppVersion() {
        Assert.assertTrue("Entry app version: >${entry?.app_version}<", entry?.app_version == 1)
    }

    @Test fun testAppCodeVersion() {
        Assert.assertTrue("Entry buildInfo.app_code_version: >${entry?.buildInfo.app_code_version}<", entry?.buildInfo.app_code_version == 2)
    }

    @Test fun testBuildVersion() {
        Assert.assertTrue("Entry buildInfo.build_version: >${entry?.buildInfo.build_version}<", entry?.buildInfo.build_version == 3)
    }

    @Test fun testBuildInfoIsNotNull() {
        Assert.assertFalse(entry?.buildInfo == null)
    }

    @After
    fun tearDown() {
        inputStream.close()
    }
}