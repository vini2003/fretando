package dev.vini2003.fretando.client.properties.misc

import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.util.*
import kotlin.reflect.KProperty

class PropertiesDelegate(
    private val properties: Properties,
    private val propertiesFile: File,
    private val name: String,
    private val defaultValue: String
) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return properties.getProperty(name, defaultValue)
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        properties.setProperty(name, value)

        writePropertiesFile(propertiesFile, properties)
    }
}

private fun readPropertiesFile(file: File): Properties {
    val props = Properties()

    FileReader(file).use {
        props.load(it)
    }

    return props
}

private fun writePropertiesFile(file: File, properties: Properties) {
    FileWriter(file).use {
        properties.store(it, null)
    }
}

private fun getAppDataConfigPath(): File {
    val osName = System.getProperty("os.name").toLowerCase()
    val homeDir = System.getProperty("user.home")

    val appDir: File = when {
        osName.contains("win") -> {
            val appDataPath = System.getenv("APPDATA")
            File(appDataPath, "Fretando")
        }

        osName.contains("mac") || osName.contains("nix") || osName.contains("nux") -> {
            File(homeDir, ".Fretando")
        }

        else -> throw UnsupportedOperationException("Unsupported operating system: $osName")
    }

    if (!appDir.exists()) {
        appDir.mkdir()
    }

    return File(appDir, "config.properties")
}

private val PropertiesFile = getAppDataConfigPath()

private val Properties =
    if (PropertiesFile.exists()) {
        readPropertiesFile(PropertiesFile)
    } else {
        Properties()
    }

fun property(name: String, defaultValue: String) = PropertiesDelegate(Properties,  PropertiesFile, name, defaultValue)