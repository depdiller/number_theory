import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.random.Random

class TestSolver {
    private fun generateExample(m: Int, n: Int): Pair<Array<IntArray>, IntArray> {
        val matrix = Array(m) { IntArray(n) { Random.nextInt(-50, 50) } }
        val values = IntArray(m) { Random.nextInt(-50, 50) }
        return Pair(matrix, values)
    }

    private fun generateDims(): Pair<Int, Int> {  // returns m and n : m < n
        var m = Random.nextInt(3, 10)
        var n = Random.nextInt(3, 10)
        if (m >= n) {
            val temp = m
            m = n
            n = temp
        }
        return Pair(m, n)
    }

    private fun substitute(matrix: Array<IntArray>, values: IntArray): IntArray {
        val res = ArrayList<Int>()
        for (i in matrix.indices) {
            var dotProduct = 0
            for (j in matrix[i].indices) {
                dotProduct += values[j] * matrix[i][j]
            }
            res.add(dotProduct)
        }
        return res.toIntArray()
    }

    @Test
    fun testSolver() {
        val nOfTests = 1000
        var passed = 0
        var notResolved = 0
        for (i in 0 until nOfTests) {
            val (m, n) = generateDims()
            val (matrix, values) = generateExample(m, n)
            val copyMatrix = Array(matrix.size) { matrix[it].copyOf() }
            val (isResolved, partSolution) = resolve(matrix, values)
            if (isResolved) {
                val valuesExpected = substitute(copyMatrix, partSolution)
                assertTrue(values.contentEquals(valuesExpected), "Failed test $i")
                passed++
            } else {
                notResolved++
            }
        }
        println("Passed: $passed / $nOfTests")
        println("Cannot be resolved: $notResolved / $nOfTests")
        println("Failed: ${100 - (passed + notResolved)} / $nOfTests")
    }
}
