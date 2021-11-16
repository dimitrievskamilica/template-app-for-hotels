package com.example.lib

import java.util.*

class Guest(var id:String , var name: String, var surname: String, var age:Int, var roomInfo: RoomInfo) :Comparable<Guest>{

    init {
        if(age<16 || age>90) throw AgeException(age)
    }


    override fun toString(): String {
        return "\nName: "+name+"\nSurname: "+surname+"\nAge: "+age+"\n"+roomInfo.toString()
    }

    override fun compareTo(other: Guest): Int {
        return roomInfo.roomNumber.compareTo(other.roomInfo.roomNumber)
    }

}