package com.dudu.adbtools

import com.funny.data_saver.core.DataSaverInterface
import com.funny.data_saver.core.DataSaverProperties

actual object AppConfig {
    private val userHome = System.getProperty("user.home")
    private const val projectName = "ADB-Toolkit"
    actual val dataSaver: DataSaverInterface = DataSaverProperties("$userHome/$projectName/data_saver.properties")
}