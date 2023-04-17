package com.example.testcompose.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
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
fun TestMutableStateListOf() {
    val nums = remember { mutableStateListOf(0, 1, 2, 3) }
    Column {
        Button(onClick = { nums.add(nums.last() + 1) }) {
            Text(text = "加1")
        }
        for (num in nums) {
            Text("第${num}个")
        }
    }
}

