package crudtest.services;
import crudtest.entities.Student;
import crudtest.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student createStudent(Student student) {
        Student savedStudent = studentRepository.save(student);
        return savedStudent;
    }

    public List<Student> getAllStudents() {
        List<Student> students = (List<Student>) studentRepository.findAll();
        return students;
    }

    public Optional<Student> getStudent(Long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        return studentOptional;
    }

    public Optional<Student> updateStudente(Long id,Student student){
        Optional<Student> studenteDaAggiornare = studentRepository.findById(id);
        if (studenteDaAggiornare.isPresent()){
            studenteDaAggiornare.get().setName(student.getName());
            studenteDaAggiornare.get().setSurname(student.getSurname());
            studentRepository.save(studenteDaAggiornare.get());
        } else {
            return Optional.empty();
        }
        return studenteDaAggiornare;
    }


    public Student deleteStudent(Long id){
        studentRepository.deleteById(id);
        return null;
    }


    public Optional<Student> updateIsWorking(Long id, boolean working) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent()) {
            studentOptional.get().setWorking(working);
            studentRepository.save(studentOptional.get());
        }
        return studentOptional;
    }

}