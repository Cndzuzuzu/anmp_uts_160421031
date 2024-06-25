package com.zuzudev.hobbyapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Page(
    @ColumnInfo(name="id_berita")
    var idBerita:Int,
    @ColumnInfo(name="konten_berita")
    var kontenBerita: String,
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id_page")
    var idPage: Int = 0
}
