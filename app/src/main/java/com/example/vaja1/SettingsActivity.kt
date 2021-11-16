package com.example.vaja1

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.example.vaja1.databinding.ActivitySettingstBinding
import java.util.*
import kotlin.random.Random


class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingstBinding
    lateinit var sharedPref: SharedPreferences
    lateinit var sharedPrefShow: SharedPreferences
    lateinit var app: MyApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingstBinding.inflate(layoutInflater)
        setContentView(binding.root);
        sharedPref = getSharedPreferences("sharedPrefSettings.data", MODE_PRIVATE)
        sharedPrefShow = getSharedPreferences("sharedPrefSettingsShow.data", MODE_PRIVATE)
        app = application as MyApplication


        val dark = sharedPref.getBoolean("dark", false)
        val show = sharedPrefShow.getBoolean("show", false)
        if (dark) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            binding.switch1.isChecked = true
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            binding.switch1.isChecked = false
        }
        if (show) {
            binding.textView9.text=app.data.print()
            binding.switch2.isChecked = true
        }
        else {
            binding.textView9.text=""
            binding.switch2.isChecked = false
        }

        binding.switch1.setOnCheckedChangeListener { _, isChecked ->
            with(sharedPref.edit()) {
                if (isChecked) {
                    androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode(androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES)
                    putBoolean("dark", true)
                    apply()
                } else {
                    androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode(androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO)
                    putBoolean("dark", false)
                    apply()
                }
            }
        }
        binding.switch2.setOnCheckedChangeListener { _, isChecked ->
            with(sharedPrefShow.edit()) {
                if (isChecked) {
                    putBoolean("show", true)
                    apply()
                    binding.textView9.text=app.data.print()
                } else {
                    putBoolean("show", false)
                    apply()
                    binding.textView9.text=""
                }
            }
        }
    }

    fun exit(view: android.view.View) {
        finish();
    }

}