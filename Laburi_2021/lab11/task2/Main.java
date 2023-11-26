package lab11.task2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static lab11.task2.Summable.sumOfElements;

public class Main {

    public static void main(final String[] args) {
        MyVector3 vector1 = new MyVector3(1, 2, 3);
        MyVector3 vector2 = new MyVector3(4, 5, 6);
        vector1.addValue(vector2);
        System.out.println(vector1);

        // The lines from the first matrix
        ArrayList<Integer> line11 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        ArrayList<Integer> line12 = new ArrayList<>(Arrays.asList(5, 6, 7, 8));
        ArrayList<Integer> line13 = new ArrayList<>(Arrays.asList(9, 10, 11, 12));
        ArrayList<Integer> line14 = new ArrayList<>(Arrays.asList(13, 14, 15, 16));
        ArrayList<ArrayList<Integer>> matrix1 = new ArrayList<>(Arrays.asList(line11, line12, line13, line14));

        MyMatrix myMatrix1 = new MyMatrix(matrix1);

        // The lines from the second matrix
        ArrayList<Integer> line21 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        ArrayList<Integer> line22 = new ArrayList<>(Arrays.asList(5, 6, 7, 8));
        ArrayList<Integer> line23 = new ArrayList<>(Arrays.asList(9, 10, 11, 12));
        ArrayList<Integer> line24 = new ArrayList<>(Arrays.asList(13, 14, 15, 16));
        ArrayList<ArrayList<Integer>> matrix2 = new ArrayList<>(Arrays.asList(line21, line22, line23, line24));

        MyMatrix myMatrix2 = new MyMatrix(matrix2);

        myMatrix2.addValue(myMatrix1);
        System.out.println(myMatrix2);

        System.out.println("Task 2.2 - Metoda generica:");
        Collection<MyMatrix> matrixCollection = new ArrayList<>();
        matrixCollection.add(myMatrix1);
        matrixCollection.add(myMatrix2);
        System.out.println(sumOfElements(matrixCollection));

        MyVector3 vector3 = new MyVector3(10, 20, 30);
        MyVector3 vector4 = new MyVector3(40, 50, 60);
        Collection<MyVector3> vectorCollection = new ArrayList<>();
        vectorCollection.add(vector3);
        vectorCollection.add(vector4);
        System.out.println(sumOfElements(vectorCollection));
    }
}
