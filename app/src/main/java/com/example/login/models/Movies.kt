package com.example.login.models

import java.util.*

data class Movies (
    val id: Integer,
    val name: String,
    val description: String,
    val modified: Date,
    val thumbnail : Thumbnail
)