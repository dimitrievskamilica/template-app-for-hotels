package com.example.lib

import java.util.*


class RoomInfo ( var roomNumber:Int, var roomType: String, var cost: Double){

    var id:String = UUID.randomUUID().toString().replace("-", "")
    override fun toString(): String {
        return "Room info: \n\troom number: "+roomNumber+"\n\troom type: "+roomType+"\n\tcost: "+cost

    }
}