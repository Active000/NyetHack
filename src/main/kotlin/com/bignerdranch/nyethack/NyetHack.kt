package com.bignerdranch.nyethack

import visitTavern

class NyetHack {

}
var heroName: String = ""

val player = Player("Jason")
fun main() {
    // changeNarratorMood()
    player.prophesize()
    var currentRoom = Room("They Foyer")
    val mortality = if (player.isImmortal) "an immortal" else "a mortal"
    narrate("${player.name}, ${player.title}, heads to the town square")
    narrate("${player.name}, $mortality, has ${player.healthPoints} health points")
    currentRoom.enterRoom()
    player.castFireball()
    player.prophesize()
}

private fun promptHeroName(): String {
    narrate("A hero enters the town of Kronstadt. What is their name?") { message ->
        // Prints the message in yellow
        "\u001B[33;1m$message\u001B[0m"
    }

    /*val input = readLine()
    require(input != null && input.isNotEmpty()) {
        "The hero must have a name."
    }

    return input*/
    println("Madrigal")
    return "Madrigal"
}

private fun makeYellow(message: String) = "\u001B[33;1m$message\u001B[0m"
