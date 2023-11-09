package com.example.core.ext

import junit.framework.TestCase.assertEquals
import org.junit.Test

class StringExtTest {

    @Test
    fun `formatSummary removes unwanted characters`() {
        assertEquals(FORMATTED_TEXT, UNWANTED_TAGS.formatSummary())
    }

    companion object {
        private const val UNWANTED_TAGS = "<tag>This is [test] string \$with unwanted characters."
        private const val FORMATTED_TEXT = "This is  string with unwanted characters."
    }
}
