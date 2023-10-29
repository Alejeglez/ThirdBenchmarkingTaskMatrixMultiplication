package software.ulpgc.algebra.matrices.matrix.csrMatrix;

import software.ulpgc.algebra.matrices.matrix.sparseMatrix.SparseMatrixLong;

import java.util.Collections;
import java.util.List;

public class CRSMatrixLong extends SparseMatrixLong {

    public List<Integer> rowPointers;
    public List<Integer> columns;
    public List<Long> values;

    public CRSMatrixLong(List<Integer> rowPointers, List<Integer> columns, List<Long> values) {
        this.rowPointers = rowPointers;
        this.columns = columns;
        this.values = values;
    }

    @Override
    public int size() {
        // Calculate the maximum column index
        int maxColumnIndex = columns.isEmpty() ? -1 : Collections.max(columns);

        // Get the size of the rowPointers list
        int rowPointersSize = rowPointers.size();

        // Return the maximum value between maxColumnIndex and rowPointersSize
        return rowPointersSize;
    }

    @Override
    public Long get(int i, int j) {
        int index = 0;
        int indexPointer = 0;
        int appearence = 0;
        while (index < columns.size()){
            if (appearence >= rowPointers.get(indexPointer+1)){
                indexPointer += 1;
            }
            if(columns.get(index) == j && i == indexPointer){
                return(values.get(index));
            }
            appearence += 1;
            index += 1;
        }
        return 0L;
    }
}
