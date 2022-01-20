package com.example.antraTrainingSpringRest.controller;

import com.example.antraTrainingSpringRest.model.StudentClassIntermediate;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import com.example.antraTrainingSpringRest.dao.ClassDao;
import com.example.antraTrainingSpringRest.model.ClassUnit;

import com.example.antraTrainingSpringRest.dao.StudentDao;
import com.example.antraTrainingSpringRest.model.Student;

@RestController
public class StudentController {

    @Autowired
    ClassDao classdao;

    @Autowired
    StudentDao studentdao;


    @RequestMapping("/")
    public String home()
    {
        return "home.jsp";
    }

    //CRUD for Classes

    @PostMapping(path="/class",consumes= {"application/json"})
    public ResponseEntity<ClassUnit> addClass(@RequestBody ClassUnit classUnit)
    {
        classdao.save(classUnit);

        return new ResponseEntity<>(classUnit, HttpStatus.OK);
    }

    @GetMapping(path="/classes")
    public ResponseEntity<List<ClassUnit>> getClasses()
    {
        return new ResponseEntity<>(classdao.findAll(), HttpStatus.OK);

    }

    @RequestMapping("/student/{cid}")
    public ResponseEntity<ClassUnit> getClass(@PathVariable("cid")long cid)
    {
        ClassUnit cl = classdao.findById(cid).orElseThrow(IllegalArgumentException::new);
        return new ResponseEntity<>(cl, HttpStatus.OK);


    }

    @PutMapping(path="/class",consumes= {"application/json"})
    public ResponseEntity<ClassUnit> saveOrUpdateClass(@RequestBody ClassUnit classUnit)
    {
        classdao.save(classUnit);
        return new ResponseEntity<>(classUnit, HttpStatus.OK);
    }

    @DeleteMapping("/class/{cid}")
    public ResponseEntity<String> deleteClass(@PathVariable long cid)
    {
        ClassUnit a = classdao.findById(cid).orElseThrow(IllegalArgumentException::new);
        classdao.delete(a);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }








    //CRUD for Student-ClassUnit relation

    @PostMapping(path="/studentsofaclass/{cid}")
    public ResponseEntity<String> addStudentToClass(@PathVariable("cid")long cid , @RequestParam long sid)
    {
        ClassUnit cu = classdao.findById(cid).orElseThrow(IllegalArgumentException::new);
        //List<StudentClassIntermediate> intermediateListlist = cu.getIntermediates();
        Student stu = studentdao.findById(sid).orElseThrow(IllegalArgumentException::new);

        StudentClassIntermediate intermediate = new StudentClassIntermediate();

        intermediate.setStudent(stu);
        intermediate.setClassunit(cu);

        cu.addStudentClassIntermediate(intermediate);
        classdao.save(cu);

        stu.addStudentClassIntermediate(intermediate);
        studentdao.save(stu);

        return new ResponseEntity<>("connection between class_id: " +
                cid + " and student_id: " + sid + " is properly established", HttpStatus.OK);

    }

    @GetMapping(path="/studentsofaclass/{cid}")
    public ResponseEntity<List<Student>> getStudentFromClass(@PathVariable("cid")long cid)
    {
        ClassUnit cu = classdao.findById(cid).orElseThrow(IllegalArgumentException::new);
        List<StudentClassIntermediate> intermediateList = cu.getIntermediates();
        List<Student> studentList = new ArrayList<>();

        for(StudentClassIntermediate im: intermediateList){

            studentList.add(im.getStudent());
        }

        return new ResponseEntity<>(studentList, HttpStatus.OK);

    }

    @DeleteMapping("/studentsofaclass/{cid}")
    public ResponseEntity<String> deleteStudentFromClass(@PathVariable long cid, @RequestParam long sid)
    {
        ClassUnit cu = classdao.findById(cid).orElseThrow(IllegalArgumentException::new);
        //List<StudentClassIntermediate> intermediateListlist = cu.getIntermediates();
        Student stu = studentdao.findById(sid).orElseThrow(IllegalArgumentException::new);

        StudentClassIntermediate intermediate = new StudentClassIntermediate();

        intermediate.setStudent(stu);
        intermediate.setClassunit(cu);

        cu.removeStudentClassIntermediate(intermediate);
        classdao.save(cu);

        stu.removeStudentClassIntermediate(intermediate);
        studentdao.save(stu);

        return new ResponseEntity<>("connection between class_id: " +
                cid + " and student_id: " + sid +
                " is properly destroyed, the student is no longer enrolled in the class",
                HttpStatus.OK);
    }






    //CRUD for Students

    @PostMapping(path="/student",consumes= {"application/json"})
    public ResponseEntity<Student> addStudent(@RequestBody Student student)
    {
        studentdao.save(student);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping(path="/students")
    public ResponseEntity<List<Student>> getStudents()
    {
        return new ResponseEntity<>(studentdao.findAll(), HttpStatus.OK);

    }

    @RequestMapping("/student/{sid}")
    public ResponseEntity<Student> getStudent(@PathVariable("sid")long sid)
    {
        Student st = studentdao.findById(sid).orElseThrow(IllegalArgumentException::new);
        return new ResponseEntity<>(st, HttpStatus.OK);

    }

    @PutMapping(path="/student",consumes= {"application/json"})
    public ResponseEntity<Student> saveOrUpdateStudent(@RequestBody Student student)
    {
        studentdao.save(student);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @DeleteMapping("/class/{sid}")
    public ResponseEntity<String> deleteStudent(@PathVariable long sid)
    {
        Student a = studentdao.findById(sid).orElseThrow(IllegalArgumentException::new);
        studentdao.delete(a);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }




    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> xxExceptionHandler() {
        //log
        return new ResponseEntity<String>("illegal argument, id does not exist", HttpStatus.NOT_FOUND);
    }





}
