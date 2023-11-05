package com.company.service;

import com.company.dao.StudentRepository;
import com.company.dto.BookDTO;
import com.company.dto.StudentDTO;
import com.company.entity.Book;
import com.company.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentDTO creatOneStudent(StudentDTO newStudentDTO) {
        Student student = convertToEntity(newStudentDTO);
        Student saveStudent = studentRepository.save(student);
        return convertToDto(saveStudent);
    }

    public List<StudentDTO> getAllStudent() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(student -> {
            StudentDTO studentDTO = convertToDto(student);
            List<BookDTO> bookDTOList = student.getBooksReading()
                    .stream()
                    .map(this::convertToBookDTO)
                    .collect(Collectors.toList());
            studentDTO.setBooksReading(bookDTOList);
            return studentDTO;
        }).collect(Collectors.toList());
    }


    public StudentDTO getOneStudent(Long id) {
        Optional<Student> foundedStudent = studentRepository.findById(id);
        if (foundedStudent.isPresent()) {
            Student student = foundedStudent.get();
            StudentDTO studentDTO= convertToDto(student);
            List<BookDTO> bookDTOList= convertToBookEntity(student.getBooksReading());
            studentDTO.setBooksReading(bookDTOList);
            return studentDTO;
        }
        return new StudentDTO();
    }

    public StudentDTO updateOneStudent(Long id, StudentDTO newStudentDTO) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student foundedStudent = optionalStudent.get();
            if (newStudentDTO.getName() != null)
                foundedStudent.setName(newStudentDTO.getName());
            if (newStudentDTO.getEmail() != null) ;
            foundedStudent.setEmail(newStudentDTO.getEmail());
            if (newStudentDTO.getAge() != null) ;
            foundedStudent.setAge(newStudentDTO.getAge());
            if (newStudentDTO.getPassword() != null) ;
            foundedStudent.setPassword(newStudentDTO.getPassword());

//            foundedStudent.setBooksReading(convertToBookList(newStudentDTO.getBooksReading()));

            foundedStudent.setId(id);
            Student updateStudent = studentRepository.save(foundedStudent);
            return convertToDto(updateStudent);
        }
        return new StudentDTO();
    }

    //    public StudentDTO updateOneStudent(Long id, StudentDTO newStundetDTO) {
//        Optional<Student> optionalStudent = studentRepository.findById(id);
//        if (optionalStudent.isPresent()) {
//            Student foundedStudent = optionalStudent.get();
//            Student student = convertToEntity(newStundetDTO);
//            try {
//                BeanUtils.copyProperties(foundedStudent, student);
//                foundedStudent.setId(id);
//                Student updateStudent = studentRepository.save(foundedStudent);
//                return convertToDto(updateStudent);
//            } catch (IllegalAccessError ex) {
//                ex.printStackTrace();
//            }
//        }
//        return new StudentDTO();
//    }
    public boolean deleteOneStudent(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private StudentDTO convertToDto(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setName(student.getName());
        studentDTO.setEmail(student.getEmail());
        studentDTO.setAge(student.getAge());
        studentDTO.setPassword(student.getPassword());
        return studentDTO;
    }

    private Student convertToEntity(StudentDTO studentDTO) {
        Student student = new Student();
        student.setId(studentDTO.getId());
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setAge(studentDTO.getAge());
        student.setPassword(student.getPassword());
        return student;
    }

    private List<BookDTO> convertToBookEntity(List<Book> books) {
        return books.stream()
                .map(this::convertToBookDTO)
                .collect(Collectors.toList());
    }

    private BookDTO convertToBookDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setName(book.getName());
        bookDTO.setAuthorId(book.getAuthor().getId());
        return bookDTO;
    }

}
