package com.example.fittnessapp.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.fittnessapp.R

object FragmentManager {
   fun setFragment(newFragment: Fragment, activity: AppCompatActivity) {
       val transaction = activity.supportFragmentManager.beginTransaction()
       transaction.replace(R.id.placeHolder, newFragment)
       transaction.commit()

   }
}