package com.zuzudev.hobbyapp.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface HobbyDao {
    //berita
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllBerita(vararg berita:Berita)

    @Query("SELECT * FROM berita")
    fun selectAllBerita(): List<Berita>

    @Query("SELECT * FROM berita WHERE id_berita= :id")
    fun selectBerita(id:Int): Berita

    @Delete
    fun deleteBerita(berita:Berita)
    @Update
    fun updateBerita(berita:Berita)




    //detail berita
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPage(vararg page:Page)

    @Query("SELECT * FROM page")
    fun selectAllPage(): List<Page>
    @Query("SELECT * FROM page WHERE id_berita=:idBerita")
    fun selectPageByBerita(idBerita:Int): List<Page>

    @Query("SELECT * FROM page WHERE id_page= :id")
    fun selectPage(id:Int): Page

    @Delete
    fun deletePage(page:Page)




    //user
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllUser(vararg user:Users)

    @Query("SELECT * FROM users")
    fun selectAllUser(): List<Users>

    @Query("SELECT * FROM users WHERE username= :id")
    fun selectUser(id:String): Users

    @Query("SELECT * FROM users WHERE username=:id AND password=:password")
    fun login(id:String, password:String): Users

    @Query("UPDATE users SET password=:password WHERE username = :username")
    fun updatePassword(username:String, password: String)

    @Delete
    fun deleteUser(user:Users)

//    @Query("UPDATE users SET password=:password WHERE username = :id")
//    fun update(id:String, password:String)

    @Update
    fun updateUser(user:Users)
}