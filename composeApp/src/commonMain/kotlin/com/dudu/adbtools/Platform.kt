package com.dudu.adbtools

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform