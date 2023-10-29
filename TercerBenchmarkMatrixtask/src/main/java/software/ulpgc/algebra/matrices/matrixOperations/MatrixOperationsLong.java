package software.ulpgc.algebra.matrices.matrixOperations;

import software.ulpgc.algebra.matrices.Matrix;
import software.ulpgc.algebra.matrices.matrix.ccsMatrix.CCSMatrixLong;
import software.ulpgc.algebra.matrices.matrix.coordinateMatrix.CoordinateMatrixLong;
import software.ulpgc.algebra.matrices.matrix.coordinates.CoordinateLong;
import software.ulpgc.algebra.matrices.matrix.csrMatrix.CRSMatrixLong;
import software.ulpgc.algebra.matrices.matrix.denseMatrix.DenseMatrixLong;
import software.ulpgc.algebra.matrices.matrix.sparseMatrix.SparseMatrixLong;
import software.ulpgc.algebra.matrices.matrixBuilder.CCSMatrixBuilder.CCSMatrixBuilderLong;
import software.ulpgc.algebra.matrices.matrixBuilder.coordinateMatrixBuilder.CoordinateMatrixBuilderLong;
import software.ulpgc.algebra.matrices.matrixBuilder.denseMatrixBuilder.DenseMatrixBuilderLong;
import software.ulpgc.algebra.matrices.matrixBuilder.sparseMatrixBuilder.SparseMatrixBuilderLong;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MatrixOperationsLong {
    public CoordinateMatrixLong multiplyCompressed(CRSMatrixLong crsMatrixLong, CCSMatrixLong ccsMatrixLong){
        int maxSize = Math.max(crsMatrixLong.size(), ccsMatrixLong.size());
        CoordinateMatrixBuilderLong builderLong = new CoordinateMatrixBuilderLong(maxSize, new ArrayList<>());
        for (int i = 0; i < crsMatrixLong.size() - 1; i++){
            for (int j = 0; j < ccsMatrixLong.size() - 1; j++){
                int actualRowPointer = crsMatrixLong.rowPointers.get(i);
                int followingRowPointer = crsMatrixLong.rowPointers.get(i+1);
                int actualColPointer = ccsMatrixLong.columnPointers.get(j);
                int followingColPointer = ccsMatrixLong.columnPointers.get(j+1);
                long s = 0;
                while (actualRowPointer < followingRowPointer && actualColPointer < followingColPointer){
                    int aa = crsMatrixLong.columns.get(actualRowPointer);
                    int bb = ccsMatrixLong.rows.get(actualColPointer);
                    if (aa == bb){
                        s += crsMatrixLong.values.get(actualRowPointer) * ccsMatrixLong.values.get(actualColPointer);
                        actualRowPointer++;
                        actualColPointer++;
                    }
                    else if (aa < bb) actualRowPointer++;
                    else actualColPointer++;
                }
                if (s != 0) builderLong.set(i, j, s);
            }
        }

        return (CoordinateMatrixLong) builderLong.get();
    }

    public List<Long> supportVector(int size) {
        List<Long> vector = new ArrayList<>();
        Random random = new Random();

        // Generate random values for each element in the vector
        for (int i = 0; i <= size; i++) {
            long randomValue = random.nextLong();
            vector.add(randomValue);
        }

        return vector;
    }

    public CoordinateMatrixLong multiplySupportVector(List<Long> supportVector, CoordinateMatrixLong matrix){
        CoordinateMatrixBuilderLong matrixBuilderLong = new CoordinateMatrixBuilderLong(matrix.size(), new ArrayList<>());
        for (int i = 0; i <= matrix.size(); i++){
            for (int j = 0; j <= matrix.size(); j ++) {
                if (matrix.get(i, j) * supportVector.get(j) != 0) {
                    matrixBuilderLong.set(i, j, matrix.get(i, j) * supportVector.get(i));
                }
            }
        }
        return (CoordinateMatrixLong) matrixBuilderLong.get();
    }

    public DenseMatrixLong multiplyDense(DenseMatrixLong matrixLongA, DenseMatrixLong matrixLongB){
        DenseMatrixBuilderLong matrixBuilderLong = new DenseMatrixBuilderLong(matrixLongA.size());
        assert matrixLongA.size() == matrixLongB.size();
        for(int i = 0; i < matrixLongA.size(); i++){
            for(int j = 0; j < matrixLongA.size(); j++){
                for (int k = 0; k < matrixLongA.size(); k++) {
                    matrixBuilderLong.set(i, j, matrixLongA.get(i, k) * matrixLongB.get(k, j));
                }
            }
        }
        return (DenseMatrixLong) matrixBuilderLong.get();
    }
}
