package com.example.vaja1

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.example.vaja1.databinding.ActivityAddGuestBinding
import com.example.vaja1.databinding.ActivityEditGuestBinding
import java.util.*
import kotlin.random.Random


class EditGuestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditGuestBinding
    lateinit var sharedPref: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_guest)
        binding = ActivityEditGuestBinding.inflate(layoutInflater)
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

    fun editGuest(view: android.view.View) {

        val intent = Intent()
        intent.putExtra("name",binding.editTextName.text.toString())
        intent.putExtra("surname",binding.editTextSurname.text.toString())
        intent.putExtra("age",binding.editTextAge.text.toString().toInt())
        intent.putExtra("roomType",binding.editTextRoomType.text.toString())
        intent.putExtra("roomNumber",Random.nextInt(1,1000))
        intent.putExtra("cost",Random.nextDouble(30.00,150.00))
        intent.putExtra("id",Random.hashCode())
        intent.putExtra("guestNumber",binding.editTextNumber.text.toString().toInt())
        setResult(RESULT_OK,intent)
        finish()
    }

    fun exit(view: android.view.View) {
        finish();
    }

}