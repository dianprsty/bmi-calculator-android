package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.tugasharianandroid.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(HomeFragment())


        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.menu_home-> {
                    loadFragment(HomeFragment())
                }
                R.id.menu_bmi -> {
                    loadFragment(BMIFragment())
                }
                R.id.menu_about_me -> {
                    loadFragment(AboutMeFragment())
                }
            }
            true
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack(null).commit()
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }


}


