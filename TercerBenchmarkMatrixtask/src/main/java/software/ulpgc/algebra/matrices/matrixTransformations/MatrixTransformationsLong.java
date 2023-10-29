package software.ulpgc.algebra.matrices.matrixTransformations;

import software.ulpgc.algebra.matrices.matrix.ccsMatrix.CCSMatrixLong;
import software.ulpgc.algebra.matrices.matrix.coordinateMatrix.CoordinateMatrixLong;
import software.ulpgc.algebra.matrices.matrix.coordinates.CoordinateLong;
import software.ulpgc.algebra.matrices.matrix.csrMatrix.CRSMatrixLong;
import software.ulpgc.algebra.matrices.matrix.denseMatrix.DenseMatrixLong;
import software.ulpgc.algebra.matrices.matrixBuilder.CCSMatrixBuilder.CCSMatrixBuilderLong;
import software.ulpgc.algebra.matrices.matrixBuilder.CRSMatrixBuilder.CRSMatrixBuilderLong;
import software.ulpgc.algebra.matrices.matrixBuilder.coordinateMatrixBuilder.CoordinateMatrixBuilderLong;
import software.ulpgc.algebra.matrices.matrixBuilder.denseMatrixBuilder.DenseMatrixBuilderLong;

import java.util.ArrayList;

public class MatrixTransformationsLong {
    public CoordinateMatrixLong transformCoordinate(DenseMatrixLong matrix) {
        CoordinateMatrixBuilderLong builder = new CoordinateMatrixBuilderLong(matrix.size(), new ArrayList<>());
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.size(); j++) {
                if (matrix.get(i,j) == 0) continue;
                builder.set(new CoordinateLong(i+1,j+1, matrix.get(i,j)));
            }
        }
        return (CoordinateMatrixLong) builder.get();
    }

    public DenseMatrixLong transformDense(CoordinateMatrixLong matrix) {
        DenseMatrixBuilderLong builder = new DenseMatrixBuilderLong(matrix.size());
        for (CoordinateLong coordinate : matrix.getCoordinates()) {
            builder.set(coordinate.i()-1,coordinate.j()-1,coordinate.value());
        }
        return (DenseMatrixLong) builder.get();
    }

    public CRSMatrixLong transformCRS(CoordinateMatrixLong matrix){
        CRSMatrixBuilderLong builder = new CRSMatrixBuilderLong();
        for (CoordinateLong coordinate : matrix.sortCoordinatesByRow()){
            builder.set(coordinate.i(), coordinate.j(), coordinate.value());
        }
        return (CRSMatrixLong) builder.get();
    }

    public CCSMatrixLong transformCCS(CoordinateMatrixLong matrix){
        CCSMatrixBuilderLong builder = new CCSMatrixBuilderLong();
        for (CoordinateLong coordinate : matrix.sortCoordinatesByColumn()){
            builder.set(coordinate.i(), coordinate.j(), coordinate.value());
        }
        return (CCSMatrixLong) builder.get();
    }
}
