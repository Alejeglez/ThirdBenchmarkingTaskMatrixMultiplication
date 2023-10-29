package software.ulpgc.algebra.matrices.matrixOperations;

import software.ulpgc.algebra.matrices.matrix.coordinateMatrix.CoordinateMatrixLong;
import software.ulpgc.algebra.matrices.matrixBuilder.coordinateMatrixBuilder.CoordinateMatrixBuilderLong;
import software.ulpgc.algebra.matrices.matrixSerializer.MatrixSerializerLong;

import java.util.ArrayList;
import java.util.Random;

public class RandomMatrix {
    public void generateCoordianteMatrix(int size) {
        CoordinateMatrixBuilderLong coordinateMatrixBuilderLong = new CoordinateMatrixBuilderLong(size, new ArrayList<>());
        if (size <= 0) {
            throw new IllegalArgumentException("Invalid matrix size");
        }
        Random random = new Random();

        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                if (random.nextDouble() > 0.8) { // Set sparsity to 40%
                    // Generate a non-zero value
                    coordinateMatrixBuilderLong.set(i, j, random.nextLong());
                }
                // Zeros are already initialized by default
            }
        }

        CoordinateMatrixLong coordinateMatrixLong = (CoordinateMatrixLong) coordinateMatrixBuilderLong.get();
        MatrixSerializerLong matrixSerializerLong = new MatrixSerializerLong();
        matrixSerializerLong.serialize(coordinateMatrixLong);
    }
}
