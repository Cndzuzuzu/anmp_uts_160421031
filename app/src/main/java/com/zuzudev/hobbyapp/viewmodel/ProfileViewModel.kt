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
import com.zuzudev.hobbyapp.model.Users
import com.zuzudev.hobbyapp.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONObject
import kotlin.coroutines.CoroutineContext

class ProfileViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
//    val listPage = MutableLiveData<ArrayList<Page>>()
    val userLD = MutableLiveData<Users>()
    val updatePassLD = MutableLiveData<Boolean>()
    val updateDataLD = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO


    fun fetch(username: String) {
        launch {
            val db = buildDb(getApplication())
            userLD.postValue(db.hobbyDao().selectUser(username))
        }

    }

    fun updateUser(user:Users){
        launch {
            val db = buildDb(getApplication())
            db.hobbyDao().updateUser(user)
        }
    }

//    fun updateData(username:String, namaDepan:String, namaBelakang:String ){
//
//    }

}