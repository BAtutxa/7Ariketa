package com.example.classdata.models

import kotlinx.serialization.Serializable

@Serializable
data class Bideojokoak(
    val id: Int,
    val titulua: String,
    val mota: String,
    val plataforma: String
)
