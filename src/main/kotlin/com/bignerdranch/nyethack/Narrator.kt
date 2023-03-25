package com.bignerdranch.nyethack

import kotlin.random.Random
import kotlin.random.nextInt

var narrationModifier: (String) -> String = { it}

fun narrate(
    message: String,
    modifier: (String) -> String = { narrationModifier(it) }
) {
    println(modifier(message))
}

fun changeNarratorMood() {
    val mood: String
    val modifier: (String) -> String

    //when (Random.nextInt(1..4)) {
    when (6) {
        1 -> {
            mood = "loud"
            modifier = { message ->
                val numExclamationPoints = 3
                message.uppercase() + "!".repeat(numExclamationPoints)
            }
        }

        2 -> {
            mood = "tired"
            modifier = { message ->
                message.lowercase().replace(" ", "... ")
            }
        }

        3 -> {
            mood = "unsure"
            modifier = { message ->
                "$message?"
            }
        }

        4 -> {
            mood = "lazy"
            modifier = { message ->
                "${message.substring(0, message.length / 2)}"
            }
        }

        5 -> {
            mood = "1337"
            val regex: Regex = """[aeilotsm]""".toRegex()
            modifier = { message ->
                message.toLowerCase().replace(regex) { m ->
                    when(m.value) {
                        "a" -> "4"
                        "e" -> "3"
                        "i" -> "1"
                        "l" -> "1"
                        "o" -> "0"
                        "t" -> "7"
                        "s" -> "5"
                        "m" -> "///"
                        else -> ""
                    }
                }
            }
        }

        6 -> {
            mood = "poetic"
            val randomBreaks: Int = Random.nextInt(0..2)
            modifier = { message ->
                when(randomBreaks) {
                    0 -> message
                    1 -> message + "\n"
                    2 -> message +"\n\n"
                    else -> message
                }

            }
        }

        else -> {
            mood = "professional"
            modifier = { message ->
                "$message"
            }
        }
    }
    narrationModifier = modifier
    narrate("The narrator begins to feel $mood")
}