package software.ulpgc.algebra.matrices.matrix.denseMatrix;

import software.ulpgc.algebra.matrices.Matrix;

public class DenseMatrixLong implements Matrix<Long> {




    private final long[][] values;

    public DenseMatrixLong(long[][] values) {
        this.values = values;
    }
    @Override
    public int size() {
        return values.length;
    }

    @Override
    public Long get(int i, int j) {
        return values[i][j];
    }
    public long[][] getValues() {
        return values;
    }

}
