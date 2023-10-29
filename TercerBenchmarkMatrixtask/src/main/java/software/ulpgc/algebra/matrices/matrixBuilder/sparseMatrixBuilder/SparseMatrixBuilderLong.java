package software.ulpgc.algebra.matrices.matrixBuilder.sparseMatrixBuilder;

import software.ulpgc.algebra.matrices.Matrix;
import software.ulpgc.algebra.matrices.MatrixBuilder;

public abstract class SparseMatrixBuilderLong implements MatrixBuilder<Long> {


    public abstract Matrix<Long> get();
}
