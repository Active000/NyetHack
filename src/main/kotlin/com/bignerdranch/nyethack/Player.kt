package com.bignerdranch.nyethack

class Player(
    initialName: String,
    hometown: String = "Neversummer",
    var healthPoints: Int,
    val isImmortal: Boolean
) {
    var name = initialName
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
    val prophecy by lazy {
        narrate("$name embarks on an narduous quest to locate a fortune teller")
        Thread.sleep(3000)
        narrate("The fortune teller bestows a prophecy upon $name")

        "An intrepid hero from $hometown shall some day " + listOf(
            "form an unlikely bond between two warring factions",
            "take possession of an otherwordly blade",
            "bring the gift of creation back to the world",
            "best the world-eater"
        ).random()
    }

    init {
        require(healthPoints > 0) { "healthPoints must be greater than zero"}
        require(name.isNotBlank()) { "Player must have a name"}
        println("init ${healthPoints}")
    }

    constructor(name: String) : this (
        initialName = name,
        healthPoints = 100,
        isImmortal = false
    ) {
        println("secondary constructor")
        if (name.equals("Jason", ignoreCase = true)) {
            healthPoints = 500
        }
    }
    fun castFireball(numFireballs: Int = 2) {
        narrate("A glass of Fireball springs into existence (x$numFireballs)")
    }
    fun changeName(newName: String) {
        narrate("$name legally changes their name to $newName")
        name = newName
    }

    fun prophesize() {
        narrate("$name thinks about their future")
        narrate("A fortune teller told Madrigal, \"$prophecy\"")
    }
}