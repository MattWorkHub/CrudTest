package crudtest.controllers;
import crudtest.entities.Student;
import crudtest.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/addstudente")
    public ResponseEntity<Student> addStudent(@RequestBody Student student){
        studentService.createStudent(student);
        return ResponseEntity.ok().body(student);
    }

    @GetMapping("/getlist")
    public ResponseEntity<List<Student>> getAllStudent(){
        List<Student> allStudents =   studentService.getAllStudents();
        return ResponseEntity.ok().body(allStudents);
    }

    @GetMapping("/getstudent/{id}")
    public ResponseEntity<Optional<Student>> getStudent(@PathVariable Long id){
        Optional<Student> studentOptional = studentService.getStudent(id);
        return ResponseEntity.ok().body(studentOptional);
    }

    @PutMapping("/updatestudent/{id}")
    public ResponseEntity<Student> updateStudentById(@RequestBody Student student,@PathVariable Long id){
        Optional<Student> studentOptional = studentService.updateStudente(id,student);
        if(studentOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(student);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Student> deleteStudent(@RequestParam Long id){
        Student studentDaCancellare = studentService.deleteStudent(id);
        return ResponseEntity.ok().body(studentDaCancellare);
    }

    @PatchMapping("/setstato/{id}")
    public ResponseEntity<Optional<Student>> updateIsWorking(@PathVariable Long id, @RequestParam boolean working) {
        Optional<Student> studentDaModificare = studentService.updateIsWorking(id,working);
        return ResponseEntity.ok().body(studentDaModificare);
    }
}