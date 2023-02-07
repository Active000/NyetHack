import java.security.ProtectionDomain

class NyetHack {

}

fun main() {
    narrate("A hero enters the town of Kronstadt. What is their name?",
    ::makeYellow
    )
    /*
    { message ->
    // Prints the message in yellow
        "\u001b[33;1m$message\u001b[0m"
} */
    val heroName = readLine()
    require(heroName != null && heroName.isNotEmpty()) {
        "The hero must have a name"
    }

    changeNarratorMood()
    narrate("$heroName, ${createTitle(heroName)}, heads to the town square")
}

private fun makeYellow(message: String) = "\u001B[33;1m$message\u001B[0m"
private fun createTitle(name: String): String {
    return when {
        name.all { it.isDigit() } -> "The Identifiable"
        name.none { it.isLetter()} -> "The witness protection member"
        name.count() { it.lowercase() in "aeiou" } > 4 -> "The Master of vowels"
        name.all { it.isUpperCase() } -> "The Bold"
        name.count() > 10 -> "The Verbose"
        else -> "The Renowned Hero"
    }
}