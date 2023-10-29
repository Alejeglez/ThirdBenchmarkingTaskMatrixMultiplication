package software.ulpgc.algebra.matrices.matrixSerializer;

import com.sun.tools.javac.Main;
import software.ulpgc.algebra.matrices.Matrix;
import software.ulpgc.algebra.matrices.MatrixSerializer;
import software.ulpgc.algebra.matrices.matrix.coordinateMatrix.CoordinateMatrixLong;
import software.ulpgc.algebra.matrices.matrix.coordinates.CoordinateLong;
import software.ulpgc.algebra.matrices.matrixBuilder.coordinateMatrixBuilder.CoordinateMatrixBuilderLong;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MatrixSerializerLong implements MatrixSerializer<Long> {

    @Override
    public Matrix<Long> deserialize(String file) throws FileNotFoundException {
        String resourcePath = MatrixSerializerLong.class.getResource(file).getPath();
        InputStream inputStream = new FileInputStream(resourcePath);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            CoordinateMatrixBuilderLong builder = new CoordinateMatrixBuilderLong(0, new ArrayList<>());
            boolean firstLine = true;
            String line;
            while ((line = reader.readLine()) != null) {
                String[] elements = line.split(" ");
                //element1 will be row, element2 will be column, element3 will be value.
                //for the first line we will have that element3 is the size.

                int element1 = Integer.parseInt(elements[0]);
                int element2 = Integer.parseInt(elements[1]);
                long element3 = Long.parseLong(elements[2]);
                if (!firstLine) {
                    builder.set(element1, element2, element3);
                } else {
                    List<CoordinateLong> coordinateInts = new ArrayList<>();
                    builder = new CoordinateMatrixBuilderLong(element1, coordinateInts);
                    firstLine = false;
                }
            }
            return builder.get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    @Override
    public void serialize(Matrix<Long> matrix) {
        if (matrix instanceof CoordinateMatrixLong coordinateMatrix) {
            String outputFilePath = "src/main/resources/matrix" + coordinateMatrix.size() + ".mtx";

            try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(outputFilePath))) {
                CoordinateLong lastCoordinate = coordinateMatrix.getCoordinates().get(coordinateMatrix.getCoordinates().size() - 1);

                // Write the additional line at the beginning
                String infoLine = lastCoordinate.i() + " " + lastCoordinate.j() + " " + coordinateMatrix.getCoordinates().size();

                writer.write(infoLine + System.lineSeparator());
                for (CoordinateLong coordinate : coordinateMatrix.getCoordinates()) {
                    // Format and write the coordinate to the file
                    String line = coordinate.i() + " " + coordinate.j() + " " + coordinate.value();
                    writer.write(line + System.lineSeparator());
                }
                System.out.println("¡Se ha completado la serialización!");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Should be a Coordinate Matrix!");
        }
    }
}

