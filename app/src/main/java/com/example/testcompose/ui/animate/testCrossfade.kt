package com.example.testcompose.ui.animate

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 *
 * @date   :
 * @author : wanghailong
 *
 * @description: TODO
 * Crossfade
 * 1. 让两个交替出现的组件的交替过程是渐变的
 * 2. 使用透明度渐变的方式
 * 3.尺寸是直接变化的
 */

@Composable
fun TestCrossface() {
    Column() {
        var big by remember { mutableStateOf(true) }

        Crossfade(targetState = big) {
            //it 就是 big
            if (it) {
                ShowBig()
            } else {
                ShowSmall()
            }
        }
        Button(onClick = { big = !big }) {
            Text(text = "切换")
        }
    }

}

@Composable
fun ShowBig() {
    Box(
        modifier = Modifier
            .size(96.dp)
            .background(Color.Green)
    )
}

@Composable
fun ShowSmall() {
    Box(
        modifier = Modifier
            .size(48.dp)
            .background(Color.Green)
    )
}