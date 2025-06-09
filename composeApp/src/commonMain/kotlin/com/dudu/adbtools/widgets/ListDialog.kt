package com.dudu.adbtools.widgets

import androidx.annotation.Size
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ListDialog(
    title: String,
    items: List<String>,
    onDismiss: () -> Unit,
    onItemClick: (String) -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,

    ) {
        Card(
            modifier = Modifier
                .height(300.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp)
        ){
            Text(
                text = title,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(16.dp)
                    .clickable(onClick = {
                        onDismiss()
                    })
            )
            var nowClicked by remember{ mutableStateOf("") }
            LazyColumn {
                items(items){
                    Box(
                        modifier = Modifier.fillMaxWidth()
                            .clickable(onClick = {
                                nowClicked = it
                            })

                    ){
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
    }
}
