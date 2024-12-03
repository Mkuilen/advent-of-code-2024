import java.nio.file.Files
import java.nio.file.Paths

val path = Paths.get("input.txt")
val regex = "mul\\(\\d{1,3},\\d{1,3}\\)".toRegex()
var instructions = mutableListOf<List<String>>()

val lines = Files.readAllLines(path)
for (line in lines) {
    val mulInstructions = regex.findAll(line).map { it.value }.toList()
    instructions.add(mulInstructions)
}

val numRegex = "\\d+".toRegex()
var multipliedNumbers = intArrayOf()

for (instruct in instructions) {
    for (mulInstruction in instruct) {
        val numbers = numRegex.findAll(mulInstruction).map { it.value.toInt() }.toList()
        val result = numbers[0] * numbers[1]
        multipliedNumbers += result
    }
}

print(multipliedNumbers.sum())