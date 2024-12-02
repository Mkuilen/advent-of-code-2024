import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.math.abs

val path = Paths.get("input.txt")
val regex = "\\d+".toRegex()
var reports = mutableListOf<IntArray>()

try {
    val lines = Files.readAllLines(path)
    for (line in lines) {
        val numbers = regex.findAll(line).map { it.value.toInt() }.toList().toIntArray()
        reports.add(numbers)
    }
} catch (e: IOException) {
    e.printStackTrace()
}

var safeReports = 0
for (i in reports.indices) {
    var expectedIncrease = false
    for (j in reports[i].indices) {
        if (j == 0) {
            continue
        }

        val previousNumber = reports[i][j - 1]
        val number = reports[i][j]
        if (j == 1) {
            expectedIncrease = isIncreasing(previousNumber, number)
        }

        val increase = isIncreasing(previousNumber, number)
        // Check if the increase is the same as the expected increase
        if (increase != expectedIncrease) {
            break
        }

        // Check whether the difference between the previous number and the current number is 0
        if(abs(previousNumber - number) == 0 || abs(previousNumber - number) > 3) {
            break
        }

        if(j == reports[i].size - 1) {
            safeReports++
        }
    }

}

println(safeReports)

fun isIncreasing(a: Int, b: Int): Boolean {
    return a < b
}