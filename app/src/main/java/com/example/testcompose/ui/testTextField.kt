package com.example.testcompose.ui

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation

/**
 *
 * @date   :
 * @author : wanghailong
 *
 * @description: TODO
 *
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TestTextField() {
    Column {
        val focusManager = LocalFocusManager.current
        var userName by remember { mutableStateOf("") }
        var pwd by remember { mutableStateOf("") }

        BasicTextField(
            modifier = Modifier.background(color = Color.White),
            value = userName,
            onValueChange = { newValue ->
                userName = newValue
            },
            //
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onDone = { Log.e("whl ***", "onDone") },
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                    Log.e("whl ***", "onNext")
                },
                onSearch = { Log.e("whl ***", "onSearch") },
                onSend = { Log.e("whl ***", "onSend") }
            )
        )

        TextField(
            modifier = Modifier.background(color = Color.White),
            value = userName,
            onValueChange = { newValue ->
                userName = newValue
            },
            label = {
                Text("TextField")
            },
            placeholder = {
                Text("请输入手机号")
            },
            //
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onDone = { Log.e("whl ***", "onDone") },
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                    Log.e("whl ***", "onNext")
                },
                onSearch = { Log.e("whl ***", "onSearch") },
                onSend = { Log.e("whl ***", "onSend") }
            )
        )

        val keyboardController = LocalSoftwareKeyboardController.current

        OutlinedTextField(
            modifier = Modifier,
            value = pwd,
            onValueChange = {
                pwd = it
            },
            label = {
                Text("OutlinedTextField")
            },
            //相当于 hint
            placeholder = {
                Text("请输入密")
            },
            //左边的图片
            leadingIcon = {
                Image(
                    painter = painterResource(id = androidx.core.R.drawable.ic_call_answer),
                    contentDescription = "icon_pwd"
                )
            },
            //右边的图片,随输入框内容显示或隐藏 点击后删除输入框中的内容
            trailingIcon = {
                AnimatedVisibility(visible = pwd.length > 0) {
                    Image(
                        modifier = Modifier.clickable { pwd = "" },
                        painter = painterResource(id = androidx.core.R.drawable.ic_call_answer),
                        contentDescription = "icon_pwd"
                    )
                }
            },
            //输入密码显示为 '*'
            visualTransformation = PasswordVisualTransformation('*'),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            //按键事件
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
//                    keyboardController?.hide()
                    Log.e("whl ***", "onDone")
                },
                onNext = { Log.e("whl ***", "onNext") },
                onSearch = { Log.e("whl ***", "onSearch") },
                onSend = { Log.e("whl ***", "onSend") }
            ),
            //单行
            singleLine = true,
            //
            maxLines = 1
        )
    }
}

