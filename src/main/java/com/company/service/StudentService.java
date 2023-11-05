package com.company.service;

import com.company.dao.StudentRepository;
import com.company.dto.StudentDTO;
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
        StudentDTO studentDTO = convertToDto(saveStudent);
        return studentDTO;
    }

    public List<StudentDTO> getAllStudent() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public StudentDTO getOneStudent(Long id) {
        Optional<Student> foundedStudent = studentRepository.findById(id);
        StudentDTO studentDTO = new StudentDTO();
        if (foundedStudent.isPresent()) {
            Student student = foundedStudent.get();
            return convertToDto(student);
        }
        return new StudentDTO();
    }

    public StudentDTO updateOneStudent(Long id, StudentDTO newStundetDTO) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student foundedStudent = optionalStudent.get();
            if (newStundetDTO.getName() != null)
                foundedStudent.setName(newStundetDTO.getName());
            if (newStundetDTO.getEmail() != null) ;
            foundedStudent.setEmail(newStundetDTO.getEmail());
            if (newStundetDTO.getAge() != null) ;
            foundedStudent.setAge(newStundetDTO.getAge());
            if (newStundetDTO.getPassword() != null) ;
            foundedStudent.setPassword(newStundetDTO.getPassword());
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


}
