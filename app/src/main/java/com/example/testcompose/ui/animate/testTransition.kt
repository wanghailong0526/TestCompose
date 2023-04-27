package com.example.testcompose.ui.animate

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 *
 * @date   :
 * @author : wanghailong
 *
 * @description: TODO
 * Transition 针对多个属性
 * Animtable 针对某个属性
 */
@Preview
@Composable
fun TestTransition() {
    var big by remember { mutableStateOf(false) }
    val bigTransition = updateTransition(big, "big")
    // it 就是 big; big 就是状态
    val size by bigTransition.animateDp(
        //initialState 和 targetState 的类型是根据 updateTransition 的状态类型来定的
//        { if ((!initialState && targetState)) spring() else tween() },//方法一
        { if (false isTransitioningTo true) spring() else tween() },//方法二
        label = "size"
    ) { if (it) 96.dp else 48.dp }
    val corner by bigTransition.animateDp(label = "corner") { if (it) 0.dp else 8.dp }

    Box(modifier = Modifier
        .size(size)
        .clip(RoundedCornerShape(corner))
        .background(Color.Green)
        .clickable { big = !big })
}

/**
 * 设置初始值
 */
@Composable
fun TestTransition2() {
    var big by remember { mutableStateOf(true) }
    var bigState = remember { MutableTransitionState(!big) }//设置初始值
    bigState.targetState = big
    val bigTransition = updateTransition(bigState, "big")
    val size by bigTransition.animateDp(label = "size") { if (it) 96.dp else 48.dp }
    val corner by bigTransition.animateDp(label = "corner") { if (it) 0.dp else 8.dp }

    Box(modifier = Modifier
        .size(size)
        .clip(RoundedCornerShape(corner))
        .background(Color.Green)
        .clickable { big = !big })
}