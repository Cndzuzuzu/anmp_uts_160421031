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
    val oldPass = MutableLiveData<String>()
    val updatePassLD = MutableLiveData<Boolean>()
    val updateDataLD = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO


    fun fetch(username: String) {
//        var data:Users = Users("","","","")
        launch {
            val db = buildDb(getApplication())
            userLD.postValue(db.hobbyDao().selectUser(username))
            Log.d("profileData", userLD.value.toString())
        }
//        userLD.value = data
//        Log.d("profile", userLD.value.toString())
    }

    fun updateUser(user:Users){
        launch {
            val db = buildDb(getApplication())
            db.hobbyDao().updateUser(user)
            updateDataLD.postValue(true)
            Log.d("profileUpdateStat", updateDataLD.value.toString())
        }
    }

    fun updatePassword(username:String, newPass:String ){
        launch {
            val db = buildDb(getApplication())
            db.hobbyDao().updatePassword(username, newPass)
            updatePassLD.postValue(true)
            Log.d("profileUpdatePass", updateDataLD.value.toString())
        }
    }

}