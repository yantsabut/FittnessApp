package com.example.fittnessapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import com.example.fittnessapp.fragments.DaysFragment
import com.example.fittnessapp.utils.FragmentManager
import com.example.fittnessapp.utils.MainViewModel

class MainActivity : AppCompatActivity() {
    private val model: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        model.pref = getSharedPreferences("main", MODE_PRIVATE)
        FragmentManager.setFragment(DaysFragment.newInstance(), this)
    }

    @Suppress("DEPRECATION")
    override fun onBackPressed() {
        if (FragmentManager.currentFragment is DaysFragment) super.onBackPressed()
        else FragmentManager.setFragment(DaysFragment.newInstance(), this)
    }
}