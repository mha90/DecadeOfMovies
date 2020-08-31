package me.mhabulazm.decadeofmovies.utils


import android.content.Context
import androidx.annotation.RawRes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.nio.charset.Charset

suspend fun Context.readDataFromRawFile(@RawRes rawJsonFileId: Int) =
    withContext(Dispatchers.IO) {
        val content: String?
        try {
            val inputStream = this@readDataFromRawFile.resources.openRawResource(rawJsonFileId)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            content = String(buffer, Charset.forName("UTF-8"))
        } catch (ex: IOException) {
            return@withContext null
        }
        return@withContext content
    }

fun Context.readRawFile(@RawRes rawJsonFileId: Int): String? {
    val content: String?
    try {
        val inputStream = resources.openRawResource(rawJsonFileId)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        content = String(buffer, Charset.forName("UTF-8"))
    } catch (ex: IOException) {
        return null
    }
    return content
}