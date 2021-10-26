package com.example.lib



class RoomInfo (var roomNumber:Int,var roomType: String,var cost: Double){

    override fun toString(): String {
        return "Room info: \n\troom number: "+roomNumber+"\n\troom type: "+roomType+"\n\tcost: "+cost

    }
}