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
import androidx.compose.runtime.*
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
import androidx.lifecycle.lifecycleScope
import coil.compose.AsyncImage
import com.example.testcompose.R.drawable
import com.example.testcompose.ui.TestButton
import com.example.testcompose.ui.TestColumn
import com.example.testcompose.ui.TestCompositionLocal
import com.example.testcompose.ui.TestDerivedStateOf
import com.example.testcompose.ui.TestDerivedStateOf2
import com.example.testcompose.ui.TestDerivedStateOf3
import com.example.testcompose.ui.TestDerivedStateOf4
import com.example.testcompose.ui.TestMutableStateListOf
import com.example.testcompose.ui.TestMutableStateMapOf
import com.example.testcompose.ui.TestSpan
import com.example.testcompose.ui.TestTextField
import com.example.testcompose.ui.animate.TestAnimateDpAsState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val scrollState = rememberScrollState(0)
            Column(
                Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {

                Greeting("Android")
                //Image 显示本地图片 使用 painterResource(id)
                Image(painterResource(drawable.ic_launcher_background), "Icon")
                //Image 显示网络图片使用 coil 库 加载图片
                AsyncImage(
                    model = "https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png",
                    contentDescription = "Icon",
                )

                TestSpan(ctx = this@MainActivity)
                TestColumn()
                TestButton()
                TestTextField()

                //mutablestateof  下面的方式不会更新 Text 内容为 "王海龙",
                //因为重组时 val name = mutableStateOf("whl") 反复执行，生成多个 name
                //导致老的 name 值更新后，不影响新的 name 值
                /*                val name = mutableStateOf("whl")
                                Text(name.value)

                                lifecycleScope.launch {
                                    delay(3000)
                                    name.value = "王海龙"
                                }*/

                //mutableStateOf 正确用法，使用 remember
                var name by remember { mutableStateOf("whl") }
                Text(name)

                lifecycleScope.launch {
                    delay(3000)
                    name = "王海龙"
                }
                ShowMsg(value = "1234")//Text 显示 4
                ShowMsg(value = "12345")//Text 显示 5
                ShowMsg(value = "123456")//Text 显示 6

                TestMutableStateListOf()
                TestMutableStateMapOf()
                TestDerivedStateOf()
                TestDerivedStateOf2()
                TestDerivedStateOf3()

                var names = remember {
                    mutableStateListOf("whl", "android", "compose")
                }
                TestDerivedStateOf4(names = names) {
                    names.add("java")
                }

                TestCompositionLocal()//界面显示 "默认"
                CompositionLocalProvider(LocalName provides "whl") {
                    TestCompositionLocal()//界面显示 "whl"
                }

                TestAnimateDpAsState()

            }
        }
    }
}

//只有当 value 参数改变时才会更新 Text 的值
@Composable
fun ShowMsg(value: String) {
    val length = remember(value) { value.length }
    Text("长度是${length}")
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Greeting("Android")
}

class User(name: String) {
    //第一个 name 是属性，第二个 name 是上面传递的参数
    var name by mutableStateOf(name)
}

val LocalName = compositionLocalOf { "默认" }