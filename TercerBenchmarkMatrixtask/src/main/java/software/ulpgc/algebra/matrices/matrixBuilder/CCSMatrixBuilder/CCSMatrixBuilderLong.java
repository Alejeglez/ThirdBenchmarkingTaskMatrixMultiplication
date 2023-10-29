package software.ulpgc.algebra.matrices.matrixBuilder.CCSMatrixBuilder;

import software.ulpgc.algebra.matrices.Matrix;
import software.ulpgc.algebra.matrices.matrix.ccsMatrix.CCSMatrixLong;
import software.ulpgc.algebra.matrices.matrixBuilder.sparseMatrixBuilder.SparseMatrixBuilderLong;

import java.util.ArrayList;
import java.util.List;

public class CCSMatrixBuilderLong extends SparseMatrixBuilderLong {
    private List<Integer> rows;
    private List<Long> values;
    private List<Integer> columnPointers;
    private int currentColumn;

    public CCSMatrixBuilderLong() {
        this.rows = new ArrayList<>();
        this.values = new ArrayList<>();
        this.columnPointers = new ArrayList<>();
        this.currentColumn = 0;
        columnPointers.add(0);
    }

    @Override
    public void set(int i, int j, Long value) {
        rows.add(i);
        values.add(value);
        if(currentColumn < j){
            columnPointers.add(values.size()-1);
            currentColumn = j;
        }
    }

    @Override
    public Matrix<Long> get() {
        columnPointers.add(values.size());
        return new CCSMatrixLong(columnPointers, rows, values);
    }
}
