package com.company.service;

import com.company.dao.StudentRepository;
import com.company.dto.BookDTO;
import com.company.dto.StudentDTO;
import com.company.entity.Book;
import com.company.entity.Student;
import com.company.mapper.StudentMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService2 {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentService2(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public StudentDTO creatStudent(StudentDTO newStudentDTO) {
        Student student=studentMapper.studentDTOtoStudent(newStudentDTO);
        Student saveStudent = studentRepository.save(student);
        return studentMapper.studentToStudentDTO(saveStudent);
    }

    public List<StudentDTO> getAllStudent() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(student -> {
            StudentDTO studentDTO = studentMapper.studentToStudentDTO(student);
            List<Book> booksReading = student.getBooksReading().stream().map(book -> {

            })
            studentDTO.setBooksReading(booksReading);
        }).collect(Collectors.toList());
//        List<Student> students = studentRepository.findAll();
//        return students.stream().map(student -> {
//            StudentDTO studentDTO = convertToDto(student);
//            List<BookDTO> bookDTOList = student.getBooksReading()
//                    .stream()
//                    .map(this::convertToBookDTO)
//                    .collect(Collectors.toList());
//            studentDTO.setBooksReading(bookDTOList);
//            return studentDTO;
//        }).collect(Collectors.toList());
    }

    public StudentDTO getStudent(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student foundedStudent = optionalStudent.get();
            StudentDTO studentDTO = convertToDto(foundedStudent);
            List<Book> booksReading = foundedStudent.getBooksReading();
            List<BookDTO> bookDTOS = booksReading.stream().map(this::convertToBookDTO).collect(Collectors.toList());
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
                List<Book> books = bookDTOList.stream().map(this::convertToBookEntity).toList();
                foundedStudent.setBooksReading(books);
            }
            foundedStudent.setId(id);
            Student updateStudent = studentRepository.save(foundedStudent);
            return convertToDto(updateStudent);
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

//    private StudentDTO convertToDto(Student student) {
//        StudentDTO studentDTO = new StudentDTO();
//        studentDTO.setId(student.getId());
//        studentDTO.setName(student.getName());
//        studentDTO.setEmail(student.getEmail());
//        studentDTO.setAge(student.getAge());
//        studentDTO.setPassword(student.getPassword());
//        return studentDTO;
//    }
//
//    private Student convertToEntity(StudentDTO studentDTO) {
//        Student student = new Student();
//        student.setId(studentDTO.getId());
//        student.setName(studentDTO.getName());
//        student.setEmail(studentDTO.getEmail());
//        student.setAge(studentDTO.getAge());
//        student.setPassword(student.getPassword());
//        return student;
//    }
//
//    private BookDTO convertToBookDTO(Book book) {
//        BookDTO bookDTO = new BookDTO();
//        bookDTO.setId(book.getId());
//        bookDTO.setName(book.getName());
//        bookDTO.setAuthorId(book.getAuthor().getId());
//        return bookDTO;
//    }
//
//    private Book convertToBookEntity(BookDTO bookDTO) {
//        Book book = new Book();
//        book.setId(bookDTO.getId());
//        book.setName(bookDTO.getName());
//        Author author = new Author();
//        author.setId(bookDTO.getAuthorId());
//        book.setAuthor(author);
//        return book;
//    }

}
