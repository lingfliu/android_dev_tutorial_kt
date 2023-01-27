package io.issc.android_dev_tutorial_kt.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_info")
class UserInfo(id:Long?, name:String, password:String, descrip:String?) {
    @PrimaryKey
    val id = id
    @ColumnInfo(name = "username")
    val name = name
    @ColumnInfo(name = "password")
    val password = password
    @ColumnInfo(name = "descrip")
    val descrip = descrip
}