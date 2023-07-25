package com.example.whitedragonvpn.data.resources

interface ResourceProvider {
    fun getSystemLocale(): String
    val systemLanguagePermissionCode: String
}