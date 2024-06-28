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
import com.zuzudev.hobbyapp.model.Users
import com.zuzudev.hobbyapp.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.json.JSONObject
import kotlin.coroutines.CoroutineContext

class LoginViewModel(application: Application): AndroidViewModel(application), CoroutineScope {

    val userLD = MutableLiveData<Users>()
    val loginStatLD = MutableLiveData<Boolean>()
    val passErrorLD = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun login(username: String, password:String) {
        passErrorLD.value = false
        loginStatLD.value = false
        launch {
            val db = buildDb(getApplication())
            userLD.postValue(db.hobbyDao().login(username, password))
            Log.d("login", userLD.value.toString())
//            passErrorLD.postValue(userLD.value == null)
//            loginStatLD.postValue(true)

        }
        Log.d("loginStat", passErrorLD.value.toString() + loginStatLD.value.toString())
    }
    override fun onCleared() {
        super.onCleared()
//        queue?.cancelAll(TAG)
    }

}