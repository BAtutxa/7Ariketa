package com.example.a7ariketa.models

import android.content.Context
import android.util.Log
import kotlinx.serialization.json.Json

class Datuak {
    fun datuakLortu (context: Context): List<Pelikula>{
        return try {
            val jsonString = context.assets.open("fitx.json")
                .bufferedReader()
                .use { it.readText() }

            Log.d ("EL string: ",jsonString)

            Json { ignoreUnknownKeys = true }
                .decodeFromString<List<Pelikula>>(jsonString)
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }

    }
}