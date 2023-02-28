import java.io.File

private const val TAVERN_MASTER = "Taernyl"
private const val TAVERN_NAME = "$TAVERN_MASTER's folley"

private val firstName = setOf("Alex", "Mordoc", "Sophie", "Tariq")
private val lastName = setOf("Ironfoot", "Fernsworth", "Baggins", "Downstrider")

private val menuData = File("data/tavern-menu-data.txt")
    .readText()
    .split("\n")

private val menuItemsNames = List(menuData.size) { index ->
    val (_, name, _) = menuData[index].split(",")
    name
}

private var menuItems = List(menuData.size) { index ->
    val (category, name, price) = menuData[index].split(",")
    listOf(category, name, price)
}


private val menuPrices = List(menuData.size) { index ->
    val(_,_, price) = menuData[index].split(",")
    price
}

fun visitTavern() {
    narrate("$heroName enters $TAVERN_NAME")
    //narrate("There are several items for sale:")
    narrate("*** Welcome to Taernyl's Folly ***")


    // Sort the list of menu items
    menuItems = menuItems.sortedBy { it[0] }
    //get longest menu item.
    val longestItem = menuItemsNames.maxBy { it.length }.length

    //longest price
    val longestPrice = menuPrices.maxBy { it.length}.length

    // row length
    val rowLength = longestItem + longestPrice + 5


    //loop print menu item dots calculated price
    menuItems.forEachIndexed { index, item ->
        val dots = rowLength - (item[1].length + item[2].length)
        print(item[1])
        repeat(dots) {
            print(".")
        }
        print(item[2]) // Price
        println()
        print("~[${item[0]}]~") // Category
        println()

    }
    /*
    menuItemsNames.forEachIndexed { index, item ->
        val dots = rowLength - (item.length + menuPrices[index].length)
        print(item)
        repeat(dots) {
            print(".")
        }
        print(menuPrices[index])
        println()
    }
     */

    val patrons: MutableSet<String> = mutableSetOf()
    while(patrons.size < 10) {
        patrons += "${firstName.random()} ${lastName.random()}"
    }
    val readOnlyPatrons = patrons.toList()

    narrate("$heroName sees several patrons in the tavern:")
    narrate(patrons.joinToString())

    repeat(3) {
        placeOrder(patrons.random(), menuItemsNames.random())
    }


}

private fun placeOrder(patronName: String, menuItemName: String) {
    narrate("$patronName speaks with $TAVERN_MASTER to place an order")
    narrate("$TAVERN_MASTER hands $patronName a $menuItemName")
}