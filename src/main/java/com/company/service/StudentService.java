package com.company.service;

import com.company.dao.StudentRepository;
import com.company.dto.StudentDTO;
import com.company.entity.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentDTO creatOneStudent(StudentDTO newStudentDTO) {

        Student student = new Student();
        student.setName(newStudentDTO.getName());
        student.setEmail(newStudentDTO.getEmail());
        student.setAge(newStudentDTO.getAge());
        student.setPassword(newStudentDTO.getPassword());

        Student saveStudent = studentRepository.save(student);

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName(saveStudent.getName());
        studentDTO.setEmail(saveStudent.getEmail());
        studentDTO.setAge(saveStudent.getAge());
        studentDTO.setPassword(saveStudent.getPassword());
        return studentDTO;
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
