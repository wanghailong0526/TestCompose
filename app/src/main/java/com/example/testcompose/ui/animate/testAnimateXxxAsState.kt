package com.example.testcompose.ui.animate

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 *
 * @date   :
 * @author : wanghailong
 *
 * @description: TODO
 *
 */
@Composable
fun TestAnimateDpAsState() {
    var big by remember { mutableStateOf(false) }
    val size by animateDpAsState(targetValue = if (big) 96.dp else 48.dp)

    Box(
        Modifier
            .size(size)
            .background(Color.Green)
            .clickable { big = !big })
}