package com.dudu.adbtools.ui.devices

import adb_toolkit.composeapp.generated.resources.Res
import adb_toolkit.composeapp.generated.resources.add_devices
import adb_toolkit.composeapp.generated.resources.choose_device
import adb_toolkit.composeapp.generated.resources.device_info
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dudu.adbtools.widgets.ExtendedButton
import com.dudu.adbtools.widgets.FilledCard
import org.jetbrains.compose.resources.stringResource

@Composable
fun DeviceScreen() {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(320.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        item {
            FilledCard {
                Column {
                    Row {
                        Icon(
                            Icons.Rounded.Devices,
                            contentDescription = "devices"
                        )
                        Text(stringResource(Res.string.device_info))
                    }
                    ExtendedButton(
                        modifier = Modifier.align(Alignment.End),
                        onClick = {

                        },
                        icon = Icons.Rounded.List,
                        text = stringResource(Res.string.choose_device)
                    )
                }
            }
        }
    }
}