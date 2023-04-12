package com.example.testcompose.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle

/**
 *
 * @date   :
 * @author : wanghailong
 *
 * @description: TODO
 * Text 属性
 */

@Composable
fun testSpan(ctx: Context) {
    val str1 = "勾选即表示同意"
    val str2 = "《隐私政策》"
    val str3 = "《隐私信息使用说明》"
    val str4 = "《三方SDK信息共享》"

    val annotationStrings = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color.Black)) {
            append(str1)
        }

        pushStringAnnotation(tag = str2, str2)
        withStyle(style = SpanStyle(color = Color.Blue, fontWeight = FontWeight.Bold)) {
            append(str2)
        }
        pop()

        pushStringAnnotation(str3, str3)
        withStyle(style = SpanStyle(color = Color.Blue, fontWeight = FontWeight.Bold)) {
            append(str3)
        }
        pop()

        pushStringAnnotation(str4, str4)
        withStyle(style = SpanStyle(color = Color.Blue, fontWeight = FontWeight.Bold)) {
            append(str4)
        }
        pop()
    }

    ClickableText(text = annotationStrings, onClick = { clickIdx ->
        annotationStrings.getStringAnnotations(str2, start = clickIdx, end = clickIdx).firstOrNull()
            ?.let {
                Toast.makeText(ctx, it.item, Toast.LENGTH_SHORT).show()
            }

        annotationStrings.getStringAnnotations(str3, start = clickIdx, end = clickIdx).firstOrNull()
            ?.let {
                Toast.makeText(ctx, it.item, Toast.LENGTH_SHORT).show()
            }

        annotationStrings.getStringAnnotations(str4, start = clickIdx, end = clickIdx).firstOrNull()
            ?.let {
                Toast.makeText(ctx, it.item, Toast.LENGTH_SHORT).show()
            }
    })
}