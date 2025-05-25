package com.dudu.adbtools

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
import com.dudu.adbtools.ui.devices.DeviceScreen
import com.funny.data_saver.core.LocalDataSaver


data class Screen(
    val title: String,
    val icon: ImageVector,
    val content: @Composable () -> Unit
)

val screens = listOf(
    Screen("device", Icons.Rounded.Devices, { DeviceScreen() }),
    Screen("files", Icons.Rounded.FileOpen, { DeviceScreen() }),
    Screen("about", Icons.Rounded.Info, { DeviceScreen() })
)

@Composable
fun App() {
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
