package com.example.whitedragonvpn.data.settings.store

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.example.whitedragonvpn.store.protos.Settings
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

@Suppress("BlockingMethodInNonBlockingContext")
object SettingsSerializer : Serializer<Settings> {
    override val defaultValue: Settings = Settings.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): Settings {
        try {
            return Settings.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto", exception)
        }
    }

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override suspend fun writeTo(settings: Settings, output: OutputStream) {
        settings.writeTo(output)
    }
}