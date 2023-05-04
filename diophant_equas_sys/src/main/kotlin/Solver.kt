import kotlin.math.abs

fun canResolve(aValues: Array<IntArray>, m: Int): Boolean {
    var isCouldResolve = true
    for (j in 0 until m) {
        isCouldResolve = isCouldResolve && (aValues[j][0] == 0)
    }
    return isCouldResolve
}

fun getRang(matrix: Array<IntArray>): Int {
    var r = 0
    val end = matrix.size - matrix[0].size
    for (i in 0 until end) {
        if (matrix[i][i] > 0) {
            r = i
        } else {
            break
        }
    }
    return r + 1
}

fun resolve(aMatrix: Array<IntArray>, bVec: IntArray): Pair<Boolean, IntArray> {
    val n = aMatrix[0].size
    val m = aMatrix.size

    val notBVec = Array(bVec.size) { intArrayOf(-bVec[it]) }
    val zeroVec = Array(n) { intArrayOf(0) }
    val eMatrix = Array(n) { i -> IntArray(n) { j -> if (i == j) 1 else 0 } }

    val aEMatrixes = aMatrix.plus(eMatrix)
    val cKMatrixes = tril(aEMatrixes)

    val r = getRang(cKMatrixes)
    val aValues = notBVec.plus(zeroVec)

    var k = 0
    while (k < r && cKMatrixes[k][k] != 0) {
        val kI = aValues[k][0] / cKMatrixes[k][k]
        aValues.indices.forEach { i ->
            aValues[i][0] -= kI * cKMatrixes[i][k]
        }
        k++
    }

    return if (canResolve(aValues, m)) {
        val x0 = IntArray(aValues.size - m)
        for (i in m until aValues.size) {
            x0[i - m] = aValues[i][0]
        }

        val kVectors = cKMatrixes.sliceArray(m until cKMatrixes.size).map { it.sliceArray(r - 1 until it.size) }
        val kVectorsSumStr = kVectors.joinToString(" + ") { "t_${it.indexOfFirst { value -> abs(value) > 0.0 } + 1}*[${it.joinToString(", ")}]" }
         println("Particular solution is $x0 + $kVectorsSumStr")
        Pair(true, x0)
    } else {
         println("System cannot be resolved in whole numbers!")
        Pair(false, aValues[m])
    }
}
