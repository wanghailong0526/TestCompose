package com.example.testcompose.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
fun TestColumn() {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)//margin 效果
            .background(Color.Red)
    ) {
        Text(
            "我是第一个文字，我有背景和边距！",
            Modifier
                .padding(10.dp)//margin 效果
                .padding(10.dp)//margin 效果
                .background(Color.Green)
        )
        Text(
            "我是第二个文字，我也有背景和边距！",
            Modifier
                .padding(10.dp)//margin 效果
                .background(Color.Black)
                .padding(10.dp),//padding 效果
            Color.White
        )
        Text(
            "我是第三个文字，我也有背景和边距！",
            Modifier
                .background(Color.Black)
                .padding(10.dp)//padding 效果
                .padding(10.dp),//padding 效果
            Color.White
        )
    }
}