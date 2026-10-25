package com.example.a7ariketa.models

import kotlinx.serialization.Serializable

@Serializable
data class Bideojokoa(
    val id: Int,
    val titulua: String,
    val mota: String,
    val plataforma: String,
    val irudia: String = ""
)
