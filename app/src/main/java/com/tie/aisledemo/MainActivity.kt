package com.tie.aisledemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.tie.aisledemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        val navController = findNavController(this@MainActivity, R.id.frame_layout)
        setupWithNavController(binding!!.bottomNavigation, navController)
    }
}