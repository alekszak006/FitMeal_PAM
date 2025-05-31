package com.fitmeal.app.database.entites

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
class Product (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val calories: Int,
    val protein: Double,
    val carbs: Double,
    val fat: Double
)

