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
import org.json.JSONObject

class BeritaListViewModel(application: Application): AndroidViewModel(application)
{
    val beritaLD = MutableLiveData<ArrayList<Berita>>()
    val studentLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null


    fun refresh() {
        loadingLD.value = true
        studentLoadErrorLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://192.168.195.188/anmp/uts/berita.php"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                Log.d("apilistberita", it.toString())
                val obj = JSONObject(it)
                if (obj.getString("result") == "OK") {
                    val data = obj.getJSONArray("data")
                    val sType = object: TypeToken<List<Berita>>(){}.type
//                    val result = Gson().fromJson(data.toString(), sType)
                    beritaLD.value = Gson().fromJson(data.toString(), sType) as ArrayList<Berita>?
                    loadingLD.value = false
                    Log.d("showlistberita", beritaLD.value.toString())
                }
            },
            {
                Log.d("showlistberita", it.toString())
                studentLoadErrorLD.value = false
                loadingLD.value = false
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }



}