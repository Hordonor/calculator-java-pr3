package ua.edu.dut.calculator;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    private Main() {
    }

    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        System.setErr(new PrintStream(System.err, true, StandardCharsets.UTF_8));
        Calculator calculator = new Calculator();
        try (Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8)) {
            System.out.println("Калькулятор: додавання, віднімання, множення та ділення.");
            double first = readNumber(scanner, "Введіть перше число: ");
            double second = readNumber(scanner, "Введіть друге число: ");
            System.out.print("Введіть операцію (+, -, *, /): ");
            String operation = scanner.nextLine().trim();

            double result = calculator.calculate(first, second, operation);
            System.out.printf(Locale.ROOT, "Результат: %s %s %s = %s%n",
                    formatNumber(first), operation, formatNumber(second), formatNumber(result));
        } catch (ArithmeticException exception) {
            System.out.println("Помилка обчислення: " + exception.getMessage());
        } catch (InvalidInputException exception) {
            System.out.println("Некоректні дані: " + exception.getMessage());
        } finally {
            System.out.println("Обробку запиту завершено.");
        }
    }

    static double readNumber(Scanner scanner, String prompt) throws InvalidInputException {
        System.out.print(prompt);
        return parseNumber(scanner.nextLine());
    }

    static double parseNumber(String input) throws InvalidInputException {
        if (input == null || input.isBlank()) {
            throw new InvalidInputException("Потрібно ввести число.");
        }
        try {
            double number = Double.parseDouble(input.trim());
            if (!Double.isFinite(number)) {
                throw new InvalidInputException("Введіть скінченне число.");
            }
            return number;
        } catch (NumberFormatException exception) {
            throw new InvalidInputException("«" + input + "» не є числом.", exception);
        }
    }

    private static String formatNumber(double number) {
        return String.format(Locale.ROOT, "%s", number);
    }
}
