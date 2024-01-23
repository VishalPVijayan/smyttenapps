package com.vishalpvijayan.smyttens.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vishalpvijayan.smyttens.dao.MyDao
import com.vishalpvijayan.smyttens.data.ButtonEntity
import com.vishalpvijayan.smyttens.data.ProductEntity


@Database(entities = [ProductEntity::class, ButtonEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun myDao(): MyDao
}