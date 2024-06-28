package com.zuzudev.hobbyapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.zuzudev.hobbyapp.model.Berita
import com.zuzudev.hobbyapp.model.Page
import com.zuzudev.hobbyapp.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONObject
import kotlin.coroutines.CoroutineContext

class DetailViewModel(application: Application): AndroidViewModel(application), CoroutineScope
{
    val listPage = MutableLiveData<List<Page>>()
    val pageLD = MutableLiveData<Page>()
    val beritaLD = MutableLiveData<Berita>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun fetch(idBerita:Int) {
        launch {
            val db = buildDb(getApplication())
            beritaLD.postValue(db.hobbyDao().selectBerita(idBerita))
            Log.d("beritaData", beritaLD.value.toString())
        }
        detailBerita(idBerita)
    }

    fun detailBerita(idBerita:Int) {
        launch {
            val db = buildDb(getApplication())
            listPage.postValue(db.hobbyDao().selectPageByBerita(idBerita))
            Log.d("beritaDataDetail", listPage.value.toString())

        }

    }

    fun fetchPage(idPage:Int) {
        launch {
            val db = buildDb(getApplication())
            pageLD.postValue(db.hobbyDao().selectPage(idPage))
            Log.d("beritaPage", pageLD.value.toString())
        }
    }
    fun addPage(page:Page){
        val db = buildDb(getApplication())
        db.hobbyDao().insertAllPage(page)
    }
    override fun onCleared() {
        super.onCleared()

    }


}