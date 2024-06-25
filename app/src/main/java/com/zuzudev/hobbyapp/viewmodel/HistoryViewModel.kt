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

class HistoryViewModel(application: Application): AndroidViewModel(application) {

    val beritaLD = MutableLiveData<ArrayList<Berita>>()
    val studentLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val addHistLD = MutableLiveData<Boolean>()

//    val TAG = "volleyTag"
//    private var queue: RequestQueue? = null

    fun refresh(idPembaca:String) {
        loadingLD.value = true
        studentLoadErrorLD.value = false
//
//        queue = Volley.newRequestQueue(getApplication())
//        val url = "http://192.168.195.188/anmp/uts/history.php?idPembaca="+idPembaca
//
//        val stringRequest = StringRequest(
//            Request.Method.GET, url,
//            {
//                Log.d("apihistory", it.toString())
//                val obj = JSONObject(it)
//                if (obj.getString("result") == "OK") {
//                    val data = obj.getJSONArray("data")
//                    val sType = object: TypeToken<List<Berita>>(){}.type
////                    val result = Gson().fromJson(data.toString(), sType)
//                    beritaLD.value = Gson().fromJson(data.toString(), sType) as ArrayList<Berita>?
//                    loadingLD.value = false
//                    Log.d("showhistory", beritaLD.value.toString())
//                }
//            },
//            {
//                Log.d("showhistory", it.toString())
//                studentLoadErrorLD.value = false
//                loadingLD.value = false
//            })
//        stringRequest.tag = TAG
//        queue?.add(stringRequest)
    }
    fun addHistory(username:String, idBerita:Int) {
        addHistLD.value = false
//        queue = Volley.newRequestQueue(getApplication())
//        val url =
//            "http://192.168.195.188/anmp/uts/addHistory.php?idBerita=" + idBerita.toString() + "&idPembaca=" + username
//
//        val stringRequest = StringRequest(
//            Request.Method.POST, url,
//            {
//                Log.d("apiaddhistory", it.toString())
//                val obj = JSONObject(it)
//                if (obj.getString("result") == "success") {
//                    Log.d("showaddhistory", it.toString())
//                    addHistLD.value = true
//                }
//            },
//            {
//                Log.d("showaddhistory", it.toString())
//                addHistLD.value = false
//            })
//        stringRequest.tag = TAG
//        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
//        queue?.cancelAll(TAG)
    }
}