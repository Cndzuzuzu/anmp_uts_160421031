package com.zuzudev.hobbyapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Users(
    @ColumnInfo(name="password")
    var password:String?,
    @ColumnInfo(name="email")
    var email:String?,
    @ColumnInfo(name="nama_depan")
    var namaDepan:String?,
    @ColumnInfo(name="nama_belakang")
    var namaBelakang:String?,
){
    @PrimaryKey(autoGenerate = false)
    var username:String = ""
}
