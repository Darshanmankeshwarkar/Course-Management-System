package com.curdoperations.curd;

import com.curdoperations.curd.model.Course;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@SpringBootTest
public class ValidationTest {

    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidTitle() {
        Course course = new Course();
        course.setTitle("Mathematics");
        course.setDescription("This is a math course.");

        Set<ConstraintViolation<Course>> violations = validator.validate(course);
        Assert.assertTrue(violations.isEmpty());
    }

    @Test
    public void testTitleTooShort() {
        Course course = new Course();
        course.setTitle("A");
        course.setDescription("This is a course.");

        Set<ConstraintViolation<Course>> violations = validator.validate(course);
        Assert.assertFalse(violations.isEmpty());

        ConstraintViolation<Course> violation = violations.iterator().next();
        Assert.assertEquals("Name must be between 2 and 32 characters long", violation.getMessage());
    }

    @Test
    public void testTitleTooLong() {
        Course course = new Course();
        course.setTitle("ThisTitleIsWayTooLongAndExceedsTheMaximumAllowedLength");
        course.setDescription("This is a course.");

        Set<ConstraintViolation<Course>> violations = validator.validate(course);
        Assert.assertFalse(violations.isEmpty());

        ConstraintViolation<Course> violation = violations.iterator().next();
        Assert.assertEquals("Name must be between 2 and 32 characters long", violation.getMessage());
    }

    @Test
    public void testEmptyTitle() {
        Course course = new Course();
        course.setTitle("");
        course.setDescription("This is a course.");

        Set<ConstraintViolation<Course>> violations = validator.validate(course);
        Assert.assertFalse(violations.isEmpty());

        ConstraintViolation<Course> violation = violations.iterator().next();
        Assert.assertEquals("Name may not be empty", violation.getMessage());
    }

}
