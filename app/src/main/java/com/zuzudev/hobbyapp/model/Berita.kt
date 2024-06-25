package com.zuzudev.hobbyapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Berita(
    @ColumnInfo(name="judul_berita")
    var judulBerita:String,
    @ColumnInfo(name="deskripsi")
    var deskripsi:String,
    @ColumnInfo(name="id_pembuat")
    var idPembuat: String,
    @ColumnInfo(name="gambar")
    var gambar:String,
    @ColumnInfo(name="tanggal")
    var tanggal:String,
//    var Pages:List<Page>?,
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id_berita")
    var idBerita: Int = 0
}
