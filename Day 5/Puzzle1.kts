import java.nio.file.Files
import java.nio.file.Paths

val path = Paths.get("input.txt")
var instructions = arrayOf<IntArray>()
var updates = arrayOf<IntArray>()
val lines = Files.readAllLines(path)

for (line in lines) {
    if (line.contains("|")) {
        instructions += line.split("|").map { it.toInt() }.toIntArray().let { arrayOf(it) }
        continue
    } else if (line == "") {
        continue
    }
    updates += line.split(",").map { it.toInt() }.toIntArray().let { arrayOf(it) }
}

var validUpdates = arrayOf<IntArray>()

outer@ for (update in updates) {
    var usableInstructions = arrayOf<IntArray>()
    for (instruction in instructions) {
        if (instruction[0] in update && instruction[1] in update) {
            usableInstructions += instruction
        }
    }

    for (usableInstruction in usableInstructions) {
        val updateX = usableInstruction[0]
        val updateY = usableInstruction[1]
        if (update.indexOf(updateX) < update.indexOf(updateY)) {
            continue
        }
        continue@outer
    }
    validUpdates += update
}

var sumMiddleUpate = 0
for (validUpdate in validUpdates) {
    val middleIndex = validUpdate.size / 2
    sumMiddleUpate += validUpdate[middleIndex]
}

println(sumMiddleUpate)