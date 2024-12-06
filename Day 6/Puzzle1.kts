import java.nio.file.Files
import java.nio.file.Paths

val path = Paths.get("input.txt")
var map = arrayOf<CharArray>()
val lines = Files.readAllLines(path)

for (line in lines) {
    map += line.toCharArray()
}

val guard = '^'
var guardPosition = intArrayOf()

for (line in map) {
    if (guard in line) {
        val index = line.indexOf(guard)
        val row = map.indexOf(line)
        guardPosition += intArrayOf(row, index)
    }
}

moveGuard(guardPosition, map)

var stepsTaken = 0
for (row in map) {
    for (cell in row) {
        if (cell == 'X') {
            stepsTaken++
        }
    }
}
println(stepsTaken)

fun moveGuard(guardPos: IntArray, mapArray: Array<CharArray>, steps: Int = 0, direction: Char = 'U'): Int {
    var newDirection = direction
    // Check if the guard is blocked in their direction
    when (direction) {
        'U' -> {
            if (guardPos[0] - 1 >= 0 && mapArray[guardPos[0] - 1][guardPos[1]] == '#') {
                // If the guard is blocked, turn right
                newDirection = 'R'
            }
        }

        'D' -> {
            if (guardPos[0] + 1 < mapArray.size && mapArray[guardPos[0] + 1][guardPos[1]] == '#') {
                // If the guard is blocked, turn right
                newDirection = 'L'
            }
        }

        'L' -> {
            if (guardPos[0] >= 0 && guardPos[0] < mapArray.size && guardPos[1] - 1 >= 0 && mapArray[guardPos[0]][guardPos[1] - 1] == '#') {
                // If the guard is blocked, turn right
                newDirection = 'U'
            }
        }

        'R' -> {
            if (guardPos[0] >= 0 && guardPos[0] < mapArray.size && guardPos[1] + 1 < mapArray[0].size && mapArray[guardPos[0]][guardPos[1] + 1] == '#') {
                // If the guard is blocked, turn right
                newDirection = 'D'
            }
        }

        else -> return -1
    }

    if (newDirection == direction) {
        // If the guard is not blocked, move in the current direction
        when (direction) {
            'U' -> guardPos[0] -= 1
            'D' -> guardPos[0] += 1
            'L' -> guardPos[1] -= 1
            'R' -> guardPos[1] += 1
            else -> return -1
        }
    }

    if (guardPos[0] < 0 || guardPos[0] >= mapArray[0].size || guardPos[1] < 0 || guardPos[1] >= mapArray[0].size) {
        return steps
    }
    map[guardPos[0]][guardPos[1]] = 'X'
    return moveGuard(guardPos, mapArray, steps + 1, newDirection)
}
