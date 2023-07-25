package com.example.whitedragonvpn.data.resources

import android.content.Context
import com.example.whitedragonvpn.R
import com.example.whitedragonvpn.ioc.ApplicationScope
import javax.inject.Inject

@ApplicationScope
class AndroidResourceProvider @Inject constructor(private val applicationContext: Context) :
    ResourceProvider {
    override fun getSystemLocale(): String =
        applicationContext.resources.configuration.locales[0].language

    override val systemLanguagePermissionCode: String
        get() =
            applicationContext.resources.getTextArray(R.array.language_values)[0].toString()

}