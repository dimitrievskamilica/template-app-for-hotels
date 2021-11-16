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
import java.util.*

class MainActivity : AppCompatActivity() {
   // var hotel = Hotel(10)
    lateinit var app: MyApplication
    val resultContract = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            app.data.addGuest( Guest(UUID.randomUUID().toString().replace("-", ""),
                data?.getStringExtra("name")!!,
                data.getStringExtra("surname")!!,
                data.getIntExtra("age",30),
                RoomInfo(UUID.randomUUID().toString().replace("-", ""),data.getIntExtra("roomNumber",100),
                    data.getStringExtra("roomType")!! ,
                    data.getDoubleExtra("cost",50.00)
                )
            )
            )
            app.saveData()
            Log.i("MainActivityAdd",app.data.guests.last().toString())

        }

    }
    val editResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val guestNumber= data?.getIntExtra("guestNumber",0)?.minus(1)
            app.data.guests[guestNumber!!].age=data.getIntExtra("age",30)
            app.data.guests[guestNumber].name=data.getStringExtra("name")!!
            app.data.guests[guestNumber].surname=data.getStringExtra("surname")!!
            app.data.guests[guestNumber].roomInfo.roomNumber= data.getIntExtra("roomNumber",100)
            app.data.guests[guestNumber].roomInfo.roomType=data.getStringExtra("roomType")!!
            app.data.guests[guestNumber].roomInfo.cost=data.getDoubleExtra("cost",50.00)
            app.saveData()
            Log.i("MainActivityAdd",app.data.guests.last().toString())

        }

    }
    val deleteResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val guestNumber= data?.getIntExtra("guestNumber",0)?.minus(1)
            app.data.guests.removeAt(guestNumber!!)
            app.saveData()
            Log.i("MainActivityAdd",app.data.guests.last().toString())

        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = application as MyApplication
        setContentView(R.layout.activity_main)
    }

    fun vnos(view: android.view.View) {
        val intent = Intent(this,AddGuestActivity::class.java)
        resultContract.launch(intent)
    }

    fun edit(view: android.view.View) {
        val intent = Intent(this,EditGuestActivity::class.java)
        editResult.launch(intent)
    }
    fun delete(view: android.view.View) {
        val intent = Intent(this,DeleteGuestActivity::class.java)
        deleteResult.launch(intent)
    }

    fun settings(view: android.view.View) {
        val intent = Intent(this,SettingsActivity::class.java)
        startActivity(intent)
    }

}