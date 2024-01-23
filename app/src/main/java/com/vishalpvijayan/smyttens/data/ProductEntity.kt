package com.vishalpvijayan.smyttens.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "product")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    val dbid: Int,
    val batch: Int,
    val id: Int,
    val brand: String,
    val name: String,
    val image: String,
    var items: Boolean,
    val isGrouped: Boolean
)