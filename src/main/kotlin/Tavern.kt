import java.io.File

private const val TAVERN_MASTER = "Taernyl"
private const val TAVERN_NAME = "$TAVERN_MASTER's folley"

private val firstName = setOf("Alex", "Mordoc", "Sophie", "Tariq")
private val lastName = setOf("Ironfoot", "Fernsworth", "Baggins", "Downstrider")

private val menuData = File("data/tavern-menu-data.txt")
    .readText()
    .split("\n")

private val menuItems = List(menuData.size) { index ->
    val (_, name, _) = menuData[index].split(",")
    name
}

private val menuPrices = List(menuData.size) { index ->
    val(_,_, price) = menuData[index].split(",")
    price
}

fun visitTavern() {
    narrate("$heroName enters $TAVERN_NAME")
    //narrate("There are several items for sale:")
    narrate("Welcome to Taernyl's Folly")

    //get longest menu item.
    val longestItem = menuItems.maxBy { it.length }.length

    //longest price
    val longestPrice = menuPrices.maxBy { it.length}.length
    println("longest price $longestPrice")

    // row length
    val rowLength = longestItem + longestPrice + 5


    //loop print menu item dots calculated price
    menuItems.forEachIndexed {index, item ->
        val dots = rowLength - (item.length + menuPrices[index].length)
        print(item)
        repeat(dots) {
            print(".")
        }
        print(menuPrices[index])
        println()
    }

    val patrons: MutableSet<String> = mutableSetOf()
    while(patrons.size < 10) {
        patrons += "${firstName.random()} ${lastName.random()}"
    }
    val readOnlyPatrons = patrons.toList()

    narrate("$heroName sees several patrons in the tavern:")
    narrate(patrons.joinToString())

    repeat(3) {
        placeOrder(patrons.random(), menuItems.random())
    }


}

private fun placeOrder(patronName: String, menuItemName: String) {
    narrate("$patronName speaks with $TAVERN_MASTER to place an order")
    narrate("$TAVERN_MASTER hands $patronName a $menuItemName")
}