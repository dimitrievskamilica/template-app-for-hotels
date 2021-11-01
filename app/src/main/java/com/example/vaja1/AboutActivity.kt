package com.example.vaja1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle



class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

    }
    fun back(view: android.view.View) {
        finish()
    }

}