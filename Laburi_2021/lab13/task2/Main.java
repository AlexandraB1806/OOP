package lab13.task2;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // Initialize the calculator
        Calculator calculator = new DoubleCalculator();

        System.out.println(calculator.add(2d, 3d));
        System.out.println(calculator.divide(9d, 4d));
        System.out.println(calculator.average(List.of(1d, 2d, 3d, 4d)));

        // Edge cases that would throw exceptions
        try {
            System.out.println(calculator.add(null, 7d));
        }
        catch (Calculator.NullParameterException e) {
            System.out.println("NullParameterException found");
        }
        catch (Calculator.OverflowException e) {
            System.out.println("OverflowException found");
        }
        catch (Calculator.UnderflowException e) {
            System.out.println("UnderflowException found");
        }

        try {
            System.out.println(calculator.divide(5d, 0d));
        }
        catch (Calculator.OverflowException e) {
            System.out.println("OverflowException found");
        }
        catch (Calculator.NullParameterException e) {
            System.out.println("NullParameterException found");
        }
        catch (Calculator.UnderflowException e) {
            System.out.println("UnderflowException found");
        }

        try {
            System.out.println(calculator.average(Set.of()));
        }
        catch (Calculator.NotANumberException e) {
            System.out.println("NotANumberException found");
        }
        catch (Calculator.OverflowException e) {
            System.out.println("OverflowException found");
        }
        catch (Calculator.NullParameterException e) {
            System.out.println("NullParameterException found");
        }
        catch (Calculator.UnderflowException e) {
            System.out.println("UnderflowException found");
        }
    }
}
