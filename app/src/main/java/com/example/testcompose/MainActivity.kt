package com.example.testcompose

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.testcompose.R.drawable
import com.example.testcompose.ui.TestButton
import com.example.testcompose.ui.TestColumn
import com.example.testcompose.ui.TestSpan

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val scrollState = rememberScrollState(0)
            Column(Modifier.verticalScroll(scrollState).fillMaxSize()) {

                Greeting("Android")
                //Image 显示本地图片 使用 painterResource(id)
                Image(painterResource(drawable.ic_launcher_background), "Icon")
                //Image 显示网络图片使用 coil 库 加载图片
                AsyncImage(
                    model = "https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png",
                    contentDescription = "Icon",
                )

                TestSpanStyle( ctx = this@MainActivity)
                TestSpan(ctx = this@MainActivity)
                TestColumn()
                TestButton()




                //lazycolum 直接生成列表
//            LazyColumn {
//                items(names/*数组*/) { name ->
//                    Text(name)
//                }
//            }
                //lazyColumn 生成特定的 viewType 使用 item
//            LazyColumn {
//                item {
//                    AsyncImage(
//                        model = "https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png",
//                        contentDescription = "coil Icon"
//                    )
//                }
//                items(names){ name ->
//                    Text(text = name, /*fontfontSize = 20.sp,*/ style = TextStyle(fontSize = 20.sp))
//                }
//            }

                //给需要滑动的组件添加 Modifier.verticalScroll  Modifier.hori
//            val scrollState = rememberScrollState(0)
//            Column(Modifier.verticalScroll(scrollState).fillMaxSize()) {
//                Text(
//                    "Editor picks".uppercase(),
//                    modifier = Modifier.padding(8.dp)
//                )
//            }
//            Column(modifier = Modifier.horizontalScroll(scrollState)) {}

            }
        }
    }
}

@Composable
fun Greeting(text: String) {
    Text(
        text = text, // 文字
        color = Color.Green, // 字体颜色
        fontSize = 16.sp, // 字体大小
        fontStyle = FontStyle.Italic, // 斜体
        fontWeight = FontWeight.Bold, // 粗体
        textAlign = TextAlign.Center, // 对齐方式: 居中对齐
        modifier = Modifier.width(300.dp), // 指定宽度为300dp
        maxLines = 2, // 最大行数
        overflow = TextOverflow.Ellipsis, // 文字溢出后就裁剪
        softWrap = true, // 文字过长时是否换行
        textDecoration = TextDecoration.Underline, // 文字装饰，这里添加下划线
    )
}

@Composable
fun TestSpanStyle(ctx: Context) {

    val annotatedString = buildAnnotatedString {

        append("勾选即代表同意")


        pushStringAnnotation("隐私政策", "隐私政策")
        // 使用白色背景，红色字体，18sp，Monospace字体来绘制"Hello " (注意后面有个空格)
        withStyle(
            style = SpanStyle(
                color = Color.Red,
                background = Color.White,
                fontSize = 18.sp,
                fontFamily = FontFamily.Monospace,

                )
        ) {
            append(" 隐私政策 ")
        }
        pop()
        // 正常绘制"World"
        append("World ")

        // 使用黄色背景，绿色字体，18sp，Serif字体，W900粗体来绘制"Click"
        withStyle(
            style = SpanStyle(
                color = Color.Green,
                background = Color.Yellow,
                fontSize = 30.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.W900
            )
        ) {
            append("Click")
        }
        // 正常绘制" Me" (注意前面有个空格)
        append(" Me")

        pushStringAnnotation("我们是中国人", "我们是中国人")
        // 添加阴影及几何处理
        withStyle(
            style = SpanStyle(
                color = Color.Yellow,
                background = Color.White,
                baselineShift = BaselineShift(1.0f), // 向BaseLine上偏移10
                textGeometricTransform = TextGeometricTransform(
                    scaleX = 2.0F,
                    skewX = 0.5F
                ), // 水平缩放2.0，并且倾斜0.5
                shadow = Shadow(
                    color = Color.Blue,
                    offset = Offset(x = 1.0f, y = 1.0f),
                    blurRadius = 10.0f
                ) // 添加音阴影和模糊处理
            )
        ) {
            append(" 我们是中国人")
        }

        pop()
    }

    ClickableText(
        text = annotatedString,
        onClick = { clickIdx ->
            annotatedString.getStringAnnotations(
                tag = "隐私政策",
                start = clickIdx,
                end = clickIdx
            ).firstOrNull()?.let {
                Toast.makeText(ctx, it.item, Toast.LENGTH_SHORT).show()
            }

            annotatedString.getStringAnnotations(
                tag = "我们是中国人",
                start = clickIdx,
                end = clickIdx
            ).firstOrNull()?.let {
                Toast.makeText(ctx, it.item, Toast.LENGTH_SHORT).show()
            }
        }
    )
}




@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Greeting("Android")
}