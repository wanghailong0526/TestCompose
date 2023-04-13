package com.example.testcompose.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
fun TestButton() {
    Column(
        Modifier
            .background(color = Color.White)
            .fillMaxWidth()
    ) {
        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(2.dp, Color.Red),
            contentPadding = PaddingValues(20.dp),
            colors = object : ButtonColors {

                @Composable
                override fun backgroundColor(enabled: Boolean): State<Color> {
                    //内容的背景颜色
                    return rememberUpdatedState(if (enabled) Color.Green else Color.Yellow)
                }

                @Composable
                override fun contentColor(enabled: Boolean): State<Color> {
                    //内部控件的颜色
                    return rememberUpdatedState(if (enabled) Color.Red else Color.Blue)
                }
            },
            enabled = true

        ) {
            Image(
                painterResource(com.example.testcompose.R.drawable.ic_launcher_foreground),
                contentDescription = "icon"
            )
            Text("我是 Button")
        }

        OutlinedButton(onClick = { /*TODO*/ }) {
            Text("我是 OutlineButton")
        }

        TextButton(onClick = { /*TODO*/ }) {
            Text("我是TextButton")
        }
    }
}
