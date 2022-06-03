package com.junka.glo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.junka.glo.data.local.dao.ProductDao
import com.junka.glo.data.local.model.Product

@Database(
    entities = [Product::class],
    exportSchema = false,
    version = 1
)
abstract class LocalDataBase : RoomDatabase() {

    abstract fun productDao() : ProductDao
}