package com.example.testcompose.ui.animate

import android.widget.Toast
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationEndReason
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.StartOffsetType
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.exponentialDecay
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.animation.splineBasedDecay
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.testcompose.LocalWidth
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay

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
         *      1. dampingRatio: Float = Spring.DampingRatioNoBouncy, 阻尼比，弹簧阻力是多大？默认值 1 一点都不弹，值越大动画越慢
         *      2. stiffness: Float = Spring.StiffnessMedium, 刚度，(硬度)就是把弹簧拉开后，它有多想变回去,刚度越强越想变回去
         *      3. visibilityThreshold: T? = null 可视阈值 弹簧震动过程中，距离弹簧停下来的那个点达到这个值弹簧就停下来
         */
////        anim.animateTo(size, spring(0.1f, Spring.StiffnessHigh, 5.dp))
//        //2000.dp 是初始速度，下面这个动画是弹簧动画后，回来控件原来大小
//        anim.animateTo(48.dp, spring(0.1f, Spring.StiffnessHigh), 2000.dp)
        /**
         * RepeatableSpec
         * 1.重复执行动画 SpringSpec 不能重复  KeyframesSpec SnapSpec TweenSpec 这三个可以重复
         * 2.构造函数参数
         *  1. iterations: Int, 重复次数
         *  2. animation: DurationBasedAnimationSpec<T>, 动画
         *  3. repeatMode: RepeatMode = RepeatMode.Restart, 重复模式
         *      1. RepeatMode.Restart_重新播放
         *      2. RepeatMode.Reverse_倒放
         *  4. initialStartOffset: StartOffset = StartOffset(0) 偏移时间 单位毫秒
         *      1. StartOffsetType.Delay 延时动画的开始执行时间
         *      2. StartOffsetType.FastForward 快进 快进的指定的时间点
         *  5. InfiniteRepeatableSpec 无限循环的动画 当动画所在协程退出时，动画则退出
         */
//        anim.animateTo(
//            size,
////            repeatable(3, tween(), RepeatMode.Reverse, StartOffset(1000, StartOffsetType.FastForward))
//            infiniteRepeatable( tween(), RepeatMode.Reverse, StartOffset(1000, StartOffsetType.FastForward))
//        )
    }
    Box(modifier = Modifier
        .size(anim.value)
        .background(Color.Blue)
        .clickable { big = !big }) {
        Text(text = "Animatable", color = Color.Red)
    }

}

/**
 * DecayAnimateSpec 惯性衰减
 * 1. initialVelocity: T, 初始速度 单位与 Animatable 创建时的单位保持一致
 * 2. animationSpec: DecayAnimationSpec<T>,
 *  1. splineBasedDecay<>() //只能使用像素 不使用这个，使用 rememberSplineBasedDecay
 *  2. exponentialDecay<>() 指数衰减 单位是 Dp
 *      1. 两个参数
 *          1. frictionMultiplier: Float = 1f, 摩擦系数，越大摩擦力越大，滑动距离越小
 *          2. absVelocityThreshold: Float = 0.1f 速度阈值的绝对值
 *  3. rememberSplineBasedDecay() 单位是像素，如果是 Dp 要转换成 px
 * 3. block: (Animatable<T, V>.() -> Unit)? = null 每一帧的回调
 *
 *
 * 动画被打断
 * 1. 对同一个控件的动画 当前一个动画在执行，后面也开始执行了另一个动画，前一个动画会被打断。然后抛异常 CancellationException
 * 2. anim.stop() 挂起函数，必须要在协程里执行
 *      1.LaunchedEffect 是和界面相关的，实战中可能会用 lifeCycleScop.launch{anim.stop()}
 * 3. anim.updateBounds() 设置动画边界，上边界和下边界 到达边界后直接停下
 */
@Composable
fun TestAnimatableDecay() {
    val ctx = LocalContext.current
    val anim = remember { Animatable(0.dp, Dp.VectorConverter) }
    val animY = remember { Animatable(0.dp, Dp.VectorConverter) }
    var padding2 by remember { mutableStateOf(anim.value) }
//    val decay = rememberSplineBasedDecay<Dp>()//操作像素，Dp 是不对的
    val decay = remember { exponentialDecay<Dp>(/*4f*/) }

//        LaunchedEffect(Unit) {
//            delay(1000)
//            try {
//                anim.animateDecay(1000.dp, decay) {
//                    //每一帧的回调
//                    padding2 = value
//                }
//            } catch (e: CancellationException) {
//                Toast.makeText(
//                    ctx.applicationContext,
//                    "动画被打断了！！！",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//        }
    //停止动画
//    LaunchedEffect(Unit) { anim.stop() }

    LaunchedEffect(Unit) {
        delay(1000)
        val result = anim.animateDecay(2000.dp, decay)
        if (result.endReason == AnimationEndReason.BoundReached) {
            anim.animateDecay(-result.endState.velocity, decay)
        }

    }
    LaunchedEffect(Unit) {
        delay(1000)
        val result = animY.animateDecay(4000.dp, decay)
        if (result.endReason == AnimationEndReason.BoundReached) {
            anim.animateDecay(-result.endState.velocity, decay)
        }
    }
    anim.updateBounds(upperBound = 393.dp - 100.dp, lowerBound = 0.dp)
    animY.updateBounds(upperBound = 839.dp - 100.dp, lowerBound = 0.dp)

    Row {
        Box(
            modifier = Modifier
                .padding(anim.value, animY.value, 0.dp, 0.dp)
                .size(100.dp)
                .background(Color.Blue)
        )
        {
            Text(text = "AnimatableDecay", color = Color.Red)
        }
//            Box(
//                modifier = Modifier
//                    .padding(0.dp, padding2, 0.dp, 0.dp)
//                    .size(100.dp)
//                    .background(Color.Blue)
//            )
//            {
//                Text(text = "AnimatableDecay", color = Color.Red)
//            }
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