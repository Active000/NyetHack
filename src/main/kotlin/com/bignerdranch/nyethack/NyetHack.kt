package com.bignerdranch.nyethack

lateinit var player: Player

fun main() {
    narrate("Welcome to NyetHack!")
    val playerName = promptHeroName()
    player = Player(playerName)
    // changeNarratorMood()

    Game.play()
}

object Game {
    private val worldMap = listOf(
        listOf(TownSquare(), Tavern(), Room("Back Room")),
        listOf(Room("A Long Corridor"), Room("A Generic Room")),
        listOf(Room("The Dungeon"))
    )
    var currentRoom: Room = worldMap[0][0]
    var currentPosition = Coordinate(0,0)
    var running = true;
    init {
        narrate("welcome, adventurer")
        val mortality = if (player.isImmortal) "an mmortal" else "a mortal"
        narrate("${player.name}, $mortality, has ${player.healthPoints} health points")
    }

    fun play() {
        while (running) {
            narrate("${player.name} of ${player.hometown}, ${player.title}, is in ${currentRoom.description()}")
            currentRoom.enterRoom()
            print("> Enter your command: ")
            GameInput(readLine()).processCommand()
        }
    }

        fun move(direction: Direction) {
            val newPosition = direction.updateCoordinate(currentPosition)
            val newRoom = worldMap.getOrNull(newPosition.y)?.getOrNull(newPosition.x)

            if(newRoom != null) {
                narrate("The hero moves ${direction.name}")
                currentPosition = newPosition
                currentRoom = newRoom
            } else {
                narrate("You cannot move ${direction.name}")
            }
        }
    private class GameInput(arg: String?) {
        private val input = arg ?: ""
        val command = input.split(" ")[0]
        val argument = input.split(" ").getOrElse(1) { "" }

        fun processCommand() = when (command.lowercase()) {
            "move" -> {
                val direction = Direction.values()
                    .firstOrNull { it.name.equals(argument, ignoreCase = true)}
                if(direction != null) {
                    move(direction)
                } else {
                    narrate("I don't know what direction that is")
                }
            }
            "cast" -> {
                player.cast(argument)

            }

            "prophesize" -> {
                player.prophesize()
            }
            "map" -> {
                worldMap.forEachIndexed { y, row ->
                    row.forEachIndexed { x, column ->
                        if(x == currentPosition.x && y == currentPosition.y) print("X")
                        else {
                            print("0")
                        }
                    }
                    println()
                }

            }
            "quit" -> {
                narrate("Quiting game...")
                running = false
            }
            else -> narrate("I'm not sure what you're trying to do")
        }
    }

}


private fun promptHeroName(): String {
    narrate("A hero enters the town of Kronstadt. What is their name?") { message ->
        // Prints the message in yellow
        "\u001b[33;1m$message\u001b[0m"
    }

    /*val input = readLine()
    require(input != null && input.isNotEmpty()) {
        "The hero must have a name."
    }

    return input*/
    println("Madrigal")
    return "Madrigal"
}
