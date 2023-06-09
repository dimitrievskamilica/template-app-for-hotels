package com.example.vaja1

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.lib.Hotel
import com.squareup.picasso.Picasso
import timber.log.Timber

class HotelAdapter(private val data: Hotel, private val onClickObject:HotelAdapter.MyOnClick) :
    RecyclerView.Adapter<HotelAdapter.ViewHolder>() {
    interface MyOnClick {
        fun onLongClick(p0: View?, position:Int)
        fun onClick(p0: View?, position:Int)
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView6)
        val guestName: TextView = itemView.findViewById(R.id.textView10)
        val guestSurname: TextView = itemView.findViewById(R.id.textView12)
        val guestAge: TextView = itemView.findViewById(R.id.textView11)
        val roomType: TextView = itemView.findViewById(R.id.textView13)
        val line: CardView = itemView.findViewById(R.id.cvLine)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.guests.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = data.guests[position]
        Picasso.get().load("https://icons.iconarchive.com/icons/designbolts/free-male-avatars/128/Male-Avatar-Mustache-icon.png").placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(holder.imageView);
        // sets the text to the textview from our itemHolder class
        Timber.d("MM onBindViewHolder ${data.guests.size}")
        holder.guestName.text = ItemsViewModel.name
        holder.guestAge.text = ItemsViewModel.age.toString()
        holder.guestSurname.text = ItemsViewModel.surname
        holder.roomType.text = ItemsViewModel.roomInfo.roomType

        holder.line.setOnLongClickListener(object:View.OnLongClickListener {
            override fun onLongClick(v: View?): Boolean {
                println("Here code comes Click on ${holder.adapterPosition}")

                onClickObject.onLongClick(v, holder.adapterPosition)//delegacija klica na lasten objekt-sledi razlaga

                return true
            }
        });

        holder.line.setOnClickListener(object:View.OnClickListener{
            override fun onClick(p0: View?) {
                Timber.d("Here code comes Click on ${holder.adapterPosition}")
                onClickObject.onClick(p0,holder.adapterPosition) //Action from Activity
            }
        })

    }

}
