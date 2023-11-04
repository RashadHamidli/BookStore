package com.company.service;

import com.company.dao.StudentRepository;
import com.company.dto.StudentDTO;
import com.company.entity.Student;
import org.apache.catalina.UserDatabase;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentDTO creatOneStudent(StudentDTO newStudentDTO) {

        Student student = new Student();
        student.setId(newStudentDTO.getId());
        student.setName(newStudentDTO.getName());
        student.setEmail(newStudentDTO.getEmail());
        student.setAge(newStudentDTO.getAge());
        student.setPassword(newStudentDTO.getPassword());

        Student saveStudent = studentRepository.save(student);

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(saveStudent.getId());
        studentDTO.setName(saveStudent.getName());
        studentDTO.setEmail(saveStudent.getEmail());
        studentDTO.setAge(saveStudent.getAge());
        studentDTO.setPassword(saveStudent.getPassword());
        return studentDTO;
    }

    public List<StudentDTO> getAllStudent() {
        List<StudentDTO> list = new ArrayList<>();
        for (Student student : studentRepository.findAll()) {
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setId(student.getId());
            studentDTO.setName(student.getName());
            studentDTO.setEmail(student.getEmail());
            studentDTO.setAge(student.getAge());
            studentDTO.setPassword(student.getPassword());
            list.add(studentDTO);
        }
        return list;
    }

    public StudentDTO getOneStudent(Long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setId(student.getId());
            studentDTO.setName(student.getName());
            studentDTO.setEmail(student.getEmail());
            studentDTO.setAge(student.getAge());
            studentDTO.setPassword(student.getPassword());
            return studentDTO;
        }
        return null;
    }

//    public UserDTO createUser(UserDTO userDTO) {
//        User user = convertToEntity(userDTO);
//        User savedUser = userRepository.save(user);
//        return convertToDTO(savedUser);
//    }
//
//    private UserDTO convertToDTO(User user) {
//        UserDTO userDTO = new UserDTO();
//        userDTO.setId(user.getId());
//        userDTO.setName(user.getName());
//        userDTO.setEmail(user.getEmail());
//        return userDTO;
//    }
//
//    private User convertToEntity(UserDTO userDTO) {
//        User user = new User();
//        user.setName(userDTO.getName());
//        user.setEmail(userDTO.getEmail());
//        // Diğer özelliklerin atanması
//        return user;
//    }

}
