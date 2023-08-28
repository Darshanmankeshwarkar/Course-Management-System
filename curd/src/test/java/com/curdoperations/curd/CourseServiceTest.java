package com.curdoperations.curd;

import com.curdoperations.curd.model.Course;
import com.curdoperations.curd.repository.CourseRepository;
import com.curdoperations.curd.service.CourseService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseService courseService;

    @Test
    public void testGetAllCourses() {

        MockitoAnnotations.initMocks(this);


        List<Course> mockCourses = new ArrayList<>();
        mockCourses.add(new Course(1L, "Course 1","ah"));
        mockCourses.add(new Course(2L, "Course 2", "gf" ));


        when(courseRepository.findAll()).thenReturn(mockCourses);


        List<Course> resultCourses = courseService.getAllCourses();


        verify(courseRepository, times(1)).findAll(); // Ensure that the repository method was called exactly once


        assertEquals(2, resultCourses.size()); // Verify the size of the returned course list
        assertEquals("Course 1", resultCourses.get(0).getTitle()); // Verify course names or other attributes
        assertEquals("Course 2", resultCourses.get(1).getTitle());
    }

    @Test
    public void testGetCourseById() {
        MockitoAnnotations.initMocks(this);


        Course mockCourse = new Course(1L, "Mock Course","dfgj");


        when(courseRepository.findById(1L)).thenReturn(Optional.of(mockCourse));
        when(courseRepository.findById(2L)).thenReturn(Optional.empty());


        Course resultCourseExisting = courseService.getCourseById(1L);


        verify(courseRepository, times(1)).findById(1L); // Ensure that the repository method was called exactly once
        assertNotNull(resultCourseExisting); // Verify that a course is returned
        assertEquals("Mock Course", resultCourseExisting.getTitle()); // Verify course name or other attributes


        Course resultCourseNonExisting = courseService.getCourseById(2L);


        verify(courseRepository, times(1)).findById(2L); // Ensure that the repository method was called exactly once
        assertNull(resultCourseNonExisting);
    }

    @Test
    public void testCreateCourse() {
        MockitoAnnotations.initMocks(this);


        Course mockCourseToSave = new Course(null, "New Course","aksgd");
        Course mockSavedCourse = new Course(1L, "New Course","sajf");


        when(courseRepository.save(mockCourseToSave)).thenReturn(mockSavedCourse);


        Course resultCourse = courseService.createCourse(mockCourseToSave);


        verify(courseRepository, times(1)).save(mockCourseToSave); // Ensure that the repository method was called exactly once
        assertNotNull(resultCourse); // Verify that a course is returned
        assertEquals(1L, resultCourse.getId()); // Verify the ID of the returned course
        assertEquals("New Course", resultCourse.getTitle());
    }

    @Test
    public void testUpdateCourse() {
        MockitoAnnotations.initMocks(this);


        Course existingCourse = new Course(1L, "Existing Course", "sah");
        Course updatedCourse = new Course(1L, "Updated Course","");


        when(courseRepository.existsById(1L)).thenReturn(true);
        when(courseRepository.save(updatedCourse)).thenReturn(updatedCourse);


        Course resultCourse = courseService.updateCourse(1L, updatedCourse);


        verify(courseRepository, times(1)).existsById(1L); // Ensure that existsById was called
        verify(courseRepository, times(1)).save(updatedCourse); // Ensure that save was called
        assertNotNull(resultCourse); // Verify that a course is returned
        assertEquals(1L, resultCourse.getId()); // Verify the ID of the returned course
        assertEquals("Updated Course", resultCourse.getTitle());
    }



    @Test
    public void testDeleteCourse() {
        MockitoAnnotations.initMocks(this);


        when(courseRepository.existsById(1L)).thenReturn(true);


        boolean result = courseService.deleteCourse(1L);


        verify(courseRepository, times(1)).existsById(1L); // Ensure that existsById was called
        verify(courseRepository, times(1)).deleteById(1L); // Ensure that deleteById was called
        assertTrue(result);
    }
}
