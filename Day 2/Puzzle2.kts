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
    var safeReport = isSafe(reports[i])

    if (!safeReport) {
        safeReport = canBeMadeSafe(reports[i])
    }

    if (safeReport) {
        safeReports++
    }
}

println(safeReports)

fun isSafe(list: IntArray): Boolean {
    var expectedIncrease = false
    for (j in list.indices) {
        if (j == 0) {
            continue
        }

        val previousNumber = list[j - 1]
        val number = list[j]

        if (j == 1) {
            expectedIncrease = isIncreasing(previousNumber, number)
        }

        val increase = isIncreasing(previousNumber, number)
        // Check if the increase is the same as the expected increase
        if (increase != expectedIncrease) {
            return false
        }

        // Check whether the difference between the previous number and the current number is within range
        if (abs(previousNumber - number) !in 1..3) {
            return false
        }
    }
    return true
}

fun canBeMadeSafe(report: IntArray): Boolean {
    for (i in report.indices) {
        val tempReport = report.toMutableList()
        tempReport.removeAt(i)
        val modifiedReport = tempReport.toIntArray()

        if (isSafe(modifiedReport)) {
            return true
        }
    }
    return false
}

fun isIncreasing(a: Int, b: Int): Boolean {
    return a < b
}
