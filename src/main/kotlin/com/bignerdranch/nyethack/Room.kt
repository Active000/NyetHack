package com.bignerdranch.nyethack

class Room(val name: String){
    fun description() = name;
    fun enterRoom() {
        narrate("There is nothing to do here")
    }
}