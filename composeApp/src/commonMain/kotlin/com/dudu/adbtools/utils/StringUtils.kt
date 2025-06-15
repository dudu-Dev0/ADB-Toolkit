package com.dudu.adbtools.utils

fun String.isValidIpAddress(): Boolean {
    // IPv4 正则：4组0-255的数字，用点分隔
    val ipv4Regex = """^(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})$""".toRegex()
    // IPv6 正则：8组1-4位十六进制数（允许缩写 ::）
    val ipv6Regex = """^([0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$""".toRegex()
    val ipv6AbbrRegex =
        """^(([0-9a-fA-F]{1,4}:)*[0-9a-fA-F]{1,4})?::(([0-9a-fA-F]{1,4}:)*[0-9a-fA-F]{1,4})?$""".toRegex()

    return when {
        matches(ipv4Regex) -> {
            split(".").all { segment ->
                segment.toIntOrNull() in 0..255 && !segment.startsWith("0")
                        || segment == "0" // 允许单独的 0
            }
        }

        matches(ipv6Regex) || matches(ipv6AbbrRegex) -> true
        else -> false
    }
}

fun String.isNumericInRange(range: IntRange): Boolean {
    val number = this.toIntOrNull() ?: return false
    return number in range
}