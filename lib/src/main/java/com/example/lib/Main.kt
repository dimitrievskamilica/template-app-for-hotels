package com.example.lib

import java.io.IOException
import java.lang.Exception
import java.util.*

fun readFile(): String? {
    val filePath = null
    return filePath ?: throw IOException("File path is invalid")
}
fun main(){
    var hotel=Hotel(10)
    try{
        var guest = Guest(UUID.randomUUID().toString().replace("-", ""),"Ema","Horvat",15, RoomInfo(UUID.randomUUID().toString().replace("-", ""),3,"single",50.50))
    }catch(ex: Exception){
        println(ex.toString())
    }
    try{
        var guest = Guest(UUID.randomUUID().toString().replace("-", ""),"Ema","Horvat",91,RoomInfo(UUID.randomUUID().toString().replace("-", ""),3,"single",50.50))
    }catch(ex: Exception){
        println(ex.toString())
    }
    println("Normal: ${hotel.toString()}")
    println("Sortetd by room number: ${hotel.guests.sorted().toString()}")

   //toInt() throws NumberFormatException if the string is not a valid representation of a number.
    try {
        val message = "Kotlin exception"
        message.toInt()
    } catch (exception: NumberFormatException) {
        println(exception)
    }
    //it will throw an ArithmeticException because of division by zero
    try {
        val result = 25 / 0
    }
    catch (exception: ArithmeticException) {
        println(exception)
    }

    try {
        readFile()
    }catch(e: IOException){
      println(e)
    }


}