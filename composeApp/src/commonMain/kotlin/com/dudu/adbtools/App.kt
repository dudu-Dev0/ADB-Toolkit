package com.dudu.adbtools

import adb_toolkit.composeapp.generated.resources.Res
import adb_toolkit.composeapp.generated.resources.about
import adb_toolkit.composeapp.generated.resources.device
import adb_toolkit.composeapp.generated.resources.files
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Devices
import androidx.compose.material.icons.rounded.FileOpen
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import com.dudu.adbtools.theme.AppTheme
import com.dudu.adbtools.ui.about.AboutScreen
import com.dudu.adbtools.ui.devices.DeviceScreen
import com.dudu.adbtools.ui.files.FileScreen
import com.funny.data_saver.core.LocalDataSaver
import org.jetbrains.compose.resources.stringResource


data class Screen(
    val title: String,
    val icon: ImageVector,
    val content: @Composable () -> Unit
)



@Composable
fun App() {
    val screens = listOf(
        Screen(stringResource(Res.string.device), Icons.Rounded.Devices, { DeviceScreen() }),
        Screen(stringResource(Res.string.files), Icons.Rounded.FileOpen, { FileScreen() }),
        Screen(stringResource(Res.string.about), Icons.Rounded.Info, { AboutScreen() })
    )
    CompositionLocalProvider(LocalDataSaver provides AppConfig.dataSaver) {
        AppTheme {
            var screenWidth by remember { mutableStateOf(0) }
            var screenHeight by remember { mutableStateOf(0) }
            var selectedScreen by remember { mutableStateOf(screens[0]) }
            Scaffold(
                modifier = Modifier
                    .onGloballyPositioned {
                        screenWidth = it.size.width
                        screenHeight = it.size.height
                    },
                content = {
                    Row(Modifier.padding(it).consumeWindowInsets(WindowInsets.systemBars)) {
                        if (screenWidth >= screenHeight) {
                            NavigationRail(Modifier.padding(6.dp)) {
                                screens.forEach {
                                    NavigationRailItem(
                                        icon = { Icon(it.icon, contentDescription = it.title) },
                                        label = { Text(it.title) },
                                        alwaysShowLabel = false,
                                        selected = selectedScreen == it,
                                        onClick = {
                                            selectedScreen = it
                                        }
                                    )
                                }
                            }
                        }
                        selectedScreen.content()
                    }
                },
                bottomBar = {
                    if (screenHeight > screenWidth) {
                        NavigationBar {
                            screens.forEach {
                                NavigationBarItem(
                                    icon = { Icon(it.icon, contentDescription = it.title) },
                                    label = { Text(it.title) },
                                    alwaysShowLabel = false,
                                    selected = selectedScreen == it,
                                    onClick = {
                                        selectedScreen = it
                                    }
                                )
                            }
                        }
                    }
                }
            )
        }
    }
}
