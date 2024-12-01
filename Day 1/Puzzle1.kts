import java.nio.file.Files
import java.nio.file.Paths
import java.io.IOException
import kotlin.math.abs

val path = Paths.get("input.txt")
var leftList = intArrayOf()
var rightList = intArrayOf()
val regex = "\\d+".toRegex()

try {
    val lines = Files.readAllLines(path)
    for (line in lines) {
        val numbers = regex.findAll(line).map { it.value }.toList()
        leftList += numbers[0].toInt()
        rightList += numbers[1].toInt()
    }
} catch (e: IOException) {
    e.printStackTrace()
}

leftList.sort()
rightList.sort()

var totalDistance = 0

for (i in leftList.indices) {
    val distance = abs(leftList[i] - rightList[i])
    totalDistance += distance
}

print(totalDistance)