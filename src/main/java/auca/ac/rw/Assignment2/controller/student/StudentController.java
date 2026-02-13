package auca.ac.rw.Assignment2.controller.student;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import auca.ac.rw.Assignment2.model.student.Student;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private List<Student> students = new ArrayList<>();

    // Constructor of sample data
    public StudentController() {

        students.add(new Student(1L, "urban", "Bobola", "urbanbobola20.com", "Computer Science", 4.0));
        students.add(new Student(2L, "Lena", "Paolla", "kazeneza@gmail.com.com", "Networking", 3.4));
        students.add(new Student(3L, "Ndayizeye", "Owen", "owenndayizeye.com", "Info Managment", 3.9));
        students.add(new Student(4L, "Gaella", "Irisa", "irisadiran54.com", "Business", 3.2));
        students.add(new Student(5L, "Vivine", "Uwimana", "uwimanavivine20.com", "Theology", 3.6));
    }

    // GET all students
    @GetMapping
    public List<Student> getAllStudents() {
        return students;
    }

    // GET student by ID
    @GetMapping("/{studentId}")
    public Student getStudentById(@PathVariable Long studentId) {

        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }

        return null;
    }

    // GET students by major
    @GetMapping("/major/{major}")
    public List<Student> getStudentsByMajor(@PathVariable String major) {

        List<Student> result = new ArrayList<>();

        for (Student student : students) {
            if (student.getMajor().equalsIgnoreCase(major)) {
                result.add(student);
            }
        }

        return result;
    }

    // GET students by GPA filter
    @GetMapping("/filter")
    public List<Student> getStudentsByGpa(@RequestParam Double gpa) {

        List<Student> result = new ArrayList<>();

        for (Student student : students) {
            if (student.getGpa() >= gpa) {
                result.add(student);
            }
        }

        return result;
    }

    // POST new student
    @PostMapping
    public Student addStudent(@RequestBody Student student) {

        students.add(student);

        return student;
    }

    // PUT update student
    @PutMapping("/{studentId}")
    public Student updateStudent(@PathVariable Long studentId,
                                 @RequestBody Student updatedStudent) {

        for (Student student : students) {

            if (student.getStudentId().equals(studentId)) {

                student.setFirstName(updatedStudent.getFirstName());
                student.setLastName(updatedStudent.getLastName());
                student.setEmail(updatedStudent.getEmail());
                student.setMajor(updatedStudent.getMajor());
                student.setGpa(updatedStudent.getGpa());

                return student;
            }
        }

        return null;
    }
}
