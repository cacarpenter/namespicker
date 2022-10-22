import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

//val NAMESFILE = "lots-of-names.txt"
//val NAMESFILE = "names.txt"
val NAMESFILE = "us.txt"

val random = Random()

fun main(args: Array<String>) {
    println("Name Picker!")
    val nameFile = if (args.size > 0) args[0] else NAMESFILE
    println(nameFile)
    val readNames: MutableList<String> = Files.readAllLines(Paths.get(nameFile))
    val names = readNames.filter { n -> n.isNotBlank() }.map { n -> n.trim() }
    println(names)
    println("-------------------------")
    randomReceiver(names)
}

fun randomReceiver(names: List<String>) {
    val leftToRec = arrayListOf<String>()
    leftToRec.addAll(names)
    var n = 0
    while(leftToRec.isNotEmpty()) {
        val giver = names[n]
        var rn = random.nextInt(leftToRec.size)
        while(giver == leftToRec.get(rn)) {
            rn = random.nextInt(leftToRec.size)
        }
        val receiver = leftToRec.get(rn)
        leftToRec.removeAt(rn)
        n++
        println("$giver gives to $receiver")
    }
}

fun pairUp(names: MutableList<String>) {

    var numLeft = names.size

    while (numLeft > 0) {
        if (numLeft < 2) {
            println("No one left for ${names[0]}! :(")
            break
        }
        if (numLeft == 2) {
            println("${names[0]} with ${names[1]}")
            break
        }
        var match = random.nextInt(numLeft)
        var matchWith = random.nextInt(numLeft)
        while (matchWith == match) {
            match = random.nextInt(numLeft)
            matchWith = random.nextInt(numLeft)
        }
        val first = names.get(match)
        val sec = names.get(matchWith)
        println("$first with $sec")
        names.removeAt(Math.max(match, matchWith))
        names.removeAt(Math.min(match, matchWith))
        numLeft -= 2
        println()
    }
}
