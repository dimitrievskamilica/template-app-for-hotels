package com.example.lib

import io.github.serpro69.kfaker.Faker
import java.util.*

class Hotel(var numberOfGuests:Int) :Sizable{
    val faker = Faker()
    var guests:MutableList<Guest>
    val typeOfRoom= arrayOf("single","double","twin","king","studio","triple")
    init {
        guests=generateGuests()
    }
    fun generateGuests():MutableList<Guest>{
        val arrayOfguests = mutableListOf<Guest>()
        for(i in 1..numberOfGuests) {
            arrayOfguests.add(
                Guest(

                    faker.name.firstName(),
                    faker.name.lastName(),
                    faker.random.nextInt(16,90),
                    RoomInfo(faker.random.nextInt(1,1000),typeOfRoom[faker.random.nextInt(0,typeOfRoom.size-1)],faker.random.nextInt(30,150)+faker.random.nextDouble())
                )
            )
        }
        return arrayOfguests
    }
    fun addGuest(guest: Guest){
        guests.add(guest)
    }
    override fun toString(): String {
        var guestsToString = ""
        for(guest in guests) {
          guestsToString+=guest.toString()
        }
        return guestsToString
    }
    override fun size():Int{
        return guests.size
    }
    fun print(): String{
        var string =""
        var numberOfGuest=1
        for(guest in guests) {
            string+=numberOfGuest.toString()+". "+guest.name+" "+guest.surname+" "+guest.age+" "+guest.roomInfo.roomType+"\n"
            numberOfGuest++
        }
        return  string
    }

}