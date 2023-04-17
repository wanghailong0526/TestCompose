package com.example.testcompose.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.testcompose.LocalName

/**
 *
 * @date   :
 * @author : wanghailong
 *
 * @description: TODO
 *
 */
@Composable
fun TestCompositionLocal() {
    Text(LocalName.current)
}