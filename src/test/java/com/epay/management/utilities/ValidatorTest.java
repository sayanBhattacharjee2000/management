package com.epay.management.utilities;

import org.junit.jupiter.api.*;

class ValidatorTest {

    Validator validator = new Validator();

    @BeforeAll
    static void setupBeforeAll(){
        System.out.println("before all condition");
    }

    @BeforeEach
    void setup(){
        System.out.println("before each condition");
    }

    @Test
    @Order(1)
    @DisplayName("Valid Email: Verify validator method should return true")
    void testIsValidEmailWhenEmailIsValid() {
        String email = "subhajit@gmail.com";
        boolean isValid = validator.isValidEmail(email);
        Assertions.assertTrue(isValid);
    }

    @Test
    @Order(2)
    @DisplayName("Invalid Email: Verify validator method should return false")
    void testIsValidEmailWhenEmailIsInvalid(){
        String email = "subhajit@gmail";
        boolean isValid = validator.isValidEmail(email);
        Assertions.assertFalse(isValid);
    }

    @AfterEach
    void afterSteps(){
        System.out.println("after step config");
    }

    @AfterAll
    static void afterAllStep(){
        System.out.println("after all config");
    }
}