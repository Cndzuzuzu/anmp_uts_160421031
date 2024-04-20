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
import org.json.JSONObject

class RegisterViewModel(application: Application): AndroidViewModel(application) {

    val userLD = MutableLiveData<Users>()
    val registerLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(username: String, password:String, email:String, namaDepan:String, namaBelakang:String) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://192.168.195.188/anmp/uts/register.php"

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            {
                Log.d("apiregis", it.toString())
                val obj = JSONObject(it)
                if (obj.getString("result") == "success") {
                    Log.d("showregis", it.toString())
                    registerLD.value = true
                }
            },
            {
                Log.d("showregis", it.toString())
                registerLD.value = false
            })
        {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["username"] = username
                params["password"] = password
                params["email"] = email
                params["namaDepan"] = namaDepan
                params["namaBelakang"] = namaBelakang
                return params
            }
        }
        stringRequest.tag = TAG
        queue?.add(stringRequest)

    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

}