package com.example.vaja1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.example.lib.Guest
import com.example.lib.Hotel
import com.example.lib.RoomInfo

class MainActivity2 : AppCompatActivity() {
    var hotel = Hotel(10)
    val resultContract = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            hotel.addGuest( Guest(data?.getIntExtra("id",0)!!,
                data.getStringExtra("name")!!,
                data.getStringExtra("surname")!!,
                data.getIntExtra("age",30),
                RoomInfo(data.getIntExtra("roomNumber",100),
                    data.getStringExtra("roomType")!! ,
                    data.getDoubleExtra("cost",50.00)
                )
            )
            )
            Log.i("MainActivityAdd", hotel.guests.last().toString())

        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }

    fun vnos(view: android.view.View) {
        val intent = Intent(this,MainActivity::class.java)
        resultContract.launch(intent)
    }
}