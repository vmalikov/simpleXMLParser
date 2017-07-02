package com.justforfun.simplexml.kt_tests

import android.support.test.InstrumentationRegistry
import android.text.TextUtils
import com.justforfun.simplexml.R
import com.justforfun.simplexml.SimpleXmlParser
import com.justforfun.simplexml.model.kt_models.TestChannelEntry
import org.junit.*
import java.io.InputStream

/**
 * Created by Vladimir on 6/16/17.
 */
class SimpleLJRssParserTest {
    lateinit var entry: TestChannelEntry
    lateinit var inputStream: InputStream

    @Before
    fun setUp() {
        inputStream = InstrumentationRegistry.getInstrumentation().getContext().getResources().openRawResource(R.raw.lj_rss_sample)
        entry = SimpleXmlParser.parse<TestChannelEntry>(inputStream, TestChannelEntry::class.java!!) ?: TestChannelEntry()
    }

    @Test fun testEntryTitle() {
        Assert.assertTrue("Entry title: >${entry?.title}<", TextUtils.equals(entry?.title, "Penny Arcade"))
    }

    @Test fun testEntryDescription() {
        Assert.assertTrue("Entry description: >${entry?.description}<", TextUtils.equals(entry?.description, "News Fucker 6000"))
    }

    @Test fun testEntryLink() {
        Assert.assertTrue("Entry link: >${entry?.link}<", TextUtils.equals(entry?.link, "http://penny-arcade.com"))
    }

    @Test fun testItemsInNotEmpty() {
        Assert.assertTrue(entry?.items?.isNotEmpty() as Boolean)
    }

    @Test fun testItemsSizeIs_10() {
        Assert.assertTrue(entry?.items?.size == 10)
    }

    @After
    fun tearDown() {
        inputStream.close()
    }
}