package com.example.testcompose.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember

/**
 *
 * @date   :
 * @author : wanghailong
 *
 * @description: TODO
 *
 */
@Composable
fun TestMutableStateMapOf() {
    val maps = remember { mutableStateMapOf(0 to "0", 1 to "1", 2 to "2", Pair(3, "3")) }
    Column {
        Button(onClick = { maps.put(maps.keys.last() + 1, (maps.keys.last() + 1).toString()) }) {
            Text(text = "加 1")
        }
//        for (map in maps) {
//            Text(text = "${map.key} to ${map.value}")
//        }
        for ((k, v) in maps) {
            Text("${k} to ${v}")
        }
    }
}