from sympy import Matrix
import sys
from matrixutli import *


def transform_matrix(matrix):
    n, m = matrix.shape
    neg_last_col = [-x for x in matrix[:, m - 1]]
    matrix[:, m - 1] = neg_last_col

    identity_matrix = Matrix.eye(m)
    identity_matrix.row_del(m - 1)
    matrix = matrix.col_join(identity_matrix)

    for i in range(n):
        while not is_reduced_after_i(matrix, i):
            index = index_of_min_non_zero(matrix[i, i:m - 1]) + i
            if matrix[i, index] == 0:
                continue
            if matrix[i, index] < 0:
                inverse(matrix, index)
            if index != i:
                matrix = matrix.elementary_col_op("n<->m", col1=i, col2=index)
            divide(matrix, i)
    return matrix


def read_matrix_from_file(file):
    with open(file, 'r') as f:
        rows = f.read().splitlines()
        matrix_data = [[int(x) for x in row.split()] for row in rows]
        matrix = Matrix(matrix_data)
        return matrix


def solve_system(matrix):
    n, m = matrix.shape
    rank = 0
    for i in range(n - m + 1):
        if matrix[i, i] != 0:
            k = matrix[i, m - 1] // matrix[i, i]
            minus(matrix, m - 1, i, k)
        else:
            rank = i
            break
        rank = i + 1

    res = []
    for i in range(n - m + 1):
        if matrix[i, m - 1] != 0:
            return res
    for j in range(rank, m):
        res.append(matrix[n - m + 1:, j].tolist())
    return res


def main():
    if len(sys.argv) > 1:
        file_path = sys.argv[1]
        matrix = read_matrix_from_file(file_path)
        transformed_matrix = transform_matrix(matrix)
        print(f"Transformed matrix: {transformed_matrix}")
        res = solve_system(transformed_matrix)
        print(res)
    else:
        print("No parameters were provided")


if __name__ == '__main__':
    main()

