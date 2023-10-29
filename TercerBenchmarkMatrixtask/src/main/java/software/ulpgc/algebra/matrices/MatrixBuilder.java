package software.ulpgc.algebra.matrices;

public interface MatrixBuilder<T> {
    void set(int i, int j, T value);

    Matrix<T> get();
}

