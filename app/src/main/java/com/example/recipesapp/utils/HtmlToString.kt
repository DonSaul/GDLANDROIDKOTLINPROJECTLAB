package com.example.recipesapp.utils

import android.text.Html
import android.text.Spanned
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString


fun htmlToAnnotatedString(html: String): AnnotatedString {
    val spanned: Spanned =
        Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
    return spanned.toAnnotatedString()
}

fun Spanned.toAnnotatedString(): AnnotatedString {
    return buildAnnotatedString {
        append(this@toAnnotatedString)
    }
}