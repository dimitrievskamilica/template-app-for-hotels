package com.example.lib

import java.util.*


class RoomInfo (var id:String = UUID.randomUUID().toString().replace("-", ""), var roomNumber:Int, var roomType: String, var cost: Double){

    override fun toString(): String {
        return "Room info: \n\troom number: "+roomNumber+"\n\troom type: "+roomType+"\n\tcost: "+cost

    }
}