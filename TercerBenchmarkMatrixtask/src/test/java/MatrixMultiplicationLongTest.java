import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.Describable;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import software.ulpgc.algebra.matrices.Matrix;
import software.ulpgc.algebra.matrices.MatrixSerializer;
import software.ulpgc.algebra.matrices.matrix.ccsMatrix.CCSMatrixLong;
import software.ulpgc.algebra.matrices.matrix.coordinateMatrix.CoordinateMatrixLong;
import software.ulpgc.algebra.matrices.matrix.csrMatrix.CRSMatrixLong;
import software.ulpgc.algebra.matrices.matrix.denseMatrix.DenseMatrixLong;
import software.ulpgc.algebra.matrices.matrixOperations.MatrixOperationsLong;
import software.ulpgc.algebra.matrices.matrixSerializer.MatrixSerializerLong;
import software.ulpgc.algebra.matrices.matrixTransformations.MatrixTransformationsLong;

import org.junit.Assert;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class MatrixMultiplicationLongTest {

    private static List<Long> commonSupportVector;
    private static String matrixFileAPath;

    private static CoordinateMatrixLong matrixCoordinates;

    @BeforeClass
    public static void setUpSupportVector() {
        int size = initializeMatrixB().size();
        MatrixOperationsLong matrixOperationsLong = new MatrixOperationsLong();
        commonSupportVector = matrixOperationsLong.supportVector(size);
    }



    @BeforeClass
    public static void setUpCoordinateMatrix() throws FileNotFoundException {
        MatrixSerializer<Long> serializer = new MatrixSerializerLong();
        matrixCoordinates = (CoordinateMatrixLong) serializer.deserialize(System.getProperty("matrixFileAPath"));
    }

    private static CRSMatrixLong initializeMatrixA() {
        MatrixTransformationsLong matrixTransformationsLong = new MatrixTransformationsLong();
        return matrixTransformationsLong.transformCRS((CoordinateMatrixLong) matrixCoordinates);
    }

    private static CCSMatrixLong initializeMatrixB() {
        MatrixTransformationsLong matrixTransformationsLong = new MatrixTransformationsLong();
        return matrixTransformationsLong.transformCCS((CoordinateMatrixLong) matrixCoordinates);
    }

    private static  CCSMatrixLong initializeMatrixBV(){
        MatrixOperationsLong matrixOperationsLong = new MatrixOperationsLong();
        CoordinateMatrixLong matrixCoordinatesAltered =  matrixOperationsLong.multiplySupportVector(commonSupportVector, (CoordinateMatrixLong) matrixCoordinates);
        MatrixTransformationsLong matrixTransformationsLong = new MatrixTransformationsLong();
        return matrixTransformationsLong.transformCCS(matrixCoordinatesAltered);
    }


    private static CoordinateMatrixLong initializeExpectedResultC() {
        MatrixOperationsLong matrixOperationsLong = new MatrixOperationsLong();
        return matrixOperationsLong.multiplyCompressed(initializeMatrixA(), initializeMatrixB());
    }

    private static CoordinateMatrixLong initializeExpectedResultCV() {
        MatrixOperationsLong matrixOperationsLong = new MatrixOperationsLong();
        return matrixOperationsLong.multiplyCompressed(initializeMatrixA(), initializeMatrixBV());
    }

    @Test
    public static void testMatrixMultiplicationCompressed() {
        // Calculate the result C using your matrix multiplication function
        MatrixOperationsLong matrixOperationsLong = new MatrixOperationsLong();
        CoordinateMatrixLong C = matrixOperationsLong.multiplyCompressed(initializeMatrixA(), initializeMatrixB());
        // Assert that the actual result is equal to the expected result
        assertEquals(C.getCoordinates(), initializeExpectedResultC().getCoordinates());
        System.out.println("Completed test compressed matrix multiplication! (AxB = ExpectedC)");
    }

    @Test
    public static void testMatrixMultiplicationSupportVector(){
        // Calculate the result C using your matrix multiplication function
        MatrixOperationsLong matrixOperationsLong = new MatrixOperationsLong();
        CoordinateMatrixLong C = matrixOperationsLong.multiplyCompressed(initializeMatrixA(), initializeMatrixBV());

        // Assert that the actual result is equal to the expected result
        assertEquals(C.getCoordinates(), initializeExpectedResultCV().getCoordinates());
        System.out.println("Completed test with support vector! (AxBv = ExpectedCv)");
    }

    @Test
    public static void compareWithDense(){
        MatrixTransformationsLong matrixTransformationsLong = new MatrixTransformationsLong();
        DenseMatrixLong denseMatrixLong = matrixTransformationsLong.transformDense(matrixCoordinates);
        MatrixOperationsLong matrixOperationsLong = new MatrixOperationsLong();
        DenseMatrixLong denseMatrixLong1 = matrixOperationsLong.multiplyDense(denseMatrixLong, denseMatrixLong);
        CoordinateMatrixLong coordinateMatrixLong = matrixTransformationsLong.transformCoordinate(denseMatrixLong1);
        assertEquals(initializeExpectedResultC().getCoordinates(), coordinateMatrixLong.getCoordinates());
        System.out.println("Completed Test with Dense Matrix multiplication! (DenseMatrixMultiplication = Compressed Matrix Multiplication)");
    }


    public static void main(String[] args) throws FileNotFoundException {
        System.setProperty("matrixFileAPath", "/matrix128.mtx");

        // Call setUpCoordinateMatrix to initialize matrixCoordinates
        setUpCoordinateMatrix();
        setUpSupportVector();


        // Measure and display execution time for testMatrixMultiplicationNormal() (1st time)
        long startTime = System.currentTimeMillis();
        testMatrixMultiplicationCompressed();
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("testMatrixMultiplicationCompressed() executed in " + executionTime + " milliseconds");

        // Measure and display execution time for testMatrixMultiplicationNormal() (2nd time)
        startTime = System.currentTimeMillis();
        testMatrixMultiplicationSupportVector();
        endTime = System.currentTimeMillis();
        executionTime = endTime - startTime;
        System.out.println("testMatrixMultiplicationSupportVector() executed in " + executionTime + " milliseconds");

        // Measure and display execution time for compareWithDense()
        startTime = System.currentTimeMillis();
        compareWithDense();
        endTime = System.currentTimeMillis();
        executionTime = endTime - startTime;
        System.out.println("compareWithDense() executed in " + executionTime + " milliseconds");
    }


}



