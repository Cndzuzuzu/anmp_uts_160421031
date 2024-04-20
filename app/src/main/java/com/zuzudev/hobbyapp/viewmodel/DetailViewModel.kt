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
import org.json.JSONObject

class DetailViewModel(application: Application): AndroidViewModel(application)
{
    val listPage = MutableLiveData<ArrayList<Page>>()
    val beritaLD = MutableLiveData<Berita>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null
    fun fetch(idBerita:Int) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://192.168.195.188/anmp/uts/beritaById.php?idBerita="+idBerita.toString()

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                Log.d("apiresult2", it.toString())
                val obj = JSONObject(it)
                if (obj.getString("result") == "OK") {
                    val data = obj.getJSONArray("data")
                    val sType = object: TypeToken<Berita>(){}.type
                    beritaLD.value = Gson().fromJson(data[0].toString(), sType) as Berita
                    Log.d("showvoley2", beritaLD.value.toString())
                    detailBerita(idBerita)

                }
            },
            {
                Log.d("showvoley", it.toString())
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    fun detailBerita(idBerita:Int) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://192.168.195.188/anmp/uts/detailberita.php?idBerita="+idBerita.toString()

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                Log.d("apiresult3", it.toString())
                val obj = JSONObject(it)
                if (obj.getString("result") == "OK") {
                    val data = obj.getJSONArray("data")
                    val sType = object: TypeToken<List<Page>>(){}.type
                    listPage.value = Gson().fromJson(data.toString(), sType) as ArrayList<Page>?
                    Log.d("showvoley3", listPage.value.toString())

//                    val data = obj.getJSONArray("data")
//                    val sType = object: TypeToken<List<Berita>>(){}.type
////                    val result = Gson().fromJson(data.toString(), sType)
//                    beritaLD.value = Gson().fromJson(data.toString(), sType) as ArrayList<Berita>?
//                    loadingLD.value = false
//                    Log.d("showvoley", beritaLD.value.toString())
                }
            },
            {
                Log.d("showvoley", it.toString())
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }


}