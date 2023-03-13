import java.security.ProtectionDomain

class NyetHack {

}
var heroName: String = ""

val player = Player()
fun main() {
    narrate("${player.name} is ${player.title}")
    player.changeName("Aurelia")
    // changeNarratorMood()
    narrate("${player.name}, ${player.title}, heads to the town square")
    visitTavern()
    player.castFireball()
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
