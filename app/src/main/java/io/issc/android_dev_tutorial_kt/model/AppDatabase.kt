package io.issc.android_dev_tutorial_kt.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserInfo::class], version = 2)
abstract class AppDatabase:RoomDatabase(){
    abstract fun userInfoDao():UserInfoDao
}