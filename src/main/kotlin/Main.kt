import java.nio.file.Files
import java.nio.file.Paths

val NAMESFILE = "names.txt"

fun main() {
    println("Name Drawer!")

    val names = Files.readAllLines(Paths.get(NAMESFILE))
    println(names)

    
}