package com.example.testcompose.ui.animate

import android.opengl.Visibility
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

/**
 *
 * @date   :
 * @author : wanghailong
 *
 * @description: TODO
 * AnimatedVisibility  只能操作一个布局，多个布局只能一个生效
 * 1. enter 参数
 *  1. fadeIn(tween(2000),initialAlpha = 0.3f) //淡入
 *  2. slideIn(tween(2000)) { IntOffset(-it.width, -it.height) } //位移
 *  3. changeSize 使用 expandIn 裁切的方式来实现动画
 *  4. scale  scaleIn(transformOrigin = TransformOrigin.Center)//缩放
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TestAnimetedVisibility() {
    var big by remember { mutableStateOf(true) }
    var bigState = remember { MutableTransitionState(!big) }//设置初始值
    bigState.targetState = big
    val bigTransition = updateTransition(bigState, "big")
    val size by bigTransition.animateDp(label = "size") { if (it) 96.dp else 48.dp }
    val corner by bigTransition.animateDp(label = "corner") { if (it) 0.dp else 8.dp }
    var visibility by remember { mutableStateOf(true) }
    Column() {

        AnimatedVisibility(
            visible = visibility,
//            enter = fadeIn(tween(2000), initialAlpha = 0.3f)
//            enter = slideIn(tween(2000)) { IntOffset(-it.width, -it.height) }
//            enter = expandIn(
//                tween(5000),
//                expandFrom = Alignment.TopStart,//从哪里开始展开,Alignment.TopStart 左上角
//                clip = true,//是否裁剪
//                initialSize = { IntSize(it.width / 2, it.height / 2) })//初始化大小
            //TransformOrigin.Center 缩放中心
            enter = scaleIn(transformOrigin = TransformOrigin.Center)
        ) {
            Box(modifier = Modifier
                .size(size)
                .clip(RoundedCornerShape(corner))
                .background(Color.Green)
                .clickable { big = !big })
        }

        Button(onClick = { visibility = !visibility }) {
            Text(text = if (visibility) "隐藏" else "显示")
        }
    }
}