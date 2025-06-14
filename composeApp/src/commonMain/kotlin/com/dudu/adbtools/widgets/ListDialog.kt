package com.dudu.adbtools.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun ListDialog(
    title: String,
    items: List<String>,
    emptyText: String = "暂无数据",
    btn1Text: String = "取消",
    btn2Text: String = "确定",
    btn1ClickAction: () -> Unit = {},
    btn2ClickAction: () -> Unit = {},
    onDismiss: () -> Unit,
    onItemClick: (String) -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,

        ) {
        Card(
            modifier = Modifier
                .height(300.dp)
                .padding(16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(24.dp)
            )
            var nowClicked by remember { mutableStateOf("") }
            if (items.isEmpty()) {
                Text(
                    text = emptyText,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                )
            } else {
                LazyColumn {
                    items(items) {
                        Box(
                            modifier = Modifier.fillMaxWidth()
                                .clickable(onClick = {
                                    nowClicked = it
                                })

                        ) {
                            Text(
                                text = it,
                                modifier = Modifier
                                    .padding(16.dp)
                                    .align(Alignment.CenterStart),
                            )
                            RadioButton(
                                modifier = Modifier
                                    .align(Alignment.CenterEnd),
                                selected = nowClicked == it,
                                onClick = {
                                    nowClicked = it
                                    onItemClick(it)
                                }
                            )
                        }
                    }
                }
            }
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Row(
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.BottomEnd),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(
                        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 16.dp),
                        onClick = btn1ClickAction
                    ) {
                        Text(btn1Text)
                    }
                    TextButton(
                        modifier = Modifier.padding(0.dp, 0.dp, 16.dp, 16.dp),
                        onClick = btn2ClickAction
                    ) {
                        Text(btn2Text)
                    }
                    }
                }
            }

    }
}
