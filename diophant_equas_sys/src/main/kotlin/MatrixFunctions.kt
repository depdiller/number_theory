import kotlin.math.abs

fun isZeros(array: IntArray): Boolean {
    for (elem in array) {
        if (elem != 0) {
            return false
        }
    }
    return true
}

fun getDividers(array: IntArray, divider: Int): IntArray {
    val res = ArrayList<Int>()
    return if (divider < 0) {
        for (i in array.indices) {
            if (array[i] < 0) {
                res.add(abs(array[i] / abs(divider)))
            } else {
                res.add(-1 * (array[i] / divider))
            }
        }
        res.toIntArray()
    } else {
        array.map { (it / divider) }.toIntArray()
    }
}

fun tril(matrix: Array<IntArray>): Array<IntArray> {
    for (i in matrix.indices) {
        while (i + 1 < matrix[0].size && !isZeros(matrix[i].copyOfRange(i + 1, matrix[i].size))) {

            val subMatrix = matrix.sliceArray(i until matrix.size)
                .map { it.sliceArray(i until matrix[0].size) }
                .let { sort(it.toTypedArray()) }
            subMatrix.forEachIndexed { index, row ->
                row.copyInto(matrix[index + i], i, 0, row.size)
            }

            val dividers = getDividers(matrix[i].copyOfRange(i + 1, matrix[i].size), matrix[i][i])
            for ((j, div) in dividers.withIndex()) {
                for (row in matrix.indices) {
                    matrix[row][(i + 1) + j] -= (matrix[row][i] * div)
                }
            }
        }
    }
    return matrix
}

fun sort(matrix: Array<IntArray>): Array<IntArray> {
    var k = matrix[1].size - 1
    while (k > 0) {
        var ind = 0
        for (j in 1 .. k) {
            if (abs(matrix[0][j]) > abs(matrix[0][ind]) && abs(matrix[0][ind]) > 0) {
                ind = j
            }
        }
        val n = matrix.size
        for (i in 0 until n) {
            val b = matrix[i][ind]
            matrix[i][ind] = matrix[i][k]
            matrix[i][k] = b
        }
        k -= 1
    }

    var nulls = 0
    val n = matrix[1].size
    for (i in 0 until n) {
        if (matrix[0][i] != 0) {
            break
        }
        nulls += 1
    }

    if (nulls > 0) {
        for (i in matrix.indices) {
            val b = matrix[i][nulls]
            matrix[i][nulls] = matrix[i][0]
            matrix[i][0] = b
        }
    }

    return matrix
}