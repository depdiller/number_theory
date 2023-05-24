def is_reduced_after_i(matrix, i):
    n, m = matrix.shape
    return all(matrix[i, j] == 0 for j in range(i + 1, m - 1))


def divide(matrix, row):
    n, m = matrix.shape
    for i in range(row + 1, m - 1):
        d = matrix[row, i] // matrix[row, row]
        minus(matrix, i, row, d)


def minus(matrix, i, j, d):
    matrix[:, i] -= matrix[:, j] * d


def inverse(matrix, i):
    matrix[:, i] *= -1


def index_of_min_non_zero(row):
    abs_row = [abs(value) for value in row]
    min_non_zero = 0
    min_value = float('inf')

    for i, val in enumerate(abs_row):
        if val != 0 and val < min_value:
            min_value = val
            min_non_zero = i

    return min_non_zero

