import java.nio.file.Files
import java.nio.file.Paths
import java.io.IOException
import kotlin.math.abs

val path = Paths.get("input.txt")
var listOne = intArrayOf()
var listTwo = intArrayOf()
val regex = "\\d+".toRegex()

try {
    val lines = Files.readAllLines(path)
    for (line in lines) {
        val numbers = regex.findAll(line).map { it.value }.toList()
        listOne += numbers[0].toInt()
        listTwo += numbers[1].toInt()
    }
} catch (e: IOException) {
    e.printStackTrace()
}

listOne.sort()
listTwo.sort()

var totalDistance = 0

for (i in listOne.indices) {
    val distance = abs(listOne[i] - listTwo[i])
    totalDistance += distance
}

print(totalDistance)