package com.vishalpvijayan.smyttens.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "button")
data class ButtonEntity(
    @PrimaryKey(autoGenerate = true)
    val dbid: Int,
    val id: Int,
    val name: String,
    val clicked: Boolean
)