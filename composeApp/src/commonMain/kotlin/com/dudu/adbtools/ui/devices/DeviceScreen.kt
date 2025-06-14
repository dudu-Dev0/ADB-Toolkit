package com.dudu.adbtools.ui.devices

import adb_toolkit.composeapp.generated.resources.Res
import adb_toolkit.composeapp.generated.resources.choose_device
import adb_toolkit.composeapp.generated.resources.device_info
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Devices
import androidx.compose.material.icons.rounded.List
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dudu.adbtools.KadbManager
import com.dudu.adbtools.widgets.ExtendedButton
import com.dudu.adbtools.widgets.FilledCard
import com.dudu.adbtools.widgets.ListDialog
import org.jetbrains.compose.resources.stringResource

@Composable
fun DeviceScreen() {
    var isShowChooseDialog by remember { mutableStateOf(false) }
    Box {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(320.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            item {
                FilledCard {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Rounded.Devices,
                                contentDescription = "devices"
                            )
                            Text(stringResource(Res.string.device_info))
                            Box(Modifier.fillMaxWidth()) {
                                ExtendedButton(
                                    modifier = Modifier.align(Alignment.CenterEnd),
                                    onClick = {
                                        isShowChooseDialog = true
                                    },
                                    icon = Icons.Rounded.List,
                                    text = stringResource(Res.string.choose_device)
                                )
                            }

                        }
                    }
                }
            }
        }

        if (isShowChooseDialog) {
            ListDialog(
                stringResource(Res.string.choose_device),
                KadbManager.devicesList().map { it.model },
                onDismiss = { isShowChooseDialog = false },
                onItemClick = {}
            )
        }
    }
}