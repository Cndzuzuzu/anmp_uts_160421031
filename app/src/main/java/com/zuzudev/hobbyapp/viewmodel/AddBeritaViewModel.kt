package com.zuzudev.hobbyapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.zuzudev.hobbyapp.model.Berita
import com.zuzudev.hobbyapp.model.Page
import com.zuzudev.hobbyapp.model.Users
import com.zuzudev.hobbyapp.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class AddBeritaViewModel(application: Application): AndroidViewModel(application), CoroutineScope {

    val beritaLD = MutableLiveData<Berita>()
    val pageLD = MutableLiveData<Page>()
    val userLD = MutableLiveData<Users>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun addBerita(berita:Berita) {
        launch {
//            val db = TodoDatabase.buildDatabase(getApplication())
            val db = buildDb(getApplication())
            db.hobbyDao().insertAllBerita(berita)
        }
    }
    fun fetchUser(username: String) {
        launch {
            val db = buildDb(getApplication())
            userLD.postValue(db.hobbyDao().selectUser(username))
            Log.d("username", userLD.value.toString())
        }
    }
    fun addPage(page: Page){
        launch {
            val db = buildDb(getApplication())
            db.hobbyDao().insertAllPage(page)
        }

    }

    fun fetch(idberita:Int) {
        launch {
            val db = buildDb(getApplication())
            beritaLD.postValue(db.hobbyDao().selectBerita(idberita))
        }
    }

    fun update(berita:Berita) {
        launch {
            val db = buildDb(getApplication())
            db.hobbyDao().updateBerita(berita)
        }
    }
    override fun onCleared() {
        super.onCleared()
//        queue?.cancelAll(TAG)
    }
}