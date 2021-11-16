package com.example.vaja1

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.example.vaja1.databinding.ActivityAddGuestBinding
import com.example.vaja1.databinding.ActivityDeleteGuestBinding
import com.example.vaja1.databinding.ActivityEditGuestBinding
import java.util.*
import kotlin.random.Random


class DeleteGuestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeleteGuestBinding
    lateinit var sharedPref: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_guest)
        binding = ActivityDeleteGuestBinding.inflate(layoutInflater)
        setContentView(binding.root);
        sharedPref = getSharedPreferences("sharedPrefSettings.data", MODE_PRIVATE)
        val dark = sharedPref.getBoolean("dark", false)
        if (dark) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    fun deleteGuest(view: android.view.View) {

        val intent = Intent()
        intent.putExtra("guestNumber",binding.editTextNumber.text.toString().toInt())
        setResult(RESULT_OK,intent)
        finish()
    }

    fun exit(view: android.view.View) {
        finish()
    }

}