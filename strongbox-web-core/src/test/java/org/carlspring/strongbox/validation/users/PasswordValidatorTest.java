package org.carlspring.strongbox.validation.users;


import org.carlspring.strongbox.validation.users.support.PasswordAnnotationTestClass;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PasswordValidatorTest
{

    protected final Logger logger = LoggerFactory.getLogger(getClass().getName());

    private static Validator validator;

    @Before
    public void setUp()
    {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testNewUserValidPassword()
    {
        PasswordAnnotationTestClass testClass = new PasswordAnnotationTestClass();
        testClass.setPassword("12345678");
        Set<ConstraintViolation<PasswordAnnotationTestClass>> violations = validator.validate(testClass,
                                                                                              PasswordAnnotationTestClass.NewUser.class);

        violations.forEach(v -> logger.debug("Violation: {}", v.getMessage()));

        assertTrue(violations.isEmpty());
    }

    @Test
    public void testNewUserInvalidMinLengthPassword()
    {
        PasswordAnnotationTestClass testClass = new PasswordAnnotationTestClass();
        testClass.setPassword("1234567");
        Set<ConstraintViolation<PasswordAnnotationTestClass>> violations = validator.validate(testClass,
                                                                                              PasswordAnnotationTestClass.NewUser.class);

        violations.forEach(v -> logger.debug("Violation: {}", v.getMessage()));

        assertFalse(violations.isEmpty());
    }

    @Test
    public void testNewUserInvalidMaxLengthPassword()
    {
        PasswordAnnotationTestClass testClass = new PasswordAnnotationTestClass();
        testClass.setPassword("1234567891011");
        Set<ConstraintViolation<PasswordAnnotationTestClass>> violations = validator.validate(testClass,
                                                                                              PasswordAnnotationTestClass.NewUser.class);

        violations.forEach(v -> logger.debug("Violation: {}", v.getMessage()));

        assertFalse(violations.isEmpty());
    }

    @Test
    public void testNewUserInvalidNullPassword()
    {
        PasswordAnnotationTestClass testClass = new PasswordAnnotationTestClass();
        testClass.setPassword(null);
        Set<ConstraintViolation<PasswordAnnotationTestClass>> violations = validator.validate(testClass,
                                                                                              PasswordAnnotationTestClass.NewUser.class);


        violations.forEach(v -> logger.debug("Violation: {}", v.getMessage()));

        assertFalse(violations.isEmpty());
    }

    @Test
    public void testNewUserInvalidBlankPassword()
    {
        PasswordAnnotationTestClass testClass = new PasswordAnnotationTestClass();
        testClass.setPassword("        ");
        Set<ConstraintViolation<PasswordAnnotationTestClass>> violations = validator.validate(testClass,
                                                                                              PasswordAnnotationTestClass.NewUser.class);


        violations.forEach(v -> logger.debug("Violation: {}", v.getMessage()));

        assertFalse(violations.isEmpty());
    }

    @Test
    public void testUpdateUserNullPassword()
    {
        PasswordAnnotationTestClass testClass = new PasswordAnnotationTestClass();
        testClass.setPassword(null);
        Set<ConstraintViolation<PasswordAnnotationTestClass>> violations = validator.validate(testClass, PasswordAnnotationTestClass.ExistingUser.class);

        violations.forEach(v -> logger.debug("Violation: {}", v.getMessage()));

        assertTrue(violations.isEmpty());
    }


    @Test
    public void testUpdateUserInvalidMinLengthPassword()
    {
        PasswordAnnotationTestClass testClass = new PasswordAnnotationTestClass();
        testClass.setPassword("1234567");
        Set<ConstraintViolation<PasswordAnnotationTestClass>> violations = validator.validate(testClass,
                                                                                              PasswordAnnotationTestClass.ExistingUser.class);

        violations.forEach(v -> logger.debug("Violation: {}", v.getMessage()));

        assertFalse(violations.isEmpty());
    }

    @Test
    public void testUpdateUserInvalidMaxLengthPassword()
    {
        PasswordAnnotationTestClass testClass = new PasswordAnnotationTestClass();
        testClass.setPassword("1234567891011");
        Set<ConstraintViolation<PasswordAnnotationTestClass>> violations = validator.validate(testClass,
                                                                                              PasswordAnnotationTestClass.ExistingUser.class);

        violations.forEach(v -> logger.debug("Violation: {}", v.getMessage()));

        assertFalse(violations.isEmpty());
    }
}
