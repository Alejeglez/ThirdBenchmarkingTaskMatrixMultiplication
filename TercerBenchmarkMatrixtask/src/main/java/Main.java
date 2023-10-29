import software.ulpgc.algebra.matrices.MatrixSerializer;
import software.ulpgc.algebra.matrices.matrixOperations.RandomMatrix;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        RandomMatrix randomMatrix = new RandomMatrix();
        for (int i  = 128; i <= 16384; i *= 2){
            randomMatrix.generateCoordianteMatrix(i);
        }
    }
}
