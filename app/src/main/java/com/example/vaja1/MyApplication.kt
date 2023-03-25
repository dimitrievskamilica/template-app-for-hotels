package com.example.vaja1
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.lib.Guest
import com.example.lib.Hotel
import java.io.File
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*
import java.io.IOException
import org.apache.commons.io.FileUtils
import timber.log.Timber

const val MY_FILE_NAME = "mydata.json"
const val MY_SP_FILE_NAME = "myshared.data"

class MyApplication: Application() {
    lateinit var data: Hotel

    private lateinit var gson: Gson
    private lateinit var file: File
    lateinit var sharedPref: SharedPreferences
    override fun onCreate() {
        super.onCreate()
        sharedPref = getSharedPreferences(MY_SP_FILE_NAME, Context.MODE_PRIVATE)
        data=Hotel(100)
        gson = Gson()
        file = File(filesDir, MY_FILE_NAME)
        if (!containsID()) {
            saveID(UUID.randomUUID().toString().replace("-", ""))
        }
        loadData()
    }
    fun saveID(id: String) {
        with(sharedPref.edit()) {
            putString("ID", id)
            apply()
        }
    }

    fun containsID(): Boolean {
        return sharedPref.contains("ID")
    }
    fun getID(): String? {
        return sharedPref.getString("ID", "DefaultNoData")
    }
    fun saveData() {
        try {
            //for FileUtils import org.apache.commons.io.FileUtils
            //in gradle implementation 'org.apache.commons:commons-io:1.3.2'
            org.apache.commons.io.FileUtils.writeStringToFile(file, gson.toJson(data.guests))
            Timber.d("Save to file.")
        } catch (e: IOException) {
            Timber.d("Can't save " + file.path)
        }
    }
    fun loadData() {
        val listPersonType = object : TypeToken<MutableList<Guest>>() {}.type
        var hotel=Hotel(2)
        data.guests = try { //www
            Timber.d("My file data:${org.apache.commons.io.FileUtils.readFileToString(file)}")
            gson.fromJson(
                org.apache.commons.io.FileUtils.readFileToString(file),
                listPersonType
            )
        } catch (e: IOException) {
            Timber.d("No file init data.")
            hotel.guests
        }
    }

}
