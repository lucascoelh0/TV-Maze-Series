package com.example.core.ext

fun String.formatSummary(): String {
    return replace("<[^>]*>|\\$|\\[[^]]*]".toRegex(), "")
}
