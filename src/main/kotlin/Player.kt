class Player {
    var name = "madrigal"
        get() = field.replaceFirstChar { it.uppercase() }
        private set(value) {
            field = value.trim()
        }

    val title: String
        get() = when {
            name.all { it.isDigit() } -> "The Identifiable"
            name.none { it.isLetter()} -> "The witness protection member"
            name.count() { it.lowercase() in "aeiou" } > 4 -> "The Master of vowels"
            name.all { it.isUpperCase() } -> "The Bold"
            name.count() > 10 -> "The Verbose"
            else -> "The Renowned Hero"
        }
    fun castFireball(numFireballs: Int = 2) {
        narrate("A glass of Fireball springs into existence (x$numFireballs)")
    }
    fun changeName(newName: String) {
        narrate("$name legally changes their name to $newName")
        name = newName
    }
}