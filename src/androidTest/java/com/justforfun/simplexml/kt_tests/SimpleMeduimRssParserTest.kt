package com.justforfun.simplexml.kt_tests

import android.support.test.InstrumentationRegistry.getInstrumentation
import com.justforfun.simplexml.model.kt_models.TestChannelEntry
import org.junit.Assert.assertTrue

/**
 * Created by Vladimir on 6/16/17.
 */
@org.junit.runner.RunWith(android.support.test.runner.AndroidJUnit4::class)
class SimpleMeduimRssParserTest {

    lateinit var entry: com.justforfun.simplexml.model.kt_models.TestChannelEntry
    lateinit var inputStream: java.io.InputStream

    @org.junit.Before
    fun setUp() {
        inputStream = getInstrumentation().getContext().getResources().openRawResource(com.justforfun.simplexml.R.raw.medium_rss_sample)
        entry = com.justforfun.simplexml.SimpleXmlParser.Companion.parse<TestChannelEntry>(inputStream, TestChannelEntry::class.java!!) ?: com.justforfun.simplexml.model.kt_models.TestChannelEntry()
    }

    @org.junit.Test fun testEntryTitle() {
        assertTrue("Entry title: >${entry?.title}<", android.text.TextUtils.equals(entry?.title, "Stories by Medium on Medium"))
    }

    @org.junit.Test fun testEntryDescription() {
        assertTrue("Entry description: >${entry?.description}<", android.text.TextUtils.equals(entry?.description, "Stories by Medium on Medium"))
    }

    @org.junit.Test fun testEntryLink() {
        assertTrue("Entry link: >${entry?.link}<", android.text.TextUtils.equals(entry?.link, "https://medium.com/@Medium?source=rss-504c7870fdb6------2"))
    }

    @org.junit.Test fun testItemsInNotEmpty() {
        assertTrue(entry?.items?.isNotEmpty() as Boolean)
    }

    @org.junit.Test fun testItemsSizeIs_9() {
        assertTrue(entry?.items?.size == 9)
    }

    @org.junit.After
    fun tearDown() {
        inputStream.close()
    }
}