package com.example.lib

import java.lang.Exception

class AgeException(val age: Int) : Exception() {
    override fun toString(): String {
           if(age<16)
            return "You can't reserve room if you are not above 16"
           else
               return "Invalid age"
    }
}