import java.nio.file.Files
import java.nio.file.Paths

val path = Paths.get("input.txt")
val charOrder = listOf('X', 'M', 'A', 'S')
var wordSearch = mutableListOf<List<String>>()

val lines = Files.readAllLines(path)
for (line in lines) {
    wordSearch.add(line.split(""))
}

var amountXmas = 0
val directions = listOf(
    Pair(0, 1),    // Right
    Pair(0, -1),   // Left
    Pair(1, 0),    // Down
    Pair(-1, 0),   // Up
    Pair(1, 1),    // Down-Right
    Pair(1, -1),   // Down-Left
    Pair(-1, 1),   // Up-Right
    Pair(-1, -1)   // Up-Left
)

for (i in wordSearch.indices) {
    outer@ for (j in wordSearch[i].indices) {
        if (wordSearch[i][j] == charOrder.first().toString()) {
            for (direction in directions) {
                try {
                    var firstDirection = i + direction.first
                    var secondDirection = j + direction.second
                    var charToCheck = wordSearch[firstDirection][secondDirection]
                    if (charToCheck == charOrder[1].toString()) {
                        firstDirection += direction.first
                        secondDirection += direction.second
                        charToCheck = wordSearch[firstDirection][secondDirection]
                        if (charToCheck == charOrder[2].toString()) {
                            firstDirection += direction.first
                            secondDirection += direction.second
                            charToCheck = wordSearch[firstDirection][secondDirection]
                            if (charToCheck == charOrder[3].toString()) {
                                amountXmas++
                            }
                        }
                    }
                } catch (e: IndexOutOfBoundsException) {
                    continue
                }
            }
        }
    }
}

println(amountXmas)