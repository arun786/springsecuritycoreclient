package com.arun.springsecuritycoreclient.service;

import com.arun.springsecuritycoreclient.model.Student;

import java.util.List;

/**
 * @author arun on 8/30/20
 */
public interface StudentService {

    List<Student> getStudent(String name);
}
