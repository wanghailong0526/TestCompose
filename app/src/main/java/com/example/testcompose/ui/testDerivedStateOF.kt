package com.example.testcompose.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlin.reflect.KProperty

/**
 *
 * @date   :
 * @author : wanghailong
 *
 * @description: TODO
 *
 */
@Composable
fun TestDerivedStateOf() {
    var name by remember { mutableStateOf("whl") }
    val len by remember { derivedStateOf { name.length } }

    Column {
        Text(
            "有 ${len} 个字符",
            Modifier.clickable { name = "kwg kwg kwg kwg " })
    }
}

@Composable
fun TestDerivedStateOf2() {
    var name by remember { mutableStateOf("whl") }
//    val len = remember { name.length }//这种写法，name 改变时，len 不会改变
    val len = remember(name) { name.length }//remember(Key...k2,k3)这种方式

    Column() {
        Text(
            "有 ${len} 个字符",
            Modifier.clickable { name = "kwg kwg kwg kwg " })
    }
}

@Composable
fun TestDerivedStateOf3() {
    var names = remember { mutableStateListOf("whl", "haoren") }
    val newNames by remember {
        derivedStateOf {
            names.map {
                it.uppercase()
            }
        }
    }
    Column {
        for (name in newNames) {
            Text(
                text = name,
                modifier = Modifier.clickable { names.add("compose") }
            )
        }
    }
}

/**
 * @param names 数据列表
 * @param onClick 外部外部传入的点击事件
 */
@Composable
fun TestDerivedStateOf4(names: List<String>, onClick: () -> Unit) {
    val newNames by remember(names) { derivedStateOf { names.map { it.uppercase() } } }
    Text(newNames.toString(), modifier = Modifier.clickable { onClick.invoke() })
}

