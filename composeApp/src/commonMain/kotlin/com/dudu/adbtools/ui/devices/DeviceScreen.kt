package com.dudu.adbtools.ui.devices

import adb_toolkit.composeapp.generated.resources.Res
import adb_toolkit.composeapp.generated.resources.add_devices
import adb_toolkit.composeapp.generated.resources.choose_device
import adb_toolkit.composeapp.generated.resources.device_info
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Devices
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.DeviceHub
import androidx.compose.material.icons.rounded.Devices
import androidx.compose.material.icons.rounded.LineStyle
import androidx.compose.material.icons.rounded.List
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dudu.adbtools.widgets.ExtendedButton
import com.dudu.adbtools.widgets.FilledCard
import com.dudu.adbtools.widgets.ListDialog
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

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
                "Choose a Device",
                listOf("Device 1", "Device 2", "Device 3"),
                {isShowChooseDialog = false},
                {}
            )
        }
    }
}