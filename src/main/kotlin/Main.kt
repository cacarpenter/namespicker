import java.nio.file.Files
import java.nio.file.Paths
import java.util.Random

val NAMESFILE = "names.txt"

val random = Random()

fun main() {
    println("Name Picker!")

    val names = Files.readAllLines(Paths.get(NAMESFILE))
    println(names)

    var numLeft = names.size

    while (numLeft > 0) {
        if(names.size < 2) {
            println("No one left for ${names[0]} :(")
            break
        }
        val matchWith = 1 + random.nextInt(names.size - 1)
        val first = names.get(0)
        val sec = names.get(matchWith)
        println("$first with $sec")
        names.removeAt(matchWith)
        names.removeAt(0)
        numLeft -= 2
    }
    
}