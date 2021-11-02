package com.example.vaja1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vaja1.databinding.ActivityAddGuestBinding
import java.util.*
import kotlin.random.Random


class AddGuestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddGuestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_guest)
        binding = ActivityAddGuestBinding.inflate(layoutInflater)
        setContentView(binding.root);
    }

    fun addGuest(view: android.view.View) {

        val intent = Intent()
        intent.putExtra("name",binding.editTextName.text.toString())
        intent.putExtra("surname",binding.editTextSurname.text.toString())
        intent.putExtra("age",binding.editTextAge.text.toString().toInt())
        intent.putExtra("roomType",binding.editTextRoomType.text.toString())
        intent.putExtra("roomNumber",Random.nextInt(1,1000))
        intent.putExtra("cost",Random.nextDouble(30.00,150.00))
        intent.putExtra("id",Random.hashCode())
        setResult(RESULT_OK,intent)
        finish()
    }

    fun info(view: android.view.View) {
        val intent = Intent(this,AboutActivity::class.java)
        startActivity(intent)
    }
    fun exit(view: android.view.View) {
        finish();
        System.exit(0);
    }

}