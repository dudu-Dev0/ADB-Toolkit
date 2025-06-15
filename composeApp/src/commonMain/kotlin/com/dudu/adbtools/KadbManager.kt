package com.dudu.adbtools

import com.flyfishxu.kadb.Kadb

object KadbManager {
    private var kadbConnections = mutableListOf<Device>()
    var choosingDevice: Device? = null

    fun connect(ip: String, port: Int): Kadb {
        val kadb = Kadb(ip, port)
        kadbConnections.add(kadb.getDevice())
        return kadb
    }

    fun disconnect(device: Device) {
        device.device.close()
        kadbConnections.remove(device)
    }

    fun devicesList(): List<Device> {
        return kadbConnections
    }

    fun Kadb.getDevice(): Device {
        val deviceInfo = shell("getprop").output.split("\n").associateBy({ it.split("=")[0] }, { it.split("=")[1] })
        deviceInfo.apply {
            val deviceCode = getOrDefault("ro.product.device", "null")
            val brand = getOrDefault("ro.product.brand", "null")
            val model = getOrDefault("ro.product.model", "null")
            val system = getOrDefault("ro.build.version.release", "null")
            return Device(this@getDevice, deviceCode, brand, model, system)
        }
    }

    data class Device(
        val device: Kadb,
        val deviceCode: String,
        val brand: String,
        val model: String,
        val system: String
    )
}