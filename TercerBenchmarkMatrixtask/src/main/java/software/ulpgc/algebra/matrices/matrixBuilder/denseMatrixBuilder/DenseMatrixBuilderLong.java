package software.ulpgc.algebra.matrices.matrixBuilder.denseMatrixBuilder;

import software.ulpgc.algebra.matrices.Matrix;
import software.ulpgc.algebra.matrices.MatrixBuilder;
import software.ulpgc.algebra.matrices.matrix.denseMatrix.DenseMatrixLong;

public class DenseMatrixBuilderLong implements MatrixBuilder<Long> {

    private final int size;
    private final long[][] values;

    public DenseMatrixBuilderLong(int size) {
        this.size = size;
        this.values = new long[size][size];
    }

    @Override
    public void set(int i, int j, Long value) {
        values[i][j] += value;
    }

    @Override
    public Matrix<Long> get() {
        return new DenseMatrixLong(values);
    }
}