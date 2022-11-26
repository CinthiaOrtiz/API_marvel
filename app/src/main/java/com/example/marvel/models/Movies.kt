package com.example.marvel.models

import android.os.Parcelable
import java.util.*
import kotlinx.parcelize.Parcelize

@Parcelize

data class Movies (
    val id: Integer,
    val name: String,
    val description: String,
    val modified: Date,
    val thumbnail : Thumbnail
) : Parcelable