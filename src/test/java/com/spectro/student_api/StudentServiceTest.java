package com.spectro.student_api;

import com.spectro.student_api.dto.StudentDto;
import com.spectro.student_api.entity.Student;
import com.spectro.student_api.exception.StudentNotFoundException;
import com.spectro.student_api.repository.StudentRepository;
import com.spectro.student_api.service.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    private Student student;
    private StudentDto studentDto;

    @BeforeEach
    void setUp() {
        student = Student.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@email.com")
                .dateOfBirth(LocalDate.of(2000, 1, 15))
                .build();

        studentDto = StudentDto.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@email.com")
                .dateOfBirth(LocalDate.of(2000, 1, 15))
                .build();
    }

    @Test
    void testCreateStudent() {
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        StudentDto result = studentService.createStudent(studentDto);

        assertNotNull(result);
        assertEquals(studentDto.getFirstName(), result.getFirstName());
        assertEquals(studentDto.getLastName(), result.getLastName());
        assertEquals(studentDto.getEmail(), result.getEmail());
        assertEquals(studentDto.getDateOfBirth(), result.getDateOfBirth());

        verify(studentRepository, times(1)).save(any(Student.class));
    }

    @Test
    void testGetAllStudents() {
        List<Student> students = Arrays.asList(student);
        when(studentRepository.findAll()).thenReturn(students);

        List<StudentDto> result = studentService.getAllStudents();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(student.getFirstName(), result.get(0).getFirstName());

        verify(studentRepository, times(1)).findAll();
    }

    @Test
    void testGetStudentById() {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));

        StudentDto result = studentService.getStudentById(1L);

        assertNotNull(result);
        assertEquals(student.getFirstName(), result.getFirstName());

        verify(studentRepository, times(1)).findById(1L);
    }

    @Test
    void testGetStudentById_NotFound() {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(StudentNotFoundException.class, () ->
                studentService.getStudentById(1L));

        verify(studentRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateStudent() {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        StudentDto updatedDto = StudentDto.builder()
                .firstName("Jane")
                .lastName("Smith")
                .email("jane.smith@email.com")
                .dateOfBirth(LocalDate.of(1999, 5, 20))
                .build();

        StudentDto result = studentService.updateStudent(1L, updatedDto);

        assertNotNull(result);
        verify(studentRepository, times(1)).findById(1L);
        verify(studentRepository, times(1)).save(any(Student.class));
    }

    @Test
    void testDeleteStudent() {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));
        doNothing().when(studentRepository).delete(any(Student.class));

        assertDoesNotThrow(() -> studentService.deleteStudent(1L));

        verify(studentRepository, times(1)).findById(1L);
        verify(studentRepository, times(1)).delete(student);
    }

    @Test
    void testDeleteStudent_NotFound() {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(StudentNotFoundException.class, () ->
                studentService.deleteStudent(1L));

        verify(studentRepository, times(1)).findById(1L);
        verify(studentRepository, never()).delete(any(Student.class));
    }
}