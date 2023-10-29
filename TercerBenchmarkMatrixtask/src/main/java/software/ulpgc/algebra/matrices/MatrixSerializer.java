package software.ulpgc.algebra.matrices;

import java.io.FileNotFoundException;
import java.io.InputStream;

public interface MatrixSerializer<T> {
    Matrix<T> deserialize(String file) throws FileNotFoundException;
    void serialize(Matrix<T> matrix);
}
