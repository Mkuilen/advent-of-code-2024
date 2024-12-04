import java.nio.file.Files
import java.nio.file.Paths
import kotlin.reflect.typeOf

val path = Paths.get("input.txt")
val charOrder = listOf('M', 'M', 'S', 'S')
var wordSearch = mutableListOf<List<String>>()

val lines = Files.readAllLines(path)
for (line in lines) {
    wordSearch.add(line.split("").filter { it.isNotEmpty() })
}

var amountXmas = 0
val directions = listOf(
    Pair(1, 1),    // Down-Right
    Pair(1, -1),   // Down-Left
    Pair(-1, 1),   // Up-Right
    Pair(-1, -1)   // Up-Left
)

for (i in wordSearch.indices) {
    outer@ for (j in wordSearch[i].indices) {
        if (wordSearch[i][j] == "A") {
            try {
                //Get a 6 x 6 grid surrounding the char
                val grid = wordSearch.subList(i - 1, i + 2).map { it.subList(j - 1, j + 2) }
                // Check if the grid contains the charOrder
                if (charOrder.all { char -> grid.flatten().count { it == char.toString() } >= charOrder.count { it == char } }) {
                    if (grid[0][0].single() in charOrder && grid[2][2].single() in charOrder && grid[0][2].single() in charOrder && grid[2][0].single() in charOrder && grid[0][0] != grid[2][2] && grid[0][2] != grid[2][0]) {
                        for (line in grid){
                            println(line)
                        }
                        println("\n")
                        amountXmas++
                    }
                }
            } catch (e: IndexOutOfBoundsException) {
                continue
            }
        }
    }
}

println(amountXmas)