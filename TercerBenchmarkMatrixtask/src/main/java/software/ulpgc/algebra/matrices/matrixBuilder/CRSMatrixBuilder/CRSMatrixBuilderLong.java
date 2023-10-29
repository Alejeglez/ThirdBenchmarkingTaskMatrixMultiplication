package software.ulpgc.algebra.matrices.matrixBuilder.CRSMatrixBuilder;

import software.ulpgc.algebra.matrices.Matrix;
import software.ulpgc.algebra.matrices.matrix.csrMatrix.CRSMatrixLong;
import software.ulpgc.algebra.matrices.matrixBuilder.sparseMatrixBuilder.SparseMatrixBuilderLong;

import java.util.ArrayList;
import java.util.List;

public class CRSMatrixBuilderLong extends SparseMatrixBuilderLong {

    private List<Long> values;
    private List<Integer> columns;
    private List<Integer> rowPointers;
    private int currentRow;

    public CRSMatrixBuilderLong() {
        this.values = new ArrayList<>();
        this.columns = new ArrayList<>();
        this.rowPointers = new ArrayList<>();
        this.currentRow = 0;
        this.rowPointers.add(0);
    }

    @Override
    public void set(int i, int j, Long value) {
        columns.add(j);
        values.add(value);
        if(currentRow < i){
            rowPointers.add(values.size()-1);
            currentRow = i;
        }
    }

    @Override
    public Matrix<Long> get() {
        rowPointers.add(values.size());
        return new CRSMatrixLong(rowPointers, columns, values);
    }
}
