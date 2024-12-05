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

var correctedUpdates = arrayOf<IntArray>()
var updateToCheck = intArrayOf()

for (update in updates) {
    updateToCheck = update
    var updateValid: MutableList<Boolean>
    var usableInstructions = arrayOf<IntArray>()
    for (instruction in instructions) {
        if (instruction[0] in update && instruction[1] in update) {
            usableInstructions += instruction
        }
    }

    do {
        updateValid = checkIfValid(updateToCheck, usableInstructions)
    } while(updateValid.contains(false))

    if (!updateToCheck.contentEquals(update)) {
        println("Original update: ${update.contentToString()}")
        println("Corrected update: ${updateToCheck.contentToString()}")
        correctedUpdates += updateToCheck
    }
}

var sumMiddleUpdateCorrected = 0
for (correctedUpdate in correctedUpdates) {
    val middleIndex = correctedUpdate.size / 2
    sumMiddleUpdateCorrected += correctedUpdate[middleIndex]
}

println(sumMiddleUpdateCorrected)

fun checkIfValid(update: IntArray, instructions: Array<IntArray>): MutableList<Boolean> {
    val updateValid = mutableListOf<Boolean>()
    for (usableInstruction in instructions) {
        val updateX = usableInstruction[0]
        val updateY = usableInstruction[1]
        if (update.indexOf(updateX) < update.indexOf(updateY)) {
            continue
        } else {
            updateValid.add(false)
            updateToCheck = correctArray(update, usableInstruction)
        }
    }
    return updateValid
}

fun correctArray(update: IntArray, instructions: IntArray): IntArray {
    val faultyUpdate = update.toMutableList()
    val instructionX = instructions[0]
    val instructionY = instructions[1]
    val updateXIndex = faultyUpdate.indexOf(instructionX)
    val updateYIndex = faultyUpdate.indexOf(instructionY)
    val updateYValue = faultyUpdate[updateYIndex]
    faultyUpdate.removeAt(updateYIndex)
    faultyUpdate.add(updateXIndex, updateYValue)
    return faultyUpdate.toIntArray()
}