package com.example.projekuts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.projekuts.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) show(ProductListFragment())

        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_products -> show(ProductListFragment())
                R.id.nav_profile -> show(ProfileFragment())
            }
            true
        }
    }

    private fun show(f: Fragment) =
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, f).commit()
}