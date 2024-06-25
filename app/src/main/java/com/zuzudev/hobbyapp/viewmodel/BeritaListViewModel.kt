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
import com.zuzudev.hobbyapp.model.HobbyDao
import com.zuzudev.hobbyapp.model.HobbyDatabase
import com.zuzudev.hobbyapp.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONObject
import kotlin.coroutines.CoroutineContext

class BeritaListViewModel(application: Application): AndroidViewModel(application), CoroutineScope
{
    val beritaLD = MutableLiveData<ArrayList<Berita>>()
    val beritaLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO


    fun refresh() {
        loadingLD.value = true
        beritaLoadErrorLD.value = false
        launch {
            val db = buildDb(getApplication())
            beritaLD.postValue(db.hobbyDao().selectAllBerita())
            loadingLD.postValue(false)
        }
    }


    override fun onCleared() {
        super.onCleared()
//        queue?.cancelAll(TAG)
    }



}