package com.example.a7ariketa.models

import android.content.Context
import android.util.Log
import kotlinx.serialization.json.Json

class Datuak {

    fun datuakKargatu(context: Context, fitx: String): List<Bideojokoa> {
        return try {
            val jsonString = context.assets.open(fitx)
                .bufferedReader()
                .use { it.readText() }

            Log.d("EL string: ", jsonString)

            Json { ignoreUnknownKeys = true }
                .decodeFromString<List<Bideojokoa>>(jsonString)
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

}