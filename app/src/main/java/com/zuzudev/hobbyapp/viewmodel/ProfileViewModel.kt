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
import org.json.JSONObject

class ProfileViewModel(application: Application): AndroidViewModel(application) {
//    val listPage = MutableLiveData<ArrayList<Page>>()
    val userLD = MutableLiveData<Users>()
    val updatePassLD = MutableLiveData<Boolean>()
    val updateDataLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(username: String) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://192.168.195.188/anmp/uts/userById.php"

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            {
                Log.d("apiprofile", it.toString())
                val obj = JSONObject(it)
                if (obj.getString("result") == "OK") {
                    val data = obj.getJSONArray("data")
                    val sType = object : TypeToken<Users>() {}.type
                    userLD.value = Gson().fromJson(data[0].toString(), sType) as Users
                    Log.d("showprofile", userLD.value.toString())

                }
            },
            {
                Log.d("showprofile", it.toString())
            })
        {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["username"] = username
                return params
            }
        }
        stringRequest.tag = TAG
        queue?.add(stringRequest)

    }

    fun updatePassword(username: String, password:String){
//        updatePassLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://192.168.195.188/anmp/uts/updatePassword.php"

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            {
                Log.d("apichangepass", it.toString())
                val obj = JSONObject(it)
                if (obj.getString("result") == "success") {
                    updatePassLD.value = true
                }
            },
            {
                Log.d("showpass", it.toString())
                updatePassLD.value = false
            })
        {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["username"] = username
                params["password"] = password
                return params
            }
        }
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    fun updateData(username:String, namaDepan:String, namaBelakang:String ){
//        updateDataLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://192.168.195.188/anmp/uts/updateData.php"

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            {
                Log.d("apichangedata", it.toString())
                val obj = JSONObject(it)
                if (obj.getString("result") == "success") {
                    updateDataLD.value = true
                }
            },
            {
                Log.d("showchangedata", it.toString())
                updateDataLD.value = false
            })
        {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["username"] = username
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