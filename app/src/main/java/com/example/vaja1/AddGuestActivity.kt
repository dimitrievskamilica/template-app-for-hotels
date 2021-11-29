package com.example.vaja1

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import com.example.vaja1.databinding.ActivityAddGuestBinding
import java.util.*
import kotlin.random.Random


class AddGuestActivity : AppCompatActivity() {

    lateinit var app: MyApplication
    private lateinit var binding: ActivityAddGuestBinding
    lateinit var sharedPref: SharedPreferences
    private var pos : Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_guest)
        app = application as MyApplication
        binding = ActivityAddGuestBinding.inflate(layoutInflater)
        setContentView(binding.root);
        sharedPref = getSharedPreferences("sharedPrefSettings.data", MODE_PRIVATE)
        val dark = sharedPref.getBoolean("dark", false)
        if (dark) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        if(intent.hasExtra("UPDATE_ID")) {
            pos =intent.getIntExtra("position",-1)
            if(pos!=-1) {
                binding.buttonAdd.text = "Update"
                binding.editTextName.setText(intent.getStringExtra("name"))
                binding.editTextSurname.setText(app.data.guests[pos].surname)
                binding.editTextAge.setText(intent.getStringExtra("age"))
                binding.editTextRoomType.setText(app.data.guests[pos].roomInfo.roomType)
                binding.buttonAdd.setTextKeepState("Update")
            }
            //binding.editTextPersonId.visibility= View.INVISIBLE

        }
    }

    fun addGuest(view: android.view.View) {

        val intent = Intent()
        intent.putExtra("name",binding.editTextName.text.toString())
        intent.putExtra("surname",binding.editTextSurname.text.toString())
        intent.putExtra("age",binding.editTextAge.text.toString().toInt())
        intent.putExtra("roomType",binding.editTextRoomType.text.toString())
        intent.putExtra("roomNumber",Random.nextInt(1,1000))
        intent.putExtra("cost",Random.nextDouble(30.00,150.00))
        //intent.putExtra("id",Random.hashCode())
            if(pos!=-1) {
                intent.putExtra("position", pos.toString().toInt())
            }
        setResult(RESULT_OK,intent)
        finish()
    }

    fun info(view: android.view.View) {
        val intent = Intent(this,AboutActivity::class.java)
        startActivity(intent)
    }
    fun exit(view: android.view.View) {
        finish();
    }

}