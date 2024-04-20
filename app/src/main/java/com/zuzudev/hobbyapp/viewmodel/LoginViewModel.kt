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

class LoginViewModel(application: Application): AndroidViewModel(application) {

    val userLD = MutableLiveData<Users>()
    val loginStatLD = MutableLiveData<Boolean>()
    val passErrorLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(username: String, password:String) {
        passErrorLD.value = false
        loginStatLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://192.168.195.188/anmp/uts/login.php"

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            {
                Log.d("apilogin", it.toString())
                val obj = JSONObject(it)
                if (obj.getString("result") == "success") {
                    val data = obj.getJSONArray("data")
                    val sType = object : TypeToken<Users>() {}.type
                    userLD.value = Gson().fromJson(data[0].toString(), sType) as Users
                    Log.d("showprofile", userLD.value.toString())
                    loginStatLD.value = true
                    passErrorLD.value = false
                    Log.d("showlogin", it.toString())
                }
                else{
                    loginStatLD.value = false
                    passErrorLD.value = true
                }
            },
            {
                Log.d("showlogin", it.toString())
                loginStatLD.value = false
                passErrorLD.value = true
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
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

}