package com.justforfun.simplexml.kt_tests

import android.support.test.InstrumentationRegistry
import android.text.TextUtils
import com.justforfun.simplexml.R
import com.justforfun.simplexml.SimpleXmlParser
import com.justforfun.simplexml.model.kt_models.TestResourcesEntry
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.InputStream
import java.io.InputStreamReader

/**
 * Created by Vladimir on 6/16/17.
 */
class SimpleXmlParserFromStringTest {
    lateinit var entry: TestResourcesEntry
    lateinit var inputStream: InputStream

    @Before
    fun setUp() {
        inputStream = InstrumentationRegistry.getInstrumentation().getContext().getResources().openRawResource(R.raw.strings_sample)
        val inputString = convertInputToString(inputStream)
        entry = SimpleXmlParser.parse<TestResourcesEntry>(inputString, TestResourcesEntry::class.java!!) ?: TestResourcesEntry()
    }

    fun convertInputToString(input: InputStream?): String {
        val bufferSize = 1024
        val buffer = CharArray(bufferSize)
        val out = StringBuilder()
        val `in` = InputStreamReader(input, "UTF-8")
        while (true) {
            val rsz = `in`.read(buffer, 0, buffer.size)
            if (rsz < 0)
                break
            out.append(buffer, 0, rsz)
        }
        return out.toString()
    }

    @Test fun testEntryAppName() {
        Assert.assertTrue("Entry app name: >${entry?.app_name}<", TextUtils.equals(entry?.app_name, "SimpleXmlParser"))
    }

    @Test fun testAppVersion() {
        Assert.assertTrue("Entry app version: >${entry?.app_version}<", entry?.app_version.equals("1"))
    }

    @Test fun testAppCodeVersion() {
        Assert.assertTrue("Entry buildInfo.app_code_version: >${entry?.buildInfo.app_code_version}<", entry?.buildInfo.app_code_version.equals("2"))
    }

    @Test fun testBuildVersion() {
        Assert.assertTrue("Entry buildInfo.build_version: >${entry?.buildInfo.build_version}<", entry?.buildInfo.build_version.equals("3"))
    }

    @Test fun testBuildInfoIsNotNull() {
        Assert.assertFalse(entry?.buildInfo == null)
    }

    @After
    fun tearDown() {
        inputStream.close()
    }
}