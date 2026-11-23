package ua.edu.dut.calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculatorTest {
    private final Calculator calculator = new Calculator();

    @Test
    void performsBasicArithmeticOperations() throws InvalidInputException {
        assertEquals(7.0, calculator.add(4.0, 3.0));
        assertEquals(1.0, calculator.subtract(4.0, 3.0));
        assertEquals(12.0, calculator.multiply(4.0, 3.0));
        assertEquals(2.0, calculator.divide(6.0, 3.0));
    }

    @Test
    void throwsArithmeticExceptionWhenDividingByZero() {
        ArithmeticException exception = assertThrows(ArithmeticException.class,
                () -> calculator.divide(5.0, 0.0));
        assertEquals("Ділити на нуль не можна.", exception.getMessage());
    }

    @Test
    void rejectsNonFiniteInput() {
        assertThrows(InvalidInputException.class, () -> calculator.add(Double.NaN, 1.0));
        assertThrows(InvalidInputException.class, () -> calculator.subtract(1.0, Double.POSITIVE_INFINITY));
        assertThrows(InvalidInputException.class, () -> calculator.multiply(Double.NEGATIVE_INFINITY, 2.0));
        assertThrows(InvalidInputException.class, () -> calculator.divide(1.0, Double.NaN));
    }

    @Test
    void squareRootRejectsNegativeInputAndCalculatesNonNegativeInput() throws InvalidInputException {
        assertEquals(4.0, calculator.squareRoot(16.0));
        assertThrows(InvalidInputException.class, () -> calculator.squareRoot(-1.0));
    }

    @Test
    void rejectsUnknownOperation() {
        assertThrows(InvalidInputException.class, () -> calculator.calculate(1.0, 2.0, "%"));
    }
}
