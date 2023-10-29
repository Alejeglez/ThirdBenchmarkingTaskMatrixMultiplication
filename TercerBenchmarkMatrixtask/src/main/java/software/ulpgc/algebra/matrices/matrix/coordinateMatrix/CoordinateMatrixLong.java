package software.ulpgc.algebra.matrices.matrix.coordinateMatrix;

import software.ulpgc.algebra.matrices.matrix.coordinates.CoordinateLong;
import software.ulpgc.algebra.matrices.matrix.sparseMatrix.SparseMatrixLong;

import java.util.Comparator;
import java.util.List;

public class CoordinateMatrixLong extends SparseMatrixLong {
    private final int size;



    private final List<CoordinateLong> coordinates;

    public CoordinateMatrixLong(int size, List<CoordinateLong> coordinates) {
        this.size = size;
        this.coordinates = coordinates;
    }

    public List<CoordinateLong> getCoordinates() {
        return coordinates;
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public Long get(int i, int j) {
        return coordinates.stream()
                .filter(c->c.i() == i & c.j() == j)
                .findFirst()
                .map(CoordinateLong::value)
                .orElse(0L);
    }

    public List<CoordinateLong> sortCoordinatesByColumn() {
        coordinates.sort(new Comparator<CoordinateLong>() {
            @Override
            public int compare(CoordinateLong c1, CoordinateLong c2) {
                int result = Integer.compare(c1.j(), c2.j());
                if (result == 0) {
                    result = Integer.compare(c1.i(), c2.i());
                }
                return result;
            }
        });
        return coordinates;
    }

    public List<CoordinateLong> sortCoordinatesByRow() {
        coordinates.sort(new Comparator<CoordinateLong>() {
            @Override
            public int compare(CoordinateLong c1, CoordinateLong c2) {
                int result = Integer.compare(c1.i(), c2.i());
                if (result == 0) {
                    result = Integer.compare(c1.j(), c2.j());
                }
                return result;
            }
        });
        return coordinates;
    }
}
