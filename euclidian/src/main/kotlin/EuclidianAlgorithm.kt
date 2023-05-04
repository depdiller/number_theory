import java.util.Scanner

fun main() {
    val a: Long = readLong("a")
    val b: Long = readLong("b")

    println("Least Common Multiple: ${lcm(a, b)}")
    println("Greatest Common Divisor: ${gcd(a, b)}")
}

fun readLong(varName: String): Long {
    println("Input \"$varName\": ")
    val scanner = Scanner(System.`in`)
    val input = scanner.nextLine()

    val trimmed = input.trim()
    var result: Long = 0

    try {
        val i = trimmed.toLong()
        println("your integer input: $i")
        result = i
    } catch (e: NumberFormatException) {
        println("this was not an integer: $trimmed")
    }

    return result
}

fun lcm(a: Long, b: Long): Long {
    return (a * b) / gcd(a, b)
}

fun gcd(a: Long, b: Long): Long {
    return if (a == 0L) {
        b
    } else if (b == 0L) {
        a
    } else if (a > b) {
        gcd(a % b, b)
    } else {
        gcd(b % a, a)
    }
}
