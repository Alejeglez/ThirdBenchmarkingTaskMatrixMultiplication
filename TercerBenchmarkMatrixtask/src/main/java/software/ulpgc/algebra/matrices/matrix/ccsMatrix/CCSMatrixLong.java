package software.ulpgc.algebra.matrices.matrix.ccsMatrix;

import software.ulpgc.algebra.matrices.matrix.sparseMatrix.SparseMatrixLong;

import java.util.Collections;
import java.util.List;

public class CCSMatrixLong extends SparseMatrixLong
{
    public List<Integer> columnPointers;
    public  List<Integer> rows;
    public  List<Long> values;

    public CCSMatrixLong(List<Integer> columnPointers, List<Integer> rows, List<Long> values) {
        this.columnPointers = columnPointers;
        this.rows = rows;
        this.values = values;
    }

    @Override
    public int size() {
        // Calculate the maximum column index
        int maxColumnIndex = rows.isEmpty() ? -1 : Collections.max(rows);

        // Get the size of the rowPointers list
        int rowPointersSize = columnPointers.size();

        // Return the maximum value between maxColumnIndex and rowPointersSize
        return rowPointersSize;
    }

    @Override
    public Long get(int i, int j) {
        int index = 0;
        int indexPointer = 0;
        int appearence = 0;
        while (index < rows.size()){
            if (appearence >= columnPointers.get(indexPointer+1)){
                indexPointer += 1;
            }
            if(rows.get(index) == i && j == indexPointer){
                return(values.get(index));
            }

            appearence += 1;
            index += 1;
        }
        return 0L;
    }
}
