package com.company.service;


import com.company.dao.StudentRepository;
import com.company.dto.BookDTO;
import com.company.dto.StudentDTO;
import com.company.entity.Book;
import com.company.entity.Student;
import com.company.mapper.BookMapper;
import com.company.mapper.StudentMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final BookMapper bookMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper, BookMapper bookMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.bookMapper = bookMapper;
    }

    public StudentDTO creatStudent(StudentDTO newStudentDTO) {
        Student student = studentMapper.studentConvertToStudent(newStudentDTO);
        Student saveStudent = studentRepository.save(student);
        return studentMapper.studentConvertToStudentDto(saveStudent);
    }

    public List<StudentDTO> getAllStudent() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(student -> {
            StudentDTO studentDTO = studentMapper.studentConvertToStudentDto(student);
            List<Book> booksReading = student.getBooksReading();
            List<BookDTO> bookDTOS = booksReading.stream().map(bookMapper::bookConvertToBookDTO).collect(Collectors.toList());
            studentDTO.setBooksReading(bookDTOS);
            return studentDTO;
        }).collect(Collectors.toList());
    }

    public StudentDTO getStudent(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student foundedStudent = optionalStudent.get();
            StudentDTO studentDTO = studentMapper.studentConvertToStudentDto(foundedStudent);
            List<Book> booksReading = foundedStudent.getBooksReading();
            List<BookDTO> bookDTOS = booksReading.stream().map(bookMapper::bookConvertToBookDTO).collect(Collectors.toList());
            studentDTO.setBooksReading(bookDTOS);
            return studentDTO;
        }
        return new StudentDTO();
    }

    public StudentDTO updateStudent(Long id, StudentDTO newStudentDTO) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student foundedStudent = optionalStudent.get();
            if (newStudentDTO.getName() != null && !newStudentDTO.getName().isEmpty())
                foundedStudent.setName(newStudentDTO.getName());
            if (newStudentDTO.getEmail() != null && !newStudentDTO.getEmail().isEmpty())
                foundedStudent.setEmail(newStudentDTO.getEmail());
            if (newStudentDTO.getAge() != null)
                foundedStudent.setAge(newStudentDTO.getAge());
            if (newStudentDTO.getPassword() != null && !newStudentDTO.getPassword().isEmpty())
                foundedStudent.setPassword(newStudentDTO.getPassword());
            if (newStudentDTO.getBooksReading() != null && !newStudentDTO.getBooksReading().isEmpty()) {
                List<BookDTO> bookDTOList = newStudentDTO.getBooksReading();
                List<Book> books = bookDTOList.stream().map(bookMapper::BookDTOConvertToBook).toList();
                foundedStudent.setBooksReading(books);
            }
            foundedStudent.setId(id);
            Student updateStudent = studentRepository.save(foundedStudent);
            return studentMapper.studentConvertToStudentDto(updateStudent);
        }
        return new StudentDTO();
    }

    public boolean deleteStudent(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public StudentDTO login(StudentDTO studentDTO) {
        Student student = studentMapper.studentConvertToStudent(studentDTO);
        Student foundStudent = studentRepository.findByEmail(student.getEmail());
        if (foundStudent != null && foundStudent.getPassword().equals(student.getPassword())) {
            return studentDTO; // Burada gerçek JWT üretme işlemi yapılmalıdır
        } else {
            throw new RuntimeException("Invalid email or password.");
        }
    }

    public List<BookDTO> getStudentReadingList(Long studentId) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent()) {
            optionalStudent.stream().map(student -> {
                        StudentDTO studentDTO = studentMapper.studentConvertToStudentDto(student);
                        return studentDTO.getBooksReading();
                    }
            ).collect(Collectors.toList());
        }
        return null;
    }
}
