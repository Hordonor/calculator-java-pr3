package ua.edu.dut.calculator;

public class Calculator {
    public double add(double first, double second) throws InvalidInputException {
        validate(first, second);
        return first + second;
    }

    public double subtract(double first, double second) throws InvalidInputException {
        validate(first, second);
        return first - second;
    }

    public double multiply(double first, double second) throws InvalidInputException {
        validate(first, second);
        return first * second;
    }

    public double divide(double first, double second) throws InvalidInputException {
        validate(first, second);
        if (second == 0.0) {
            throw new ArithmeticException("Ділити на нуль не можна.");
        }
        return first / second;
    }

    /** Additional operation to demonstrate validation of a non-negative argument. */
    public double squareRoot(double number) throws InvalidInputException {
        validate(number);
        if (number < 0.0) {
            throw new InvalidInputException("Підкореневе число має бути невід’ємним.");
        }
        return Math.sqrt(number);
    }

    public double calculate(double first, double second, String operation) throws InvalidInputException {
        if (operation == null) {
            throw new InvalidInputException("Операція не може бути порожньою.");
        }
        return switch (operation.trim()) {
            case "+" -> add(first, second);
            case "-" -> subtract(first, second);
            case "*" -> multiply(first, second);
            case "/" -> divide(first, second);
            default -> throw new InvalidInputException("Невідома операція. Використайте +, -, * або /.");
        };
    }

    private static void validate(double... values) throws InvalidInputException {
        for (double value : values) {
            if (!Double.isFinite(value)) {
                throw new InvalidInputException("Числа мають бути скінченними значеннями.");
            }
        }
    }
}
