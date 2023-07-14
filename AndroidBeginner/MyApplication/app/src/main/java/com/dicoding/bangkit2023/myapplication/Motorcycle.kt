package com.dicoding.bangkit2023.myapplication

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Motorcycle(
    val name: String,
    val harga: String,
    val info: String,
    val description: String,
    val photo: Int
) : Parcelable