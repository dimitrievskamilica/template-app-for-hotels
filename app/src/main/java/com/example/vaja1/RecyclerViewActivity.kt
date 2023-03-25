package com.example.vaja1

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vaja1.databinding.ActivityRecyclerViewBinding
import timber.log.Timber

class RecyclerViewActivity : AppCompatActivity() {
    lateinit var app: MyApplication
    private lateinit var binding: ActivityRecyclerViewBinding
    private lateinit var adapter: HotelAdapter
    var getContent =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                if (data != null) {
                    val name = data.getStringExtra("name")
                    val surname = data.getStringExtra("surname")
                    val age = data.getIntExtra("age",0)
                    val roomType = data.getStringExtra("roomType")
                    val pos = data.getIntExtra("position", -1)

                    if (name != null && surname != null && age != null && roomType !=null) {
                        if (pos != -1 || pos != null) {
                            app.data.guests[pos].name = name
                            app.data.guests[pos].surname = surname
                            app.data.guests[pos].age = age.toInt()
                            app.data.guests[pos].roomInfo.roomType = roomType

                        }
                    }
                    app.saveData()
                    adapter.notifyItemChanged(pos)
                }
            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        app = application as MyApplication
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = HotelAdapter(app.data, object:HotelAdapter.MyOnClick{
            override fun onLongClick(p0: View?, pos: Int) {
                Timber.d("Here code comes ${pos}.")
                val data = intent
                data.putExtra("SELECTED_ID", app.data.guests[pos].id)
                val builder =
                    AlertDialog.Builder(this@RecyclerViewActivity) //access context from inner class
                //set title for alert dialog
                builder.setTitle("Delete")
                builder.setMessage("Animal ${app.data.guests[pos].toString()}")
                builder.setIcon(android.R.drawable.ic_dialog_alert)
                builder.setPositiveButton("Yes") { dialogInterface, which -> //performing positive action
                    Toast.makeText(applicationContext, "clicked yes", Toast.LENGTH_LONG).show()

                    app.data.guests.remove(app.data.guests[pos])
                    adapter.notifyItemRemoved(pos)
                    setResult(RESULT_OK, data)
                    app.saveData()
                }
                builder.setNeutralButton("Cancel") { dialogInterface, which -> //performing cancel action
                    Toast.makeText(
                        applicationContext,
                        "clicked cancel\n operation cancel",
                        Toast.LENGTH_LONG
                    ).show()
                }
                builder.setNegativeButton("No") { dialogInterface, which -> //performing negative action
                    Toast.makeText(applicationContext, "clicked No", Toast.LENGTH_LONG).show()
                }
                // Create the AlertDialog
                val alertDialog: AlertDialog = builder.create()
                alertDialog.setCancelable(false)
                alertDialog.show()

            }

            override fun onClick(p0: View?, pos:Int) {
                Timber.d("Here code comes ${pos}.")
                var addIntent= Intent(getApplicationContext(), AddGuestActivity::class.java)
                addIntent.putExtra("position", pos.toString().toInt())

                setResult(RESULT_OK, addIntent)
                getContent.launch(addIntent)

            }

        })
        binding.recyclerView.adapter = adapter
        //adapter.notifyDataSetChanged();
        Timber.d("Items ${app.data.guests.size}")

    }
}