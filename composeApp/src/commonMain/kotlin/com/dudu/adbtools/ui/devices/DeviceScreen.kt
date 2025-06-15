package com.dudu.adbtools.ui.devices

import adb_toolkit.composeapp.generated.resources.*
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Devices
import androidx.compose.material.icons.rounded.List
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.dudu.adbtools.KadbManager
import com.dudu.adbtools.utils.isNumericInRange
import com.dudu.adbtools.utils.isValidIpAddress
import com.dudu.adbtools.widgets.ExtendedButton
import com.dudu.adbtools.widgets.FilledCard
import com.dudu.adbtools.widgets.ListDialog
import org.jetbrains.compose.resources.stringResource

@Composable
fun DeviceScreen() {
    var isShowChooseDialog by remember { mutableStateOf(false) }
    var isShowConnectDialog by remember { mutableStateOf(false) }
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
                onItemClick = {},
                btn1Text = stringResource(Res.string.add_devices),
                btn2Text = stringResource(Res.string.confirm),
                btn1ClickAction = {
                    isShowConnectDialog = true
                }
            )
        }
        if (isShowConnectDialog) {
            ConnectDeviceDialog {
                isShowConnectDialog = false
            }
        }

    }
}

@Composable
fun ConnectDeviceDialog(onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .animateContentSize(),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                Modifier.padding(24.dp, 24.dp)
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 4.dp),
                    text = stringResource(Res.string.add_devices),
                    style = MaterialTheme.typography.titleLarge,
                )
                var ip by remember { mutableStateOf("") }
                var port by remember { mutableStateOf("") }
                var pairCode by remember { mutableStateOf("") }

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth()
                        .padding(0.dp, 4.dp),
                    value = ip,
                    onValueChange = {
                        ip = it
                    },
                    placeholder = {
                        Text(stringResource(Res.string.ip))
                    },
                    isError = !ip.isValidIpAddress()
                )

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth()
                        .padding(0.dp, 4.dp),
                    value = port,
                    onValueChange = {
                        port = it
                    }, placeholder = {
                        Text(stringResource(Res.string.port))
                    },
                    isError = !port.isNumericInRange(0..99999)
                )
                var isPairMode by remember { mutableStateOf(false) }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = isPairMode,
                        onCheckedChange = {
                            isPairMode = !isPairMode
                        }
                    )
                    Text(stringResource(Res.string.pair_device))
                }
                AnimatedVisibility(
                    visible = isPairMode,
                    enter = fadeIn() + slideInVertically(),
                    exit = fadeOut() + slideOutVertically()
                ) {
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth()
                            .padding(0.dp, 4.dp),
                        value = pairCode,
                        onValueChange = {
                            pairCode = it
                        },
                        placeholder = {
                            Text(stringResource(Res.string.pair_code))
                        },
                        isError = !pairCode.isNumericInRange(100000..999999)
                    )
                }
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .wrapContentSize()
                            .align(Alignment.BottomEnd),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(
                            onClick = {

                            }
                        ) {
                            Text(stringResource(if (isPairMode) Res.string.pair else Res.string.connect))
                        }
                    }
                }
            }

        }
    }
}