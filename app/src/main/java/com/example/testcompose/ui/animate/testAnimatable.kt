package com.example.testcompose.ui.animate

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
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
fun TestAnimatable() {
    //1.生成 Animatable 对象,Animatable 构造函数初始值为 float 类型，
    // 如果需要其它类型，需要使用 TwoWayConverter 转换，就是将其它类型转换为 float 类型
    // Dp.VectorConverter 提供了 从 dp 转换为 float 的算法，我们直接使用
    var big by remember { mutableStateOf(false) }
    val size = remember(big) { if (big) 96.dp else 48.dp }
    //这里使用 animatable 的构造函数，如果 只写一个参数 8f 使用的是普通函数
    val anim = remember { Animatable(size, Dp.VectorConverter) }
    LaunchedEffect(big) {
        /**
         * anim.snapTo 点击时，放大从 192.dp 到 96.dp，缩小 从 0.dp 到 48.dp ,类似弹簧效果
         */
//        anim.snapTo(if (big) 192.dp else 0.dp)//设置初始值，瞬间完成,没有动画效果
        /**
         * TweenSpec：补间动画 easing:缓动
         * LinearEasing 匀速
         */
//        anim.animateTo(size,TweenSpec(easing = LinearEasing))
        /**
         * FastOutLinearInEasing 全程加速的动画
         */
//        anim.animateTo(size, TweenSpec(easing = FastOutLinearInEasing))
        /**
         * FastOutSlowInEasing 先加速后减速动画
         */
//        anim.animateTo(size, TweenSpec(easing = FastOutSlowInEasing))
        /**
         * LinearOutSlowInEasing 全程减速的动画
         */
//        anim.animateTo(size, TweenSpec(easing = LinearOutSlowInEasing))
        /**
         * 自定义 easing
         * 1.Easing 接口 参数是动画的实际完成度
         * 2.it 参数传递 it 表示动画完成度与时间完成度一致，即是线性动画
         * 3.传递 0.3f 表示动画时间结束后,动画完成度为 0.3
         */
//        anim.animateTo(size, TweenSpec(easing = Easing { 0.3f }))
        /**
         * SnapSpec 动画效果是突变的 延时功能，下面为延时 1 秒后开始执行动画
         */
//        anim.animateTo(size, snap(1000))
        /**
         * KeyframesSpec
         * 1. 关键帧动画
         * 2. 在哪个时间点 动画执行到哪个位置
         * 3. 下面示例为 50毫秒动画执行到 50dp
         * 4. durationMillis 设置动画执行时间,单位 毫秒
         * 5. delayMillis 设置动画延时执行时间 单位毫秒
         * 6. 设置关键帧的速度曲线 48.dp at 0 with FastOutSlowInEasing 作用于后面那段时间的速度曲线，见代码注释
         * 7. 不写默认的速度曲线为 LinearEasing
         */
//        anim.animateTo(size, keyframes {
//            //设置动画执行时间,单位 毫秒
//            durationMillis = 450
//            //设置动画延时执行时间 单位毫秒
//            delayMillis = 1000
//            //1. 设置关键帧
//            //2. 设置速度曲线 with FastOutSlowInEasing 作用于后面那段时间
//            48.dp at 0 with FastOutSlowInEasing//初始时的速度曲线 作用于 0-150 毫秒的速度曲线
//            140.dp at 150 with FastOutSlowInEasing//作用于 150-200 毫秒的速度曲线
//            80.dp at 200 with LinearOutSlowInEasing//作用于 200- 450 毫秒的速度曲线
//            96.dp at 450 with LinearEasing//不写默认的速度曲线
//        })
        /**
         * SpringSpec
         * 1. 弹簧动画
         * 2. 构造函数参数
         * dampingRatio: Float = Spring.DampingRatioNoBouncy, 阻尼比，弹簧阻力是多大？
         * stiffness: Float = Spring.StiffnessMedium,
         * visibilityThreshold: T? = null
         */
        anim.animateTo(size, spring(6f))
    }
    Box(modifier = Modifier
        .size(anim.value)
        .background(Color.Blue)
        .clickable { big = !big }) {
        Text(text = "Animatable", color = Color.Red)
    }

}

/**
 * 移动控件位置的动画
 */
@Composable
fun TestAnimtable2() {
    var big by remember { mutableStateOf(false) }
    val offset = remember(big) { if (big) (-48.dp) else 100.dp }
    val anim = remember { Animatable(offset, Dp.VectorConverter) }

    LaunchedEffect(big) {
        anim.animateTo(offset, TweenSpec(easing = FastOutLinearInEasing))
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .offset(anim.value, anim.value)
            .size(48.dp)
            .background(Color.Green)
            .clickable { big = !big }) {
            Text(text = "Animtable offset")
        }
    }
}