package com.zuzudev.hobbyapp.view

import android.view.View

interface LoginClickListener {
    fun onLoginClick(v: View)
}

interface RegisterClickListener {
//    fun onRegisterClick(v: View, passConf:String)
    fun onRegisterClick(v: View)
}

interface ReadClickListener{
    fun onReadClick(v:View)
}