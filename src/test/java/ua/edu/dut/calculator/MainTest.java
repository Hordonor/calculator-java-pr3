package ua.edu.dut.calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MainTest {
    @Test
    void parsesNumericInput() throws InvalidInputException {
        assertEquals(12.5, Main.parseNumber(" 12.5 "));
    }

    @Test
    void convertsTextInputToInvalidInputException() {
        InvalidInputException exception = assertThrows(InvalidInputException.class,
                () -> Main.parseNumber("дванадцять"));
        assertEquals("«дванадцять» не є числом.", exception.getMessage());
    }

    @Test
    void rejectsBlankAndNonFiniteInput() {
        assertThrows(InvalidInputException.class, () -> Main.parseNumber(" "));
        assertThrows(InvalidInputException.class, () -> Main.parseNumber("NaN"));
        assertThrows(InvalidInputException.class, () -> Main.parseNumber("Infinity"));
    }
}
