import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

//val NAMESFILE = "lots-of-names.txt"
val NAMESFILE = "names.txt"
//val NAMESFILE = "us.txt"

val random = Random()

fun main(args: Array<String>) {
    println("Name Picker!")
    val nameFile = if (args.size > 0) args[0] else NAMESFILE
    println("Using $nameFile")
    val readNames = Files.readAllLines(Paths.get(nameFile))
    println(readNames.map { l -> l.split(',').joinToString(" and ") { it } })
    val pairMap = HashMap<String, String?>()
    for(line in readNames) {
        if(line == null) {
            continue
        }
        if(line.contains(',')) {
            val couple = line.split(',').map { c -> c.trim() }
            pairMap.put(couple.get(0), couple.get(1))
            pairMap.put(couple.get(1), couple.get(0))
        } else {
            pairMap.put(line.trim(), null)
        }
    }
    val result = randomReceiver(pairMap)
    println("-----------------")
    for((giver, receiver) in result) {
        println("$giver gives to $receiver")
    }
}

fun randomReceiver(names: Map<String, String?>): Map<String,String> {
    val leftToRec = ArrayList(names.keys)
    val leftToGive = ArrayList(names.keys)
    val result = HashMap<String,String>()
    var c = 0
    while(leftToRec.isNotEmpty() && leftToGive.isNotEmpty() && c < 20) {
        val gIdx = random.nextInt(leftToGive.size)
        val giver = leftToGive.get(gIdx)
        var receiver: String? = null
        var rIdx = -1
        var p = 0
        while((receiver == null || giver == receiver || names[giver] == receiver || result[giver] == receiver || result[receiver] == giver) && p < 20) {
            rIdx = random.nextInt(leftToRec.size)
            receiver = leftToRec.get(rIdx)
            p++
        }
        if(receiver == null) {
            throw IllegalStateException("Giving Up")
        }
        leftToRec.removeAt(rIdx)
        leftToGive.removeAt(gIdx)
        result[giver] = receiver
        c++
    }
    return result
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
