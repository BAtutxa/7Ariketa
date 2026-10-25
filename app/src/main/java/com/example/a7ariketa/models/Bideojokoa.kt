package com.example.a7ariketa.models

import kotlinx.serialization.Serializable

@Serializable
data class Bideojokoa(
    val id: String,
    val titulua: String,
    val mota: String,
    val plataforma: String
) {

}