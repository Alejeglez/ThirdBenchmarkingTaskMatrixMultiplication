package software.ulpgc.algebra.matrices.matrixBuilder.coordinateMatrixBuilder;

import software.ulpgc.algebra.matrices.Matrix;
import software.ulpgc.algebra.matrices.matrix.coordinateMatrix.CoordinateMatrixLong;
import software.ulpgc.algebra.matrices.matrix.coordinates.CoordinateLong;
import software.ulpgc.algebra.matrices.matrixBuilder.sparseMatrixBuilder.SparseMatrixBuilderLong;

import java.util.List;

public class CoordinateMatrixBuilderLong extends SparseMatrixBuilderLong {

    protected final int size;
    protected final List<CoordinateLong> coordinates;


    public List<CoordinateLong> getCoordinates() {
        return coordinates;
    }

    public CoordinateMatrixBuilderLong(int size, List<CoordinateLong> coordinates) {
        this.size = size;
        this.coordinates = coordinates;
    }

    @Override
    public void set(int i, int j, Long value) {
        set(new CoordinateLong(i,j,value));
    }

    public void set(CoordinateLong coordinate) {
        coordinates.add(coordinate);
    }
    @Override
    public Matrix<Long> get() {
        return new CoordinateMatrixLong(size, coordinates);
    }

}
