package com.example.a7ariketa.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Pelikula(
    @SerialName("id") val identifikazioa : Int,
    val titulua : String,
    val mota: String,
    val plataforma : String
)