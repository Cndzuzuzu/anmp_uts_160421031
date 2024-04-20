package com.zuzudev.hobbyapp.view

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.zuzudev.hobbyapp.R
import com.zuzudev.hobbyapp.databinding.ActivityMainBinding
import com.zuzudev.hobbyapp.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        first = true

        navController = (supportFragmentManager.findFragmentById(R.id.navHost2) as NavHostFragment).navController
//        NavigationUI.setupActionBarWithNavController(this, navController, binding.drawerLayout)
//        NavigationUI.setupWithNavController(binding.navView, navController)

//        binding.bottomNav.setupWithNavController(navController)

    }


//    override fun onSupportNavigateUp(): Boolean {
//        return NavigationUI.navigateUp(navController, binding.drawerLayout)
//                || super.onSupportNavigateUp()
//    }

    override fun onBackPressed() {
//        var loginInfo = "com.zuzudev.yarntopia"
//        var shared: SharedPreferences = getSharedPreferences(loginInfo,
//            Context.MODE_PRIVATE )
//        var userid = shared.getString("username","").toString()
//        if(userid == "")
//        {
//            Toast.makeText(this, "Press Back Button", Toast.LENGTH_SHORT).show()
//        }
//        else{
            super.onBackPressed()
//        }
    }
}