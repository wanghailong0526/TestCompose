package com.example.testcompose.ui.animate

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
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
 * AnimatedContent
 * 1.渐变
 * 2.尺寸是渐变的
 */

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TestAnimatedContent() {
    Column() {
        var big by remember { mutableStateOf(true) }

        AnimatedContent(targetState = big) {
            if (it) {
                Box(
                    modifier = Modifier
                        .size(96.dp)
                        .background(Color.Green)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(96.dp)
                        .background(Color.Red)
                )
            }
        }
        Button(onClick = { big = !big }) {
            Text(text = "切换")
        }
    }

}
