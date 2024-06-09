package com.example.digikala.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.digikala.data.model.basket.CartItem
import com.example.digikala.data.model.favorites.FavoriteItem
import com.example.digikala.data.model.zarinpal.RefId
import com.example.digikala.utils.Constants.FAVORITE_ITEM_TABLE

@Database(
    entities = [CartItem::class, RefId::class, FavoriteItem::class],
    version = 3,
    exportSchema = false
)
abstract class DigikalaDatabase: RoomDatabase() {

    abstract fun cartDao(): CartDao
    abstract fun refIdDao(): RefIdDao
    abstract fun favoritesDao(): FavoritesDao

    companion object {
        val MIGRATION_2_3: Migration = object : Migration(2, 3) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    "CREATE TABLE IF NOT EXISTS `$FAVORITE_ITEM_TABLE` " +
                            "(`itemId` TEXT NOT NULL, " +
                            "`discountPercent` INTEGER NOT NULL, " +
                            "`image` TEXT NOT NULL, " +
                            "`name` TEXT NOT NULL, " +
                            "`price` INTEGER NOT NULL, " +
                            "`seller` TEXT NOT NULL, " +
                            "`star` REAL NOT NULL, " +
                            "PRIMARY KEY(`itemId`))"
                )
            }
        }
    }

}