package com.example.vaja1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.lib.Guest
import com.example.lib.Hotel
import com.example.lib.RoomInfo
import com.example.vaja1.databinding.ActivityMainBinding
import java.util.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var hotel = Hotel(10)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root);


    }

    fun addGuest(view: android.view.View) {
        var name= binding.editTextName.text.toString()
        var surname= binding.editTextSurname.text.toString()
        var age= binding.editTextAge.text.toString().toInt()
        var roomType= binding.editTextRoomType.text.toString()
        var roomNumber= Random.nextInt(1,1000)
        var cost= Random.nextDouble(30.00,150.00)
        hotel.addGuest(Guest(Random.hashCode(),name,surname,age, RoomInfo(roomNumber,roomType,cost)))
        Log.i("MainActivityAdd", "${hotel.guests.last().toString()}")
    }

    fun info(view: android.view.View) {
        Log.i("MainActivityInfo", "Array guests size ${hotel.size()}")
    }
    fun exit(view: android.view.View) {
        finish();
        System.exit(0);
    }
}