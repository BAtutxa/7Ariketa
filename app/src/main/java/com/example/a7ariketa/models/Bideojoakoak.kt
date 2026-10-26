package com.example.a7ariketa.models

import kotlinx.serialization.Serializable

@Serializable
data class Bideojoakoak (
    val id : Int,
    val titulua: String,
    val mota: String,
    val plataforma: String
)
