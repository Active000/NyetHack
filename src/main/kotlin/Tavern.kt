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

private val menuItemPrices: Map<String, Double> = List(menuData.size) { index ->
    val (_, name, price) = menuData[index].split(",")
    name to price.toDouble()
}.toMap()

private val menuItemTypes: Map<String, String> = List(menuData.size) { index ->
    val ( type,name, _) = menuData[index].split(",")
    name to type
}.toMap()

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
    val patronGold = mutableMapOf(
        TAVERN_MASTER to 86.00,
        heroName to 4.50
    )
    while(patrons.size < 5) {
        val patronName = "${firstName.random()} ${lastName.random()}"
        patrons += patronName
        patronGold += patronName to 6.0
    }
    val readOnlyPatrons = patrons.toList()

    narrate("$heroName sees several patrons in the tavern:")
    narrate(patrons.joinToString())

    repeat(3) {
        placeOrder(patrons.random(), menuItemsNames.random(), patronGold)
    }

    displayPatronBalances(patronGold)

}

private fun displayPatronBalances(patronGold: Map<String, Double>) {
    narrate("$heroName intuitively knows how much money each patron has")
    patronGold.forEach { (patron, balance) ->
        narrate("$patron has ${"%.2f".format(balance)} gold")
    }
}


private fun placeOrder(
    patronName: String,
    menuItemName: String ,
    patronGold: MutableMap<String, Double>
) {
    val itemPrice = menuItemPrices.getValue(menuItemName)
    narrate("$patronName speaks with $TAVERN_MASTER to place an order")
    if (itemPrice <= patronGold.getOrDefault(patronName, 0.0)) {
        val action = when(menuItemTypes[menuItemName]) {
            "shandy", "elixer" -> "pours"
            "meal" -> "serves"
            else -> "hands"
        }
        narrate("$TAVERN_MASTER $action $patronName a $menuItemName")
        narrate("$patronName pays $TAVERN_MASTER $itemPrice gold")
        patronGold[patronName] = patronGold.getValue(patronName) - itemPrice
        patronGold[TAVERN_MASTER] = patronGold.getValue(TAVERN_MASTER) + itemPrice
    } else {
        narrate("$TAVERN_MASTER says, \"You need more coin for a $menuItemName\"")
    }
}