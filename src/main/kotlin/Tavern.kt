import java.io.File
import kotlin.random.Random
import kotlin.random.nextInt

private const val TAVERN_MASTER = "Taernyl"
private const val TAVERN_NAME = "$TAVERN_MASTER's folley"

private val firstName = setOf("Alex", "Mordoc", "Sophie", "Tariq")
private val lastName = setOf("Ironfoot", "Fernsworth", "Baggins", "Downstrider")

private val menuData = File("data/tavern-menu-data.txt")
    .readText()
    .split("\n")
    .map { it.split(",")}

private var menuItems = menuData.map { (_, name, _) -> name }

private val menuItemPrices = menuData.associate { (_, name, price) ->
    name to price.toDouble()
}

private val menuItemTypes = menuData.associate { (type, name, _) ->
    name to type
}

fun visitTavern() {
    narrate("$heroName enters $TAVERN_NAME")
    //narrate("There are several items for sale:")
    narrate("*** Welcome to Taernyl's Folly ***")


    // Sort the list of menu items
    menuItems = menuItems.sortedBy { it[0] }
    //get longest menu item.
    val longestItem = menuItems.maxBy { it.length }.length

    //longest price
    val longestPrice = menuItemPrices.maxBy { (_, price) ->
        price.toString().length
    }.value

    // row length
    val rowLength = longestItem + longestPrice.toString().length + 5


    //loop print menu item dots calculated pric
    menuItemPrices.forEach { name, price ->
        val dots = rowLength - (name.length + price.toString().length)
        print(name)
        repeat(dots) {
            print(".")
        }
        print(price) // Price
        println()
        print("~[${menuItemTypes[name]}]~") // Category
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

    val itemOfDay = patrons.flatMap { getFavoriteMenuItems(it) }.random()
    println("The item of the day is the $itemOfDay")
    repeat(3) {
        placeOrder(patrons.random(), menuItems.random(), patronGold)
    }
    displayPatronBalances(patronGold)

    val departingPatrons: List<String> = patrons
        .filter { patron -> patronGold.getOrDefault(patron, 0.0) < 4.0 }
    patrons -= departingPatrons
    patronGold -= departingPatrons

    departingPatrons.forEach { patron ->
        narrate("$heroName sees $patron departing the tavern")
    }

    narrate((("there are still some patrons in the tavern")))
    narrate(patrons.joinToString())


}

private fun getFavoriteMenuItems(patron: String): List<String> {
    return when (patron) {
        "Alex Ironfoot" -> menuItems.filter { menuItem ->
            menuItemTypes[menuItem]?.contains("dessert") == true
        }
        else -> menuItems.shuffled().take(Random.nextInt(1..2))
    }
}

private fun placeOrder(
    patronName: String,
    menuItemName: String ,
    patronGold: MutableMap<String, Double>
) {
    val itemPrice = 1.0
    narrate("$patronName speaks with $TAVERN_MASTER to place an order")
    if (itemPrice <= patronGold.getOrDefault(patronName, 0.0)) {
        narrate("$TAVERN_MASTER hands $patronName a $menuItemName")
        narrate("$patronName pays $TAVERN_MASTER $itemPrice gold")
        patronGold[patronName] = patronGold.getValue(patronName) - itemPrice
        patronGold[TAVERN_MASTER] = patronGold.getValue(TAVERN_MASTER) + itemPrice
    } else {
        narrate("$TAVERN_MASTER says, \"You need more coin for a $menuItemName\"")
    }
}

private fun displayPatronBalances(patronGold: Map<String, Double>) {
    narrate("$heroName intuitively knows how much money each patron has")
    patronGold.forEach { (patron, balance) ->
        narrate("$patron has ${"%.2f".format(balance)} gold")
    }
}