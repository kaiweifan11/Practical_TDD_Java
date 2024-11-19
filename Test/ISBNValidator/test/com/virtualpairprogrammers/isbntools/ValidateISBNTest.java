package com.virtualpairprogrammers.isbntools;

import org.junit.Test;

import static org.junit.Assert.*;

// RULES OF TESTING

// TEST 1 ITEM OF FUNCTIONALITY ONLY
// Each test should only have 1 assert, unless
// 1) There are testing 2 or more examples of the exactly the same thing
// 2) Need to validate more than 1 value in order to know the test has worked

// TEST BUSINESS LOGIC, NOT METHODS
// do not write tests for methods, test for the business logic

// TESTS MUST BE REPEATABLE AND CONSISTENT

// TESTS MUST BE THOROUGH

public class ValidateISBNTest {

    @Test
    public void checkValid10DigitISBN() {
        // always start with a failing test
        // fail("not yet implemented");
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("0140449116");
        assertTrue("first value",  result);
        result = validator.checkISBN("0140177396");
        assertTrue("second value", result);
    }

    @Test
    public void checkValid13DigitISBN() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("9781853260087");
        assertTrue("first value",  result);
        result = validator.checkISBN("9781853267338");
        assertTrue("second value",  result);
    }

    @Test
    public void TenDigitISBNNumbersEndingInAnXAreValid() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("012000030X");
        assertTrue(result);
    }

    @Test
    public void checkInvalid10DigitISBN() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("0140449117");
        assertFalse(result);
    }

    @Test
    public void checkInvalid13DigitISBN() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("9781853267336");
        assertFalse(result);
    }

    @Test(expected = NumberFormatException.class)
    public void nineDigitISBNAreNotAllowed() {
        ValidateISBN validator = new ValidateISBN();
        // Junit 5
        // assertThrows(NumberFormatException.class,
        //      () => {
        //         validator.checkISBN("123456789");
        //       }
        // );
        validator.checkISBN("123456789");
    }

    @Test(expected = NumberFormatException.class)
    public void nonNumericISBNAreNotAllowed() {
        ValidateISBN validator = new ValidateISBN();
        validator.checkISBN("helloworld");
    }
}
