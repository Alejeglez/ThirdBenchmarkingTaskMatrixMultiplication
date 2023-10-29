package software.ulpgc.algebra.matrices;

public interface Matrix<T> {
    int size();
    T get(int i, int j);
}

